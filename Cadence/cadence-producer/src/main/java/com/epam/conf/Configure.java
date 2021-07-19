package com.epam.conf;

import com.epam.cadence.*;
import com.uber.cadence.client.*;
import com.uber.cadence.serviceclient.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

@Configuration
public class Configure {

    @Bean
    public WorkflowClient workflowClient(@Value("${domain}") String domain) {
        return WorkflowClient.newInstance(
                new WorkflowServiceTChannel(ClientOptions.defaultInstance()),
                WorkflowClientOptions.newBuilder().setDomain(domain).build());
    }

    @Bean
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
    public Invoker helloWorld(@Autowired WorkflowClient workflowClient) {
        return workflowClient.newWorkflowStub(Invoker.class);
    }

}
