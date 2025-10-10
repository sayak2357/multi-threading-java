package LeetcodeProblems;


import java.util.concurrent.CountDownLatch;

// https://leetcode.com/problems/print-foobar-alternately/description/?envType=problem-list-v2&envId=concurrency
public class A_printFooBarAlternatively {
    class FooBar {
        private int n;
        private CountDownLatch[] latchAfterFoo;
        private CountDownLatch[] latchAfterBar;
        public FooBar(int n) {
            this.n = n;
            this.latchAfterFoo = new CountDownLatch[n];
            this.latchAfterBar = new CountDownLatch[n];
            for(int i=0;i<n;i++){
                this.latchAfterFoo[i] = new CountDownLatch(1);
                this.latchAfterBar[i] = new CountDownLatch(1);
            }
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {

                // printFoo.run() outputs "foo". Do not change or remove this line.
                if(i>0){
                    latchAfterBar[i-1].await();
                }
                printFoo.run();
                latchAfterFoo[i].countDown();


            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {

                // printBar.run() outputs "bar". Do not change or remove this line.
                latchAfterFoo[i].await();
                printBar.run();
                latchAfterBar[i].countDown();

            }
        }
    }
}
