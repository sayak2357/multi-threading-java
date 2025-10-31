package Level4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Q16FixedSizeThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i=0;i<5;i++){
            executorService.submit(()->{
                System.out.println("thread name: "+Thread.currentThread().getName()+" & id" +
                        ": "+Thread.currentThread().getId());
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
