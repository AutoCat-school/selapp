package core.utilities;

public class Utils {

    public static void sleep(int miliSeconds) {
        try {
            Thread.sleep(miliSeconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
