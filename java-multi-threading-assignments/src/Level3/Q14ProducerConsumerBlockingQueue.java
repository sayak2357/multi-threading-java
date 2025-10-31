package Level3;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Q14ProducerConsumerBlockingQueue {

    public static void main(String[] args) {
        // Shared queue with capacity = 5
        BlockingQueue<Integer> buffer = new LinkedBlockingQueue<>(5);

        Thread producer1 = new Thread(new Producer(buffer), "Producer-1");
        Thread producer2 = new Thread(new Producer(buffer), "Producer-2");
        Thread consumer1 = new Thread(new Consumer(buffer), "Consumer-1");
        Thread consumer2 = new Thread(new Consumer(buffer), "Consumer-2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }

    // Producer thread
    private static class Producer implements Runnable {
        private final BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            int value = 0;
            try {
                while (true) {
                    queue.put(value); // blocks if queue is full
                    System.out.println(Thread.currentThread().getName() + " produced: " + value);
                    value++;
                    Thread.sleep(300); // simulate production delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Consumer thread
    private static class Consumer implements Runnable {
        private final BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int value = queue.take(); // blocks if queue is empty
                    System.out.println(Thread.currentThread().getName() + " consumed: " + value);
                    Thread.sleep(500); // simulate consumption delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


}

