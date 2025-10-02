package engineeringDigest.B_priority;

public class MyThread extends Thread{

    public MyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        for(int i=0;i<5;i++){
            String a = "";
            for(int j=0;j<10000;j++){
                a += "ab";
            }
            System.out.println(Thread.currentThread().getName()+" priority: "+Thread.currentThread().getPriority()+" count: "+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        MyThread low = new MyThread("Low priority");
        MyThread mid = new MyThread("Mid priority");
        MyThread high = new MyThread("High priority");
        low.setPriority(Thread.MIN_PRIORITY);
        mid.setPriority(Thread.NORM_PRIORITY);
        high.setPriority(Thread.MAX_PRIORITY);
        low.start();
        mid.start();
        high.start();

        // More noticeable in Single core CPU
    }
}
