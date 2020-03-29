package hw3.task3.threadpool;

import java.util.*;

public class FixedThreadPool implements ThreadPool {
    private final Collection<Thread> threads;
    private Queue<Runnable> tasks;

    FixedThreadPool(int threadsNum) {
        threads = new ArrayList<>();
        for (int i = 0; i < threadsNum; i++) {
            threads.add(new Thread(this::threadLoop));
        }
        tasks = new ArrayDeque<>();
    }

    @Override
    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable task) {
        synchronized (tasks) {
            tasks.add(task);
            tasks.notify();
        }
    }

    private void threadLoop() {
        while (true) {
            waitForTask();
            runTask();
        }
    }

    private void runTask() {
        Runnable task = null;
        synchronized (tasks) {
            if (!tasks.isEmpty()) {
                task = tasks.poll();
            }
        }
        if (task != null) {
            task.run();
        }
    }

    private void waitForTask() {
        synchronized (tasks) {
            while (tasks.isEmpty()) {
                try {
                    tasks.wait();
                } catch (InterruptedException ignored) {

                }
            }
        }
    }
}
