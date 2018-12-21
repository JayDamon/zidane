package com.protean.legislativetracker.zidane.service.update;

import com.protean.legislativetracker.zidane.service.ZidaneAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService<R> {

    private static final Logger log = LoggerFactory.getLogger(AsyncService.class);

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<R>> createThreadToProcessItem(int lowerVal, int upperVal, ZidaneAsync async) {

        log.info("Starting thread to update item starting at <" + lowerVal + "> and ending at <" + upperVal + "> on Thread: "
                + Thread.currentThread().getName());

        // Retrieve set of 200 bills
        for (int i = lowerVal ; i < upperVal ; i++) {
            async.doThreadedAction(i);
        }
        CompletableFuture<List<R>> futures = CompletableFuture.completedFuture(async.processThreadedItems());
        async.runPostThreadCleanup();
        return futures;
    }

}
