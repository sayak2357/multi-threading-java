package engineeringDigest.K_ThreadPools.executorFramework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class D_FutureCallableRunnableInvokeAll {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);


        // Use runnable when anonymous function doesn't return anything
        Runnable runnable = () -> System.out.println("Hello");
        //Future<?> future = executorService.submit(runnable);

        Callable<Integer> callable1 =  () -> 1;
        Callable<Integer> callable2 =  () -> 2;
        Callable<Integer> callable3 =  () -> 3;
        List<Callable<Integer>> list = Arrays.asList(callable1,callable2,callable3);
        executorService.invokeAll(list);
        // InvokeAll blocks main thread
        executorService.shutdown();
        Thread.sleep(100);
        System.out.println(executorService.isTerminated());
    }
}
