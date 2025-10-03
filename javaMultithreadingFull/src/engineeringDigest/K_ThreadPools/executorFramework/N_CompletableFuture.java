package engineeringDigest.K_ThreadPools.executorFramework;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Used for async programming with multi-thread
 *
 * */
public class N_CompletableFuture {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            try{
                Thread.sleep(2000);
                System.out.println("worker thread");
            }catch (Exception e){

            }
            return "ok";
        });

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(()->{
            try{
                Thread.sleep(3000);
                System.out.println("worker thread");
            }catch (Exception e){

            }
            return "ok";
        });

        try {
            String res = completableFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


        CompletableFuture<Void> f = CompletableFuture.allOf(completableFuture,completableFuture2);
        f.join();

        System.out.println("Main");
    }
}
