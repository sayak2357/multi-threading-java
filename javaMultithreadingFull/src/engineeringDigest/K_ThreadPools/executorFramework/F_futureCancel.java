package engineeringDigest.K_ThreadPools.executorFramework;

import java.util.concurrent.*;

public class F_futureCancel {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(() ->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("hello from future");
            return 42;
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Exception occurred");
        }
        future.cancel(false); // 'false' means don't stop the future task
        System.out.println(future.isCancelled());
        System.out.println(future.isDone());
        executorService.shutdown();
    }
}
