package Level2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Q11 {
    public static void main(String[] args) throws InterruptedException {
        CounterMod counter = new CounterMod();

        Thread t1 = new Thread(()->{
            for(int i=0;i<1000000;i++){
                try {
                    counter.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(()->{
            for(int i=0;i<1000000;i++){
                try {
                    counter.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("value of count: "+counter.getCount());
    }

    private static class CounterMod{
        private int count=0;
        private Lock lock = new ReentrantLock();

        public void increment() throws InterruptedException {
            if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {   // Only proceed if lock acquired
                try {
                    count++;
                } finally {
                    lock.unlock();
                }
            } else {
                // Lock not acquired — you could skip or retry here
                // For demo, we just ignore
            }
            }

            public int getCount() {
                return count;
            }
        }

}

