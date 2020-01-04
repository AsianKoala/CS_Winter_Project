package Util;

public class ScreenHandler {
    public static int getCurrentScreen() {
        return currentScreen;
    }

    private static int currentScreen = 0;

    public static void nextScreen() {
        currentScreen++;
    }
}
