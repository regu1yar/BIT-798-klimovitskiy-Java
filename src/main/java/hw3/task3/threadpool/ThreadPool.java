package hw3.task3.threadpool;

public interface ThreadPool {
    void start();
    void execute(Runnable task);
}
