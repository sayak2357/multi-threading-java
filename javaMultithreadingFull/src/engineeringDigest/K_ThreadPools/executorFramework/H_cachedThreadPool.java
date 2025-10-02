package engineeringDigest.K_ThreadPools.executorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class H_cachedThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        // there is not fixed amount of thread. It's dynamically adjusted
        // when tasks are short-lived and in variable number, this can be used
    }
}
