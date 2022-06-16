package by.epam.port.bean;

/**
 * Причал. Всего таких причалов в порту 5. В каждом может находиться не более одного {@link by.epam.port.bean.Ship корабля}.
 */
public class Pier implements Runnable {
    private final WaterArea waterArea = WaterArea.getInstance();

    @Override
    public void run() {



    }
}
