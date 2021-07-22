package com.epam.cadence.async;

import com.uber.cadence.workflow.*;

public interface InvokerAsync {
    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 1000, taskList = "HelloActivityAsync")
    String invokeAsync(Integer clazz, Integer method);
}
