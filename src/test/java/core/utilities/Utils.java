package core.utilities;

public class Utils {

    public static void sleep(int miliSeconds) {
        try {
            Thread.sleep(miliSeconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String camelToSentence(String string) {
        String sentence = string.replaceAll("([a-z])([A-Z])", "$1 $2");
        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
    }

    public static void println(String log) {
        System.out.println(log);
    }

    public static void printWarning(String log) {
        println("\u001B[33m" + log + "\u001B[0m");
    }
}
