package com.epam.conf;

import com.uber.cadence.*;
import com.uber.cadence.serviceclient.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.event.*;
import org.springframework.stereotype.*;

@Component
public class ListenerForContextUp implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${domain}")
    private String domain;

    public void registerDomain() {
        IWorkflowService cadenceService = new WorkflowServiceTChannel(ClientOptions.defaultInstance());
        RegisterDomainRequest request = new RegisterDomainRequest();
        request.setDescription("Java Samples");
        request.setEmitMetric(false);
        request.setName(domain);
        int retentionPeriodInDays = 1;
        request.setWorkflowExecutionRetentionPeriodInDays(retentionPeriodInDays);
        try {
            cadenceService.RegisterDomain(request);
            System.out.println(
                    "Successfully registered domain \""
                            + domain
                            + "\" with retentionDays="
                            + retentionPeriodInDays);
        } catch (DomainAlreadyExistsError e) {
            System.out.println("Domain \"" + domain + "\" is already registered");
        } catch (Exception e) {
            throw new RuntimeException("Context init exception", e);
        }
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent evenst) {
        registerDomain();
    }
}


