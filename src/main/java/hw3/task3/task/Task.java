package hw3.task3.task;

import java.util.concurrent.Callable;

public class Task<T> {
    private final Callable<? extends T> callable;
    private TaskException thrownException = null;
    private T result = null;
    private volatile boolean finished = false;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() throws TaskException {
        if (!finished) {
            synchronized (this) {
                if (!finished) {
                    try {
                        result = callable.call();
                    } catch (Exception e) {
                        thrownException = new TaskException(e);
                    } finally {
                        finished = true;
                    }
                }
            }
        }

        if (thrownException != null) {
            throw thrownException;
        }

        return result;
    }
}
