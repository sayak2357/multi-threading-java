package udemyMichaelPogrebinsky.A_threadCreation.B_ThreadCreationFundamentals;

public class B_UsingThread {
    public static void main(String[] args) {
        Thread thread = new NewThread();
        thread.start();
    }
    public static class NewThread extends Thread{

        @Override
        public void run(){
//            System.out.println("Hello from: "+Thread.currentThread().getName());
            System.out.println("Hello from: "+this.getName());
        }
    }
}

