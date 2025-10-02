package engineeringDigest.K_ThreadPools.executorFramework;

import java.util.concurrent.*;

public class J_WithCountdownLatch {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int numberOfServices = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
        CountDownLatch latch = new CountDownLatch(numberOfServices);

        executorService.submit(new DependentService2(latch));
        executorService.submit(new DependentService2(latch));
        executorService.submit(new DependentService2(latch));
        latch.await();

        System.out.println("Main");


        executorService.shutdown();
    }
}

class DependentService2 implements Callable<String>{

    private final CountDownLatch latch;

    public DependentService2(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public String call() throws Exception {
        try {
            System.out.println(Thread.currentThread().getName() + " service started.");
            Thread.sleep(2000);
        }finally {
            latch.countDown();
        }

        return "ok";
    }
}

