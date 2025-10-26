package Level1;

public class Q2 {
    public static void main(String[] args) {
        Thread low = new MyThread();
        Thread mid = new MyThread();
        Thread high = new MyThread();
        low.setName("Alpha");
        low.setPriority(1);

        mid.setName("Beta");
        mid.setPriority(5);

        high.setName("Gamma");
        high.setPriority(10);

        low.start();
        mid.start();
        high.start();
    }
    private static class MyThread extends Thread{
        @Override
        public void run(){

            System.out.println(this.getName()+" priority="+this.getPriority());
        }
    }
}
