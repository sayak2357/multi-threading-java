package Level1;

public class Q1 {
    public static void main(String[] args) {
        Thread thread1 = new MyThread();
        MyThread2 t2 = new MyThread2();
        Thread thread2 = new Thread(t2);
        thread1.start();
        thread2.start();
    }
    private static class MyThread extends Thread{

        @Override
        public void run(){
            for(int i=1;i<=5;i++){
                System.out.println("current thread: "+this.getName());
                System.out.println(i);
                try {
                    this.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static class MyThread2 implements Runnable{

        @Override
        public void run() {
            for(int i=1;i<=5;i++){
                System.out.println("current thread: "+Thread.currentThread().getName());
                System.out.println(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
