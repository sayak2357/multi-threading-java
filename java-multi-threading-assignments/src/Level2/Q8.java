package Level2;

import java.util.ArrayList;
import java.util.List;

public class Q8 {
    public static void main(String[] args) throws InterruptedException {
        CounterSyncBlockMethod counter = new CounterSyncBlockMethod();
        List<Thread> threads = new ArrayList<>();
        for(int i=0;i<10;i++){
            threads.add(new MyThread(counter,false));
        }
        long start = System.currentTimeMillis();
        for(Thread t:threads){
            t.start();
        }
        for(Thread t:threads){
            t.join();
        }
        long end = System.currentTimeMillis();
        long durationForMethodSync = end-start;
        System.out.println("time taken for method level synchronization: "+durationForMethodSync);
        threads.clear();
        for(int i=0;i<10;i++){
            threads.add(new MyThread(counter,true));
        }
        start = System.currentTimeMillis();
        for(Thread t:threads){
            t.start();
        }
        for(Thread t:threads){
            t.join();
        }
        end = System.currentTimeMillis();
        durationForMethodSync = end-start;
        System.out.println("time taken for block level synchronization: "+durationForMethodSync);
    }

    private static class CounterSyncBlockMethod {
        private int count = 0;
        public synchronized void increment(){
            count++;
        }
        public void incrementBlock(){
            synchronized (this){
                count++;
            }
        }
        public  int getCount(){
            return count;
        }
    }
    private static class MyThread extends Thread{
        private CounterSyncBlockMethod counterSync;
        private boolean isBlock;

        public MyThread(CounterSyncBlockMethod counterSync, boolean block) {
            this.counterSync = counterSync;
            this.isBlock = block;
        }

        @Override
        public void run(){
            for(int i=0;i<1000000;i++){
                if(isBlock){
                    this.counterSync.incrementBlock();
                }
                else
                    this.counterSync.increment();
            }
        }
    }
}
