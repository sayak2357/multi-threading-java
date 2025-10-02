package G_locks;

public class D_MainReentrant {
    public static void main(String[] args) {
        C_BankAccountReentrantLock sbi = new C_BankAccountReentrantLock();

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
