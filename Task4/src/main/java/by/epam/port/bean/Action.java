package by.epam.port.bean;

public enum Action {
    LOADING,
    UNLOADING;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
