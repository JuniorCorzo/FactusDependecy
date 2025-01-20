package io.github.juniorcorzo.services;

import io.github.juniorcorzo.tasks.InitializedAuthContextTask;
import io.github.juniorcorzo.tasks.InitializedGeneralContextsTask;

import java.util.concurrent.*;

public class TasksManagerService {
    private final ExecutorService executorService;

    public TasksManagerService() throws ExecutionException, InterruptedException {
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.initializedContexts();
    }

    private void initializedContexts() {
        this.submitTaskNow(new InitializedAuthContextTask());
        this.submitTaskNow(new InitializedGeneralContextsTask());
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

    public <T> T obtainNow(Future<T> task) {
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T obtainNow(Callable<T> task) {
        try {
            return this.executorService.submit(task).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutDown() {
        this.executorService.shutdown();
    }
}
