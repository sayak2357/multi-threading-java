package Level1;

public class Q4 {
    public static void main(String[] args) throws InterruptedException {
        ThreadA t1 = new ThreadA();
        ThreadB t2 = new ThreadB();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("All workers done!");

    }

    private static class ThreadA extends Thread{
        @Override
        public void run(){
            try {
                this.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class ThreadB extends Thread{
        @Override
        public void run(){
            try {
                this.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
