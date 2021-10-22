package com.epam.conf;

import com.epam.cadence.async.*;
import com.epam.cadence.sync.*;
import com.uber.cadence.client.*;
import com.uber.cadence.serviceclient.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class Configure {

    @Bean
    public WorkflowClient workflowClient(@Value("${domain}") String domain) {
        ClientOptions options = ClientOptions.newBuilder().build();
        return WorkflowClient.newInstance(
                new WorkflowServiceTChannel(options),
                WorkflowClientOptions.newBuilder().setDomain(domain).build());
    }

    @Bean
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
    public Invoker invoker(@Autowired WorkflowClient workflowClient, @Value("${task.list}") String task) {
        return workflowClient.newWorkflowStub(Invoker.class);
    }

    @Bean
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
    public InvokerAsync invokerAsync(@Autowired WorkflowClient workflowClient, @Value("${task.list.async}") String task) {
        Map<String, Object> map = new HashMap<>();
        map.put("31","3122313");
        return workflowClient.newWorkflowStub(InvokerAsync.class,new WorkflowOptions.Builder().setMemo(map).build());
    }

}
