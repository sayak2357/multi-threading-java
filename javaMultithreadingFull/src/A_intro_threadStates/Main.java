package A_intro_threadStates;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        OddPrinter oddPrinter = new OddPrinter();
        System.out.println("state of oddPrinter thread: "+oddPrinter.getState());
        oddPrinter.start();
        System.out.println("state of oddPrinter thread: "+oddPrinter.getState());
        System.out.println("state of main thread: "+Thread.currentThread().getState());
        for(int i=0;i<2000;i+=2){
            System.out.println(i);
        }
        System.out.println("state of main thread: "+Thread.currentThread().getState());
        oddPrinter.join();
        System.out.println("state of oddPrinter thread: "+oddPrinter.getState());
        System.out.println("done");
    }
}