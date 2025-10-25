package udemyMichaelPogrebinsky.B_ThreadCreationFundamentals;

public class A_Intro {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("current thread inside new thread: "+Thread.currentThread().getName());
            }
        });
        thread.setName("New Worker Thread");
        thread.setPriority(Thread.MAX_PRIORITY);
        System.out.println("current thread before scheduling new thread: "+Thread.currentThread().getName());
        thread.start();
        System.out.println("current thread after scheduling new thread: "+Thread.currentThread().getName());
        Thread.sleep(1000);
    }
}
