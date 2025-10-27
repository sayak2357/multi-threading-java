package udemyMichaelPogrebinsky.K_ThreadingModels;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class A_ThreadPerTaskModel {
    private static final int NUMBER_OF_TASKS = 1000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("press Enter to start");
        sc.nextLine();
        System.out.printf("Running %d tasks\n",NUMBER_OF_TASKS);

        long start = System.currentTimeMillis();

        performTasks();
        System.out.printf("Tasks took %dms to complete\n",System.currentTimeMillis() - start);
    }

    private static void performTasks(){
        ExecutorService executorService = Executors.newCachedThreadPool();

            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        blockingOperation();
                    }
                });
            }
    }
    private static void blockingOperation(){
        System.out.println("Executing a block task from Thread: "+Thread.currentThread());

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
