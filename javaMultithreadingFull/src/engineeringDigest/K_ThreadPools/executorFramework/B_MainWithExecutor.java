package engineeringDigest.K_ThreadPools.executorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class B_MainWithExecutor {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(9);
        for(int i=1;i<10;i++){

            int finalI = i;
           executorService.submit( () -> {
                long result = factorial(finalI);
                System.out.println(result);
            });
            //System.out.println(factorial(i));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Total time: "+ (System.currentTimeMillis() - startTime));
    }

    private static long factorial(int n){
        long result = 1l;
        try {
            Thread.sleep(1000);                  // mimicking when this method takes long time
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(int i=1;i<=n;i++){
            result *= i;
        }
        return result;
    }
}
