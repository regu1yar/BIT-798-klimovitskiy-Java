package hw3.task3.executionmanager;

import hw3.task3.threadpool.FixedThreadPool;
import hw3.task3.threadpool.ThreadPool;

import java.util.Arrays;

public class ExecutionManagerImpl implements ExecutionManager {
    private final ThreadPool threadPool;

    public ExecutionManagerImpl() {
        threadPool = new FixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        threadPool.start();
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        return new ContextImpl(threadPool, callback, Arrays.asList(tasks));
    }
}
