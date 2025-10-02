package G_locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class F_UnfairLockExample {
    private final Lock unfairLock = new ReentrantLock(true);         // fairness = true means threads are scheduled in order

    public void accessResource(){
        unfairLock.lock();

        try{
            System.out.println(Thread.currentThread().getName()+ " acquired the lock");
            Thread.sleep(1000);
        }catch (Exception e){
            Thread.currentThread().interrupt();
        }finally {
            System.out.println(Thread.currentThread().getName()+" released the lock");
            unfairLock.unlock();

        }
    }

    public static void main(String[] args) {
        F_UnfairLockExample example = new F_UnfairLockExample();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                example.accessResource();
            }
        };

        Thread t1 = new Thread(task,"Thread 1");
        Thread t2 = new Thread(task,"Thread 2");
        Thread t3 = new Thread(task,"Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }
}
