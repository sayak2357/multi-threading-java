package E_daemon;

public class MyThread extends Thread{
    @Override
    public void run() {

        while(true){
            System.out.println("running...");
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.setDaemon(true);
        t1.start();
        System.out.println("main thread done!");

        // daemon thread is background thread, JVM doesn't wait for daemon thread to finish
    }
}
