package engineeringDigest.C_interrupt;

public class MyThread extends Thread{
    @Override
    public void run() {

        try{
            Thread.sleep(1000);
            System.out.println("Thread is running ....");
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println("Thread interrupted: "+e);
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t1.interrupt();
    }
}
