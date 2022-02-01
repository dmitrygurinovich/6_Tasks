package by.epam.port.bean;

public enum Size {
    SMALL(10),
    MEDIUM(25),
    LARGE(50);

    private final int value;

    Size(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase() + "(" + value + ")";
    }
}
