package hw3.task3.threadpool;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ScalableThreadPool(1, 3);
        threadPool.start();
        Set<Integer> numberSet = new HashSet<>();
        for (int i = 0; i < 100000; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                synchronized (numberSet) {
                    numberSet.add(finalI);
                }
            });
        }
        sleep(10_000);
        for (int i = 0; i < 100000; i++) {
            if (!numberSet.contains(i)) {
                System.out.println(":(((((( Didn't proceed " + i);
            }
        }
        System.out.println("End");
    }
}
