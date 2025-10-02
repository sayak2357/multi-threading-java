package engineeringDigest.K_ThreadPools.executorFramework;

import java.util.concurrent.*;

public class C_FutureCallableRunnable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Use callable when the anonymous function returns some value
        Callable<?> callable = () -> "Hello";
        Future<?> future = executorService.submit( callable);

        // Use runnable when anonymous function doesn't return anything
        Runnable runnable = () -> System.out.println("Hello");
        Future<?> future1 = executorService.submit(runnable);

        System.out.println(future.get());  // waits for completion
        if(future.isDone()){
            System.out.println("Task done!");
        }
        executorService.shutdown();
        Thread.sleep(100);
        System.out.println(executorService.isTerminated());
    }
}
