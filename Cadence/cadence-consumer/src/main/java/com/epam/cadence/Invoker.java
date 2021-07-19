package com.epam.cadence;

import com.epam.*;
import com.uber.cadence.workflow.*;

public interface Invoker {
    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 1, taskList = Main.taskList)
    void invoke(Integer clazz, Integer method);
}
