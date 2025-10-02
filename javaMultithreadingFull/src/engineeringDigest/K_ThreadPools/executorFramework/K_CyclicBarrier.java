package engineeringDigest.K_ThreadPools.executorFramework;

import java.util.concurrent.*;

public class K_CyclicBarrier {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int numberOfServices = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
        CyclicBarrier barrier = new CyclicBarrier(numberOfServices);

        executorService.submit(new DependentService3(barrier));
        executorService.submit(new DependentService3(barrier));
        executorService.submit(new DependentService3(barrier));


        System.out.println("Main");
        barrier.reset();

        executorService.shutdown();
    }




}

class DependentService3 implements Callable<String>{

    private final CyclicBarrier barrier;

    public DependentService3(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public String call() throws Exception {
        try {
            System.out.println(Thread.currentThread().getName() + " service started.");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " is waiting at the barrier.");
            barrier.await();
        }finally {

        }

        return "ok";
    }
}