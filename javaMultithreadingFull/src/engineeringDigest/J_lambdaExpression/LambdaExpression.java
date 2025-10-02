package engineeringDigest.J_lambdaExpression;

public class LambdaExpression {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("Hello");
        };

        Thread t1 = new Thread(runnable);
        t1.start();
    }
}
