package com.epam.cadence;

import com.uber.cadence.workflow.*;

public interface Invoker {
    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 1, taskList = "HelloActivity")
    void invoke(Integer clazz, Integer method);
}
