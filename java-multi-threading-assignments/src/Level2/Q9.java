package Level2;

import java.util.ArrayList;
import java.util.List;

public class Q9 {
    public static void main(String[] args) throws InterruptedException {
        Q9Counter counter = new Q9Counter();
        Q9Counter counter1 = new Q9Counter();

        List<Thread> threads = new ArrayList<>();
        for(int i=0;i<5;i++){
            threads.add(new MyThread(counter));
            threads.add(new MyThread(counter1));
        }
        for(Thread t:threads){
            t.start();
        }
        for(Thread t:threads){
            t.join();
        }
        System.out.println("counter: "+counter.getCount());
        System.out.println("counter1: "+counter1.getCount());
    }

    private static class MyThread extends Thread{
        private Q9Counter counter;

        public MyThread(Q9Counter counter) {
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
