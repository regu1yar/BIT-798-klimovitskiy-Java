package hw3.task3.executionmanager;

import java.util.Arrays;

public class ExecutionManagerImpl implements ExecutionManager {
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        return new ContextImpl(callback, Arrays.asList(tasks));
    }
}
