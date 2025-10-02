package udemyMichaelPogrebinsky.A_threadCreation;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("new thread: "+Thread.currentThread().getName());
                throw new RuntimeException("Intentional Exception");
            }
        });
        thread.setName("misbehaving thread");
        System.out.println("currently in thread: "+Thread.currentThread().getName());

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error occurred in thread: "+t.getName()+ " the error is "+e.getMessage());
            }
        });
        thread.start();
        System.out.println("currently in thread: "+Thread.currentThread().getName());
    }
}
