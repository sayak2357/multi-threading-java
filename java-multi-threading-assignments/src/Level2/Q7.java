package Level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q7 {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        Counter counter = new Counter();
        for(int i=0;i<10;i++){
            threads.add(new MyThread(counter));
        }
        for(Thread t:threads){
            t.start();
        }
        for(Thread t:threads){
            t.join();
        }
        System.out.println("value of counter: "+counter.getCount());
    }

    private static class Counter{
        private int count = 0;
        public void increment(){
            count++;
        }
        public int getCount(){
            return count;
        }
    }

    private static class MyThread extends Thread{
        private Counter counter;

        public MyThread(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run(){
            for(int i=0;i<1000000;i++){
                this.counter.increment();
            }
        }
    }
}
