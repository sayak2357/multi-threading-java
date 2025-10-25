package udemyMichaelPogrebinsky.F_Synchronization;

public class A_SynchronizedKeyword {
    public static void main(String[] args) {
        InvenotoryCounter ic = new InvenotoryCounter();
        for(int i=0;i<1000;i++){
            ic.increment();
        }

        for(int i=0;i<1000;i++){
            ic.decrement();
        }
        System.out.println(ic.getItems());

    }

    private static class InvenotoryCounter{
        private int items = 0;
        private Object lock = new Object();
        public void increment(){
            synchronized (this.lock) {
                items++;
            }
        }
        public void decrement(){
            synchronized (this.lock) {
                items--;
            }
        }

        public int getItems() {
            return items;
        }
    }
}
