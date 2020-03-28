package hw3.task3;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class ContextImpl implements Context {
    private final List<Runnable> tasks;
    private final Runnable callback;
    private List<Thread> threads = new ArrayList<>();
    private Integer completedTasks = 0;
    private Integer failedTasks = 0;
    private Integer interruptedTasks = 0;
    private Boolean interrupted = false;
    private Boolean finished = false;

    public ContextImpl(Runnable callback, List<Runnable> tasks) {
        this.callback = callback;
        this.tasks = tasks;

        new Thread(this::run).start();
    }

    @Override
    public int getCompletedTaskCount() {
        synchronized (completedTasks) {
            return completedTasks;
        }
    }

    @Override
    public int getFailedTaskCount() {
        synchronized (failedTasks) {
            return failedTasks;
        }
    }

    @Override
    public int getInterruptedTaskCount() {
        synchronized (interruptedTasks) {
            return interruptedTasks;
        }
    }

    @Override
    public void interrupt() {
        synchronized (interrupted) {
            interrupted = true;
        }
    }

    @Override
    public boolean isFinished() {
        synchronized (finished) {
            return finished;
        }
    }

    public void run() {
        runTasks();
        waitTasks();
        synchronized (finished) {
            finished = true;
        }
        callback.run();
    }

    private void waitTasks() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ignored) {

            }
        }
    }

    private void runTasks() {
        int i = 0;
        for (Runnable task : tasks) {
            if (i == 2) {
                try {
                    sleep(2_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (interrupted) {
                if (interrupted) {
                    interruptedTasks = tasks.size() - i;
                    break;
                }
            }
            runTask(task);
            ++i;
        }
    }

    private void runTask(Runnable task) {
        Thread thread = new Thread(() -> {
            try {
                task.run();
                synchronized (completedTasks) {
                    ++completedTasks;
                }
            } catch (Exception e) {
                synchronized (failedTasks) {
                    ++failedTasks;
                }
            }
        });
        thread.start();
        threads.add(thread);
    }
}
