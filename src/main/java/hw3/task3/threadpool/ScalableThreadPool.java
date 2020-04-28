package hw3.task3.threadpool;

import java.util.*;

public class ScalableThreadPool implements ThreadPool {
    private static class ThreadWithState {
        public final Thread thread;
        public boolean isRunningTask = false;
        public final int threadNumber;

        public ThreadWithState(Thread thread, int threadNumber) {
            this.thread = thread;
            this.threadNumber = threadNumber;
        }
    }

    private final Collection<ThreadWithState> threads;
    private final Queue<Runnable> tasks;
    private int idleThreads = 0;
    private final int minThreads;
    private final int maxThreads;
    private int threadCounter = 0;

    ScalableThreadPool(int min, int max) {
        this.minThreads = min;
        this.maxThreads = max;
        threads = new ArrayList<>();
        for (int i = 0; i < min; i++) {
            addThread();
        }
        tasks = new ArrayDeque<>();
    }

    @Override
    public void start() {
        for (ThreadWithState thread : threads) {
            thread.thread.start();
        }
        new Thread(this::removingIdlesThread).start();
    }

    @Override
    public void execute(Runnable task) {
        synchronized (tasks) {
            tasks.add(task);
            if (idleThreads == 0 && threads.size() < maxThreads) {
                Thread thread = addThread();
                thread.start();
            }
            tasks.notifyAll();
        }
    }

    private Thread addThread() {
        Thread thread = new Thread(() -> threadLoop(threadCounter));
        threads.add(new ThreadWithState(thread, threadCounter));
        ++threadCounter;
        return thread;
    }

    private void threadLoop(int threadNumber) {
        while (true) {
            waitForTask();
            runTask(threadNumber);
        }
    }

    private void waitForTask() {
        synchronized (tasks) {
            while (tasks.isEmpty()) {
                ++idleThreads;
                try {
                    tasks.wait();
                } catch (InterruptedException ignored) {

                }
                --idleThreads;
            }
        }
    }

    private void runTask(int threadNumber) {
        Runnable task = null;
        synchronized (tasks) {
            if (!tasks.isEmpty()) {
                task = tasks.poll();
                setThreadState(threadNumber, true);
            }
            if (tasks.isEmpty()) {
                tasks.notifyAll();
            }
        }
        if (task != null) {
            try {
                task.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setThreadState(threadNumber, false);
    }

    private void removingIdlesThread() {
        while (true) {
            synchronized (tasks) {
                waitForThreadToRemove();
                removeIdleThreads();
            }
        }
    }

    private void waitForThreadToRemove() {
        while (idleThreads == 0 || !tasks.isEmpty() || threads.size() == minThreads) {
            try {
                tasks.wait();
            } catch (InterruptedException ignored) {

            }
        }
    }

    private void removeIdleThreads() {
        while (threads.size() > minThreads) {
            boolean removed = false;
            for (ThreadWithState thread : threads) {
                if (!thread.isRunningTask) {
                    removeThread(thread);
                    removed = true;
                    break;
                }
            }
            if (!removed) {
                break;
            }
        }
    }

    private void removeThread(ThreadWithState thread) {
        thread.thread.interrupt();
        threads.remove(thread);
        --idleThreads;
    }

    private void setThreadState(int threadNumber, boolean state) {
        for (ThreadWithState thread : threads) {
            if (thread.threadNumber == threadNumber) {
                thread.isRunningTask = state;
            }
        }
    }
}
