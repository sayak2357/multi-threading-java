package engineeringDigest.G_locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class G_RedWriteLock {

    private int count = 0;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();  // allows multiple thread to acquire read lock while only single thread for write

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();


    public void increment(){
        writeLock.lock();
        try{
            count++;
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();
        }
    }

    public int getCount(){
        readLock.lock();
        try{
            return count;
        }finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        G_RedWriteLock counter = new G_RedWriteLock();

        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName()+ " read: "+counter.getCount());
                }
            }
        };

        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    counter.increment();
                    System.out.println(Thread.currentThread().getName()+" counter incremented");
                }
            }
        };

        Thread writerThread = new Thread(writeTask);
        Thread readerThread1 = new Thread(readTask);
        Thread readerThread2 = new Thread(readTask);

        writerThread.start();
        readerThread1.start();
        readerThread2.start();

        writerThread.join();
        readerThread1.join();
        readerThread2.join();


        System.out.println("Reader writer threads completed. Final count: "+counter.getCount());
    }
}
