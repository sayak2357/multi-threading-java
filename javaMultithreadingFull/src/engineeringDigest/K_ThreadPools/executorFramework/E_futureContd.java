package engineeringDigest.K_ThreadPools.executorFramework;

import java.util.concurrent.*;

public class E_futureContd {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(() ->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return 42;
        });

        Integer i = null;
        try{
            i = future.get(3, TimeUnit.SECONDS);
            System.out.println(future.isDone());
            System.out.println(i);
        } catch (ExecutionException  | InterruptedException | TimeoutException e) {
            System.out.printf("Exception occured: "+e);
            Thread.currentThread().interrupt();
        }
        executorService.shutdown();
    }
}
