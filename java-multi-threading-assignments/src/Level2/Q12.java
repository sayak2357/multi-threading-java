package Level2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Q12 {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> sharedList = new ArrayList<>();
        UnsafeThread t1 = new UnsafeThread(sharedList);
        UnsafeThread t2 = new UnsafeThread(sharedList);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("size of list after running 2 threads with unsafe collection: "+sharedList.size());
        sharedList.clear();
        List<Integer> syncList = Collections.synchronizedList(sharedList);
        UnsafeThread t3 = new UnsafeThread(syncList);
        UnsafeThread t4 = new UnsafeThread(syncList);

        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println("size of list after running 2 threads with thread safe collection: "+sharedList.size());

    }
    private static class UnsafeThread extends Thread{
        private List<Integer> list;
        public UnsafeThread(List<Integer> list){
            this.list = list;
        }

        @Override
        public void run(){
            for(int i=0;i<100000;i++){
                this.list.add(i);
            }
        }
    }
}
