package by.epam.port.main;

import by.epam.port.generator.ShipGenerator;

/*
Одна очередь
Корабли заходят в порт и разгружаются/загружаются:
    - если нет места для разгрузки одного корабля - корабль ожидает пока другие загрузятся и освободиться место в порту;
    - если нет грузов для загрузки корабля - корабль ожидает, пока другие разгрузятся;
        - корабли в Water Area хранятся в ArrayList, с первого элемента начинается обработка, если разгрузка/загрузка
        идет по плану - корабль удаляется из ArrayList, если нет - выполняются доп. дейсвия и обработка начинается сначала.
    - если все крабли стоят на загрзку и нет грузов - организовать подвоз грузов;
    - если порт загружен и все корабли ждут разгрузки - организовать вывоз грузов из порта;
т.е. организовать проверку на свободное место в порту при разгрузке и наличие грузов при загрузке.
*/
public class Main {
    public static void main(String[] args) {
        ShipGenerator shipGenerator = new ShipGenerator(10);
        Thread thread = new Thread(shipGenerator);

        thread.start();
    }
}
