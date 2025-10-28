package Level2;

public class Q10 {
    public static void main(String[] args) {
        Object resourceA = new Object();
        Object resourceB = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("Thread 1: locked resource 1");

                // Simulate some work
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Thread 1: waiting to lock resource 2...");
                synchronized (resourceB) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (resourceB) {
                System.out.println("Thread 2: locked resource 2");

                // Simulate some work
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Thread 2: waiting to lock resource 1...");
                synchronized (resourceA) {
                    System.out.println("Thread 2: locked resource 1");
                }
            }
        });

        t1.start();
        t2.start();

    }

}
