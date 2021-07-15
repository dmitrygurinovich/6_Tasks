package by.epam.library.view;

public interface DataFromConsole {
    int getMenuItem(int min, int max, String message);
    int getNumFromConsole(String message, int min, int max);
    String getStringFromConsole(String message);
}
