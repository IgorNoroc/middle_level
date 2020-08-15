package ru.job4j.carstory;

import ru.job4j.carstory.model.Car;
import ru.job4j.carstory.model.Driver;

public class DaoT {
    public static void main(String[] args) {
        DAO dao =  DAO.instOf();
        Driver driver = dao.findDriverById(13);
        driver.getCars().add(dao.findCarById(2));
    }
}
