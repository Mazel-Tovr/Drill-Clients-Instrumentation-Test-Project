package com.epam.cadence.sync;

import com.uber.cadence.workflow.*;

public interface Invoker {
    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 1000, taskList = "HelloActivity")
    void invoke(Integer clazz, Integer method);
}
