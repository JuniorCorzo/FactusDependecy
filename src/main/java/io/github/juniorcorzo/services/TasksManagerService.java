package io.github.juniorcorzo.services;

import io.github.juniorcorzo.context.implementations.InMemoryAuthContext;
import io.github.juniorcorzo.tasks.InitializedAuthContextTask;
import io.github.juniorcorzo.tasks.InitializedGeneralContextsTask;
import io.github.juniorcorzo.tasks.RefreshTokenTask;
import lombok.Getter;

import java.util.concurrent.*;

@Getter
public class TasksManagerService {
    private final ExecutorService executorService;
    private final ScheduledExecutorService scheduledExecutorService;

    public TasksManagerService() throws ExecutionException, InterruptedException {
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.scheduledExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

        this.initializedContexts();
    }

    public void submitScheduleTask(Runnable task, long initialDelay, long delay, TimeUnit timeUnit){
        this.scheduledExecutorService.scheduleAtFixedRate(task, initialDelay, delay, timeUnit);
    }

    public <T> ScheduledFuture<T> submitScheduleTask(Callable<T> task, long delay, TimeUnit timeUnit) {
        return this.scheduledExecutorService.schedule(task, delay, timeUnit);
    }

    public <T> Future<T> submitTasks(Callable<T> task) {
        return this.executorService.submit(task);
    }

    public void submitTask(Runnable r) {
        this.executorService.submit(r);
    }

    public void submitTaskNow(Runnable r) {
        try {
            this.executorService.submit(r).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> Future<T> submitTask(Callable<T> task) {
        return this.executorService.submit(task);
    }

    public <T> T submitTaskNow(Callable<T> task) {
        try {
            return this.executorService.submit(task).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutDown() {
        this.executorService.shutdown();
    }

    private void initializedContexts() {
        this.submitTaskNow(new InitializedAuthContextTask());
        this.submitTaskNow(new InitializedGeneralContextsTask());

        long expiredIn = InMemoryAuthContext.getInstance().getAuthContext().expiresIn();
        this.submitScheduleTask(new RefreshTokenTask(), expiredIn, expiredIn, TimeUnit.SECONDS);
    }
}
