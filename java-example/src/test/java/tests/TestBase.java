package tests;

import app.Application;
import com.google.common.collect.Ordering;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;

public class TestBase {

    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public static Application app;

    @BeforeAll
    static void start() {

        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    app.quit();
                    app = null;
                })
        );
    }

    public static boolean isListOrdered(ArrayList<String> arr) {
        return Ordering.natural().isOrdered(arr);
    }
}
