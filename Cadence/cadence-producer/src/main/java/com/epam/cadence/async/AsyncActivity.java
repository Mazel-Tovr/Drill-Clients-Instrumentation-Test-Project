package com.epam.cadence.async;

import com.uber.cadence.activity.*;

public interface AsyncActivity {
    @ActivityMethod(scheduleToCloseTimeoutSeconds = 10000)
    String invokeAsyncActivity(Integer clazz, Integer method);
}
