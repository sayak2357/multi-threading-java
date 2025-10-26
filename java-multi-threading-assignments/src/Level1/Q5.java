package Level1;

public class Q5 {
    public static void main(String[] args) throws InterruptedException {
        DaemonThread t1 = new DaemonThread();
        t1.setDaemon(true);
        t1.start();
        Thread.sleep(2000);
    }
    private static class DaemonThread extends Thread{
        @Override
        public void run(){
            while(true) {
                System.out.println("Daemon running");
            }
        }
    }
}
