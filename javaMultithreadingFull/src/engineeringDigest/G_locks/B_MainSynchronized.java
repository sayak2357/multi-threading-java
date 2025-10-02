package engineeringDigest.G_locks;

public class B_MainSynchronized {
    public static void main(String[] args) {
        A_BankAccountSynchronized sbi = new A_BankAccountSynchronized();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                sbi.withdraw(50);
            }
        };

        Thread t1 = new Thread(task,"Thread 1");
        Thread t2 = new Thread(task,"Thread 2");

        t1.start();
        t2.start();
    }
}
