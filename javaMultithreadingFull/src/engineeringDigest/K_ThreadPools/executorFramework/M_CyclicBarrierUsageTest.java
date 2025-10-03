package engineeringDigest.K_ThreadPools.executorFramework;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class M_CyclicBarrierUsageTest {
    public static void main(String[] args) {
        int numOfSubsystems = 4;

        CyclicBarrier barrier = new CyclicBarrier(numOfSubsystems, new Runnable() {
            @Override
            public void run() {
                System.out.println("All subsystems are up & running. System startup complete.");
            }
        });

        Thread webServerThread = new Thread(new Subsystem("Web server",2000,barrier));
        Thread databaseThread = new Thread(new Subsystem("Database",4000,barrier));
        Thread cacheThread = new Thread(new Subsystem("Cache",3000,barrier));
        Thread messagingThread = new Thread(new Subsystem("Messaging service",3500,barrier));

        webServerThread.start();
        databaseThread.start();
        cacheThread.start();
        messagingThread.start();
    }
}

class Subsystem implements Runnable{

    private String name;
    private int initializationTime;
    private CyclicBarrier barrier;


    public Subsystem(String name, int initializationTime, CyclicBarrier barrier) {
        this.name = name;
        this.initializationTime = initializationTime;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try{
            System.out.println(name + " initialization started.");
            Thread.sleep(initializationTime);     // simulate initialization
            System.out.println(name+" initialization complete");
            barrier.await();
        }catch (InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
