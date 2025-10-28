package Level2;

public class Q9Counter {
    private static int count=0;

    public static synchronized void increment(){
        count++;
    }

    public int getCount(){
        return count;
    }
}
