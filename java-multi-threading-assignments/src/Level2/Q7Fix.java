package Level2;

import java.util.ArrayList;
import java.util.List;

public class Q7Fix {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        CounterSync counterSync = new CounterSync();
        for(int i=0;i<10;i++){
            threads.add(new MyThread(counterSync));
        }
        for(Thread t:threads){
            t.start();
        }
        for(Thread t:threads){
            t.join();
        }
        System.out.println("value of counter: "+ counterSync.getCount());
    }
    private static class CounterSync {
        private int count = 0;
        public synchronized void increment(){
            count++;
        }
        public  int getCount(){
            return count;
        }
    }
    private static class MyThread extends Thread{
        private CounterSync counterSync;

        public MyThread(CounterSync counterSync) {
            this.counterSync = counterSync;
        }

        @Override
        public void run(){
            for(int i=0;i<1000000;i++){
                this.counterSync.increment();
            }
        }
    }
}
