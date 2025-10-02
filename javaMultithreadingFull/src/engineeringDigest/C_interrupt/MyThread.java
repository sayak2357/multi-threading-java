package engineeringDigest.C_interrupt;

public class MyThread extends Thread{
    @Override
    public void run() {

        try{
            Thread.sleep(1000);
            System.out.println("Thread is running ....");
        }catch (Exception e){
            System.out.println("Thread interrupted: "+e);
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();
        t1.interrupt();
    }
}
