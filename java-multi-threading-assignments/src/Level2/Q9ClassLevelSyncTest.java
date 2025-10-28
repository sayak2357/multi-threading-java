package Level2;

public class Q9ClassLevelSyncTest {

    // Static synchronized method — class-level lock
    public static synchronized void staticSyncMethod(String name) {
        System.out.println(name + " entered staticSyncMethod");
        try {
            Thread.sleep(2000); // Simulate long operation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " leaving staticSyncMethod");
    }

    // Non-static synchronized method — instance-level lock
    public synchronized void instanceSyncMethod(String name) {
        System.out.println(name + " entered instanceSyncMethod");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " leaving instanceSyncMethod");
    }

    public static void main(String[] args) {
        Q9ClassLevelSyncTest obj1 = new Q9ClassLevelSyncTest();
        Q9ClassLevelSyncTest obj2 = new Q9ClassLevelSyncTest();

        // Thread A calls static synchronized method (locks on Class)
        Thread t1 = new Thread(() -> {
            Q9ClassLevelSyncTest.staticSyncMethod("Thread-A");
        });

        // Thread B calls static synchronized method on another instance (same Class lock!)
        Thread t2 = new Thread(() -> {
            Q9ClassLevelSyncTest.staticSyncMethod("Thread-B");
        });

        // Thread C calls instance synchronized method on a separate object (different lock)
        Thread t3 = new Thread(() -> {
            obj2.instanceSyncMethod("Thread-C");
        });

        t1.start();
        t2.start();
        t3.start();
    }
}

