package engineeringDigest.K_ThreadPools.executorFramework;

public class A_MainWithoutExecutor {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[9];
        for(int i=1;i<10;i++){

            int finalI = i;
            threads[i-1] = new Thread(()->{
                long result = factorial(finalI);
                System.out.println(result);
            });
            threads[i-1].start();
            //System.out.println(factorial(i));
        }
        for(Thread thread:threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
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
