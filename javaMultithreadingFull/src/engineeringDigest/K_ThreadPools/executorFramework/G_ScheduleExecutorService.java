package engineeringDigest.K_ThreadPools.executorFramework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class G_ScheduleExecutorService {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(
                ()-> System.out.println("Task executed after 5 secs delay!"),
                5,
                5,
                TimeUnit.SECONDS
        );
        // event if task is not completed within period, new task will be scheduled


        scheduler.scheduleWithFixedDelay(
                ()-> System.out.println("Task executed after 5 secs delay!"),
                5,
                5,
                TimeUnit.SECONDS
        );
        // scheduled with fixedDelay waits for delay time after completion of task
        scheduler.schedule( () ->{
            System.out.println("Initiating shutdown...");
            scheduler.shutdown();
        },20, TimeUnit.SECONDS);   // forces the scheduler to shutdown after 20 sec ie 4 times task is executed as each task repeats after 5 secs

    }
}
