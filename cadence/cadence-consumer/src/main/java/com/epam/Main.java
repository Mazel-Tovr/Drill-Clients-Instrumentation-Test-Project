package com.epam;

import com.epam.cadence.async.*;
import com.epam.cadence.sync.*;
import com.uber.cadence.client.*;
import com.uber.cadence.serviceclient.*;
import com.uber.cadence.worker.*;

public class Main {
    private final static String domain = "samples-domain";
    public final static String task = "HelloActivity";
    public final static String asyncTask = "HelloActivityAsync";

    public static void main(String[] args) {

        WorkflowClient workflowClient =
                WorkflowClient.newInstance(
                        new WorkflowServiceTChannel(ClientOptions.newBuilder().build()),
                        WorkflowClientOptions.newBuilder().setDomain(domain).setInterceptors(new WorkflowClientInterceptorBase()).build());
        WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
        Worker worker = factory.newWorker(task);
        worker.registerWorkflowImplementationTypes(InvokerImpl.class);
        Worker worker1 = factory.newWorker(asyncTask);
        worker1.registerActivitiesImplementations();
        worker1.registerWorkflowImplementationTypes(AsyncFlowImpl.class);
        worker1.registerActivitiesImplementations(new AsyncActivityImpl());
        factory.start();
    }
}
