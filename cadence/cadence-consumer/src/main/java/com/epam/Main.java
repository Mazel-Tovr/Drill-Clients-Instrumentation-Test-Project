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
        System.out.println("Cadence-consumer started");
        String host = System.getProperty("cadence.host");
        String port = System.getProperty("cadence.port");
        host = host == null ? "cadence" : host;
        port = port == null ? "7933" : port;
        System.out.println("Cadence Host " + host + " Port " + port);
        WorkflowClient workflowClient =
                WorkflowClient.newInstance(
                        new WorkflowServiceTChannel(ClientOptions.newBuilder()
                                .setHost(host)
                                .setPort(Integer.parseInt(port))
                                .build()),
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
