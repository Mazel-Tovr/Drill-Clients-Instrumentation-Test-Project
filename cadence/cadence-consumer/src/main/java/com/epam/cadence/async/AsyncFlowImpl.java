package com.epam.cadence.async;

import com.uber.cadence.workflow.*;

public class AsyncFlowImpl implements InvokerAsync {

    private final AsyncActivity asyncActivity = Workflow.newActivityStub(AsyncActivity.class);

    @Override
    public String invokeAsync(Integer clazz, Integer method) {
        return Async.function(asyncActivity::invokeAsyncActivity, clazz, method).get();
    }
}
