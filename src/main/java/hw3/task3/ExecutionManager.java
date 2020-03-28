package hw3.task3;

public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
