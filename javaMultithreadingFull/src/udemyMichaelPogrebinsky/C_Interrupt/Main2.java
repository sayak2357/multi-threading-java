package udemyMichaelPogrebinsky.C_Interrupt;

import java.math.BigInteger;



public class Main2 {
    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputation(new BigInteger(String.valueOf(2000000)),new BigInteger(String.valueOf(103241327))));
        thread.start();
        thread.interrupt();
    }

    private static class LongComputation implements Runnable{
        private BigInteger base;
        private BigInteger power;

        public LongComputation(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        private BigInteger power(BigInteger base, BigInteger power){
            BigInteger result = BigInteger.ONE;
            for(BigInteger i= BigInteger.ZERO;i.compareTo(power)!=0; i= i.add(BigInteger.ONE)){
                if(Thread.currentThread().isInterrupted()){                            // without this code block, the thread will keep on running
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        }
        @Override
        public void run() {
            System.out.println(base+"^"+power+" = "+power(base,power));
        }
    }
}
