package hw3.task3.executionmanager;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutionManager manager = new ExecutionManagerImpl();
        Context context = manager.execute(
                () -> System.out.println("Running callback"),
                () -> {
                    try {
                        sleep(2_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                () -> { throw new RuntimeException(); },
                () -> System.out.println("Running task")
        );

        System.out.println("Completed: " + context.getCompletedTaskCount());
        System.out.println("Failed: " + context.getFailedTaskCount());
        System.out.println("Interrupted: " + context.getInterruptedTaskCount());
        context.interrupt();
        while (!context.isFinished()) {
            System.out.println("Is finished: " + context.isFinished());
            sleep(1_000);
        }
        System.out.println("Completed: " + context.getCompletedTaskCount());
        System.out.println("Failed: " + context.getFailedTaskCount());
        System.out.println("Interrupted: " + context.getInterruptedTaskCount());
    }
}
