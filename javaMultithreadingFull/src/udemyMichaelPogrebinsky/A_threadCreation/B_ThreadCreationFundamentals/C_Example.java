package udemyMichaelPogrebinsky.A_threadCreation.B_ThreadCreationFundamentals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class C_Example {
    public static final int MAX_PASSWORD=999;
    public static void main(String[] args) {
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));
        System.out.println("password is: "+vault.password);
        List<Thread> threads = new ArrayList<>();

        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceSthread());

        for(Thread thread:threads){
            thread.start();
        }
    }

    public static class Vault{
        private int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrect(int guess){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return this.password==guess;
        }
    }

    private static abstract class HackerThread extends Thread{
        protected Vault vault;

        public HackerThread(Vault vault){
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start(){
            System.out.println("Starting Thread: "+this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread{

        public AscendingHackerThread(Vault vault){
            super(vault);
        }

        @Override
        public void run(){
            for(int guess=0;guess<=MAX_PASSWORD;guess++){
                if(vault.isCorrect(guess)){
                    System.out.println(this.getName()+" guessed the correct password:"+guess);
                    System.exit(0);
                }
            }
        }

    }

    private static class DescendingHackerThread extends HackerThread{

        public DescendingHackerThread(Vault vault){
            super(vault);
        }

        @Override
        public void run(){
            for(int guess=MAX_PASSWORD;guess>=0;guess--){
                if(vault.isCorrect(guess)){
                    System.out.println(this.getName()+" guessed the correct password:"+guess);
                    System.exit(0);
                }
            }
        }

    }

    public static class PoliceSthread extends Thread{

        @Override
        public void run(){
            for(int i=20;i>0;i--){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(i);
            }
            System.out.println("Game over for you Hackers");
            System.exit(0);
        }
    }

}
