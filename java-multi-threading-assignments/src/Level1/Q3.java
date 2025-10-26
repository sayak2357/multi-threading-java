package Level1;

public class Q3 {
    public static void main(String[] args) {
        ThreadA t1 = new ThreadA();
        ThreadB t2 = new ThreadB();

        t1.start();
        t2.start();
    }
    private static class ThreadA extends Thread{
        @Override
        public void run(){
            while(true){
                try {
                    System.out.println("A");
                    this.sleep(300);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    private static class ThreadB extends Thread{
        @Override
        public void run(){
            while(true){

                System.out.println("B");
                this.yield();

            }
        }
    }
}
