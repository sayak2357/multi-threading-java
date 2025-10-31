package Level3;

import java.util.LinkedList;
import java.util.List;

public class Q13ProducerConsumerExample {

    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer(5); // buffer size = 5

        Thread producer1 = new Thread(new Producer(buffer), "Producer-1");
        Thread producer2 = new Thread(new Producer(buffer), "Producer-2");
        Thread consumer1 = new Thread(new Consumer(buffer), "Consumer-1");
        Thread consumer2 = new Thread(new Consumer(buffer), "Consumer-2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}

// Shared buffer class
class SharedBuffer {
    private final List<Integer> list = new LinkedList<>();
    private final int capacity;

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int value) throws InterruptedException {
        // Wait if buffer is full
        while (list.size() == capacity) { // spurious wake-up protection
            System.out.println(Thread.currentThread().getName() + " waiting — buffer is full");
            wait();
        }

        list.add(value);
        System.out.println(Thread.currentThread().getName() + " produced: " + value);

        // Notify all waiting threads (consumers)
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        // Wait if buffer is empty
        while (list.isEmpty()) {  // spurious wake-up protection
            System.out.println(Thread.currentThread().getName() + " waiting — buffer is empty");
            wait();
        }

        int value = list.remove(0);
        System.out.println(Thread.currentThread().getName() + " consumed: " + value);

        // Notify all waiting threads (producers)
        notifyAll();
        return value;
    }
}

// Producer thread
class Producer implements Runnable {
    private final SharedBuffer buffer;

    public Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int value = 0;
        try {
            while (true) {
                buffer.produce(value++);
                Thread.sleep(300); // simulate production delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Consumer thread
class Consumer implements Runnable {
    private final SharedBuffer buffer;

    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                buffer.consume();
                Thread.sleep(500); // simulate consumption delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

