package udemyMichaelPogrebinsky.H_Semaphor;

import javax.sound.midi.ShortMessage;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class B_ProducerConsumerSample {
    public static void main(String[] args) {
        int CAPACITY = 2;
        Semaphore full = new Semaphore(0);
        Semaphore empty = new Semaphore(CAPACITY);
        Queue queue = new ArrayDeque<>();
        Lock lock = new ReentrantLock();

        Producer producer = new Producer(full,empty,lock,queue);
        Consumer consumer = new Consumer(full,empty,lock,queue);
        producer.start();
        consumer.start();

    }
    private static class Producer extends Thread{
        private Semaphore full,empty;
        private Lock lock;
        private Queue queue;
        public Producer(Semaphore full, Semaphore empty, Lock lock, Queue queue){
            this.full = full;
            this.empty = empty;
            this.lock = lock;
            this.queue = queue;
        }
        @Override
        public void run(){
            while (true){
                Object item = produce();
                try {
                    empty.acquire();
                    lock.lock();
                    System.out.println(item.toString()+" produced and added to queue");
                    queue.offer(item);
                    lock.unlock();
                    full.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        private Object produce(){
            return new Object();
        }
    }

    private static class Consumer extends Thread{
        private Semaphore full,empty;
        private Lock lock;
        private Queue queue;
        public Consumer(Semaphore full, Semaphore empty, Lock lock, Queue queue){
            this.full = full;
            this.empty = empty;
            this.lock = lock;
            this.queue = queue;
        }
        @Override
        public void run() {
            while (true){

                try {
                    full.acquire();
                    lock.lock();
                    Object item = queue.poll();
                    lock.unlock();
                    consume(item);
                    empty.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        private void consume(Object object) {
            System.out.println(object.toString()+" consumed");
        }
    }

}
