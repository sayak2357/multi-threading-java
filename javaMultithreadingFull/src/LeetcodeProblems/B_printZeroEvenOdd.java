package LeetcodeProblems;

import java.util.concurrent.Semaphore;


// https://leetcode.com/problems/print-zero-even-odd/
public class B_printZeroEvenOdd {
    class ZeroEvenOdd {
        private int n;
        private int lastPrinted;
        private Semaphore zeroSem,oddSem,evenSem;
        public ZeroEvenOdd(int n) {
            this.n = n;
            this.lastPrinted = 0;
            this.zeroSem = new Semaphore(1);
            this.oddSem = new Semaphore(1);
            this.evenSem = new Semaphore(1);
            try{
                this.oddSem.acquire();
                this.evenSem.acquire();
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            int numZero = n;
            while(numZero-->0){
                zeroSem.acquire();
                printNumber.accept(0);
                if(lastPrinted%2==1){
                    evenSem.release();
                }else{
                    oddSem.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            int numEven = n/2;
            while(numEven-->0){
                evenSem.acquire();
                printNumber.accept(++lastPrinted);
                zeroSem.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            int numOdd = n - n/2;
            while(numOdd-->0){
                oddSem.acquire();
                printNumber.accept(++lastPrinted);
                zeroSem.release();
            }
        }
    }
}
