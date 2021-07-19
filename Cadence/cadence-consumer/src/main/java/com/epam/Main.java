package com.epam;

import com.epam.cadence.*;
import com.uber.cadence.client.*;
import com.uber.cadence.serviceclient.*;
import com.uber.cadence.worker.*;

public class Main {
    private final static String domain = "samples-domain";
    public final static String taskList = "HelloActivity";

    public static void main(String[] args) {

        WorkflowClient workflowClient =
                WorkflowClient.newInstance(
                        new WorkflowServiceTChannel(ClientOptions.defaultInstance()),
                        WorkflowClientOptions.newBuilder().setDomain(domain).build());
        WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
        Worker worker = factory.newWorker(taskList);
        worker.registerWorkflowImplementationTypes(InvokerImpl.class);
        factory.start();
    }
}
