package G_locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class C_BankAccountReentrantLock {
    private int balance = 100;
    private final Lock lock = new ReentrantLock(); // declare final so that to ensure it's not modified

    public void withdraw(int amount){
        System.out.println(Thread.currentThread().getName()+" attempting to withdraw amount: "+amount);
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) { // try locking by waiting for at max 1000 ms ie 1 sec
                if (balance >= amount) {

                    try {
                        System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                        Thread.sleep(10000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + balance);
                    } catch (InterruptedException e) {
                        // throw new RuntimeException(e);
                        Thread.currentThread().interrupt(); // good practice
                    }finally {
                        lock.unlock();
                    }

                } else {
                    System.out.println(Thread.currentThread().getName() + " insufficient balance");
                }
            }else{
                System.out.println(Thread.currentThread().getName() + " couldn't acquire lock. Will try again later.");
            }
        }catch (Exception e){
            Thread.currentThread().interrupt(); // good practice for executing clean-up code
        }
        if(Thread.currentThread().isInterrupted()){
            // clean up
        }
    }
}
