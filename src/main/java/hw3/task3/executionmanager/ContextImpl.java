package hw3.task3.executionmanager;

import hw3.task3.threadpool.ThreadPool;

import java.util.List;

public class ContextImpl implements Context {
    private final ThreadPool threadPool;
    private final List<Runnable> tasks;
    private final Runnable callback;
    private Integer completedTasks = 0;
    private Integer failedTasks = 0;
    private Integer interruptedTasks = 0;
    private Boolean interrupted = false;
    private Boolean finished = false;

    public ContextImpl(ThreadPool threadPool, Runnable callback, List<Runnable> tasks) {
        this.threadPool = threadPool;
        this.callback = callback;
        this.tasks = tasks;

        new Thread(this::run).start();
    }

    @Override
    public synchronized int getCompletedTaskCount() {
        return completedTasks;
    }

    @Override
    public synchronized int getFailedTaskCount() {
        return failedTasks;
    }

    @Override
    public synchronized int getInterruptedTaskCount() {
        return interruptedTasks;
    }

    @Override
    public synchronized void interrupt() {
        interrupted = true;
    }

    @Override
    public synchronized boolean isFinished() {
        return finished;
    }

    public void run() {
        runTasks();
        waitTasks();
        synchronized (this) {
            finished = true;
        }
        callback.run();
    }

    private void waitTasks() {
        synchronized (this) {
            while (interruptedTasks + failedTasks + completedTasks != tasks.size()) {
                try {
                    this.wait();
                } catch (InterruptedException ignored) {

                }
            }
        }
    }

    private void runTasks() {
        for (Runnable task : tasks) {
            threadPool.execute(() -> runTask(task));
        }
    }

    private void runTask(Runnable task) {
        synchronized (this) {
            if (interrupted) {
                ++interruptedTasks;
                this.notify();
                return;
            }
        }
        try {
            task.run();
            synchronized (this) {
                ++completedTasks;
                this.notify();
            }
        } catch (Exception e) {
            synchronized (this) {
                ++failedTasks;
                this.notify();
            }
        }
    }
}
