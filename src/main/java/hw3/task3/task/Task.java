package hw3.task3.task;

import java.util.concurrent.Callable;

public class Task<T> {
    private final Callable<? extends T> callable;
    private volatile TaskException thrownException = null;
    private volatile T result = null;
    private volatile Boolean called = false;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() throws TaskException {
        if (thrownException != null) {
            throw thrownException;
        }

        if (result != null) {
            return result;
        }

        if (called) {
            synchronized (this) {
                while (result == null && thrownException == null) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (result != null) {
                return result;
            } else {
                throw thrownException;
            }
        } else {
            called = true;
            try {
                result = callable.call();
                synchronized (this) {
                    this.notifyAll();
                }
                return result;
            } catch (Exception e) {
                thrownException = new TaskException(e);
                synchronized (this) {
                    this.notifyAll();
                }
                throw thrownException;
            }
        }
    }
}
