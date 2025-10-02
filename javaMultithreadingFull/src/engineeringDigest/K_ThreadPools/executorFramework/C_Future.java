package engineeringDigest.K_ThreadPools.executorFramework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class C_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit( () -> "Hello");

        System.out.println(future.get());  // waits for completion
        if(future.isDone()){
            System.out.println("Task done!");
        }
        executorService.shutdown();
    }
}
