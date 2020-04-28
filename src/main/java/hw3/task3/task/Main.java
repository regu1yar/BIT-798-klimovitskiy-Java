package hw3.task3.task;

import static java.lang.Thread.sleep;

public class Main {
    private static boolean firstCalled = false;

    private static synchronized void waitFirst() {
        if (!firstCalled) {
            firstCalled = true;
            try {
                sleep(1_000);
            } catch (InterruptedException ignored) {

            }
        }
    }

    public static void main(String[] args) {
        Task<Integer> task = new Task<>(() -> {
            waitFirst();
//            throw new RuntimeException();
            return 42;
        });

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println(finalI + " " + task.get());
                } catch (Exception e) {
                    System.out.println("Exception in " + finalI);
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
