package udemyMichaelPogrebinsky.D_Join;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Long> inutNumbers = Arrays.asList(0L,23L,43332L,4264L,2222L,3885L,6755L);
        List<FactorialThread> factorialThreads = new ArrayList<>();
        for(long num:inutNumbers){
            factorialThreads.add(new FactorialThread(num));
        }
        for(Thread thread:factorialThreads){
            thread.start();
        }
        for(Thread thread:factorialThreads){
            thread.join(2000);
        }
        for(int i=0;i<inutNumbers.size();i++){
            FactorialThread factorialThread = factorialThreads.get(i);
            if(factorialThread.isFinished()){
                System.out.println("factorial of: "+inutNumbers.get(i)+" = "+factorialThread.getResult());
            }
            else{
                System.out.println("factorial of: "+inutNumbers.get(i)+" "+ " is stll being processed");
            }
        }
    }


    public static class FactorialThread extends Thread{
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run(){
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }
        public BigInteger factorial(long n){
            BigInteger tempResult = BigInteger.ONE;
            for(long i=n;i>=1;i--){
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public BigInteger getResult() {
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }
    }
}
