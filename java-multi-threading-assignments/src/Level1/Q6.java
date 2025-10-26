package Level1;

public class Q6 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.printf(Thread.currentThread().getState()+"->");
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        System.out.printf(thread.getState()+"->");
        thread.start();
        Thread.sleep(2000);
        System.out.printf(thread.getState()+"->");
        thread.join();
        System.out.printf(thread.getState().toString());
    }


}
