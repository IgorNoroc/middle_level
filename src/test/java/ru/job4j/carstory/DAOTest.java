package ru.job4j.carstory;


import org.junit.Test;
import ru.job4j.carstory.model.Car;
import ru.job4j.carstory.model.Driver;
import ru.job4j.carstory.model.Engine;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;


public class DAOTest  {
     private final DAO dao = DAO.instOf();

     @Test
     public void whenCreateCar() {
          Engine engine = new Engine("test1");
          Car car = new Car("carTest1", engine);
          dao.createEngine(engine);
          Car result = dao.createCar(car);
          Car expected = new Car("carTest1", engine);
          expected.setId(result.getId());
          assertThat(result, is(expected));
     }

     @Test
     public void whenCreateDriver() {
          Driver driver = new Driver("testD2");
          Driver result = dao.createDriver(driver);
          Driver expected = new Driver("testD2");
          expected.setId(result.getId());
          assertThat(result, is(expected));
     }

     @Test
     public void whenCreateEngine() {
          Engine engine = new Engine("type1");
          Engine result = dao.createEngine(engine);
          Engine expected = new Engine("type1");
          expected.setId(result.getId());
          assertThat(result, is(expected));
     }

     @Test
     public void whenAddHistory() {
          Engine engine = new Engine("type2");
          Car car = new Car("carTest2", engine);
          Driver driver = new Driver("driver3");
          Engine resultEngine = dao.createEngine(engine);
          Car resultCar = dao.createCar(car);
          Driver resultDriver = dao.createDriver(driver);
          dao.addHistory(car, driver);
          Set<Car> result = dao.findDriverById(resultDriver.getId()).getCars();
          Car expectedCar = new Car("carTest2", resultEngine);
          expectedCar.setId(resultCar.getId());
          Set<Car> expected = Set.of(expectedCar);
          assertThat(result, is(expected));
     }

     @Test
     public void whenUpdateCar() {
          Engine engine = new Engine("type4");
          Car car = new Car("carTest4", engine);
          Engine resultEngine = dao.createEngine(engine);
          Car resultCar = dao.createCar(car);
          Car updateCar = new Car("carTest5", resultEngine);
          updateCar.setId(resultCar.getId());
          dao.update(resultCar.getId(), updateCar);
          Car result = dao.findCarById(resultCar.getId());
          Car expected = new Car("carTest5", resultEngine);
          expected.setId(resultCar.getId());
          assertThat(result, is(expected));
     }

     @Test
     public void whenUpdateEngine() {
          Engine engine = new Engine("type5");
          Engine resultEngine = dao.createEngine(engine);
          Engine updateEngine = new Engine("type6");
          updateEngine.setId(resultEngine.getId());
          dao.update(resultEngine.getId(), updateEngine);
          Engine result = dao.findEngineById(resultEngine.getId());
          Engine expected = new Engine("type6");
          expected.setId(resultEngine.getId());
          assertThat(result, is(expected));
     }

     @Test
     public void whenUpdateDrive() {
          Driver driver = new Driver("driver7");
          Driver addedDriver = dao.createDriver(driver);
          Driver updateDriver = new Driver("driver8");
          updateDriver.setId(addedDriver.getId());
          dao.update(addedDriver.getId(), updateDriver);
          Driver result = dao.findDriverById(addedDriver.getId());
          Driver expected = new Driver("driver8");
          expected.setId(addedDriver.getId());
          assertThat(result, is(expected));
     }

     @Test
     public void whenDeleteCar() {
          Engine engine = new Engine("test9");
          Car car = new Car("carTest10", engine);
          dao.createEngine(engine);
          Car result = dao.createCar(car);
          dao.deleteCar(result);
          assertNull(dao.findCarById(result.getId()));
     }

     @Test
     public void whenDeleteEngine() {
          Engine engine = new Engine("test11");
          Engine addedEngine = dao.createEngine(engine);
          dao.deleteEngine(addedEngine);
          assertNull(dao.findEngineById(addedEngine.getId()));
     }

     @Test
     public void whenDeleteDriver() {
          Driver driver = new Driver("driver12");
          Driver addedDriver = dao.createDriver(driver);
          dao.deleteDriver(addedDriver);
          assertNull(dao.findDriverById(addedDriver.getId()));
     }

     @Test
     public void whenGetAllCars() {
          Engine engine = new Engine("type13");
          Car carFirst = new Car("car1", engine);
          Car carSecond = new Car("car2", engine);
          dao.createEngine(engine);
          dao.createCar(carFirst);
          dao.createCar(carSecond);
          assertThat(dao.findAllCars().size(), greaterThanOrEqualTo(2));
     }

     @Test
     public void whenGetAllDrivers() {
          Driver driverFirst = new Driver("driver1");
          Driver driverSecond = new Driver("driver2");
          dao.createDriver(driverFirst);
          dao.createDriver(driverSecond);
          assertThat(dao.findAllDrivers().size(), greaterThanOrEqualTo(2));
     }

     @Test
     public void whenGetAllEngines() {
          Engine engineFirst = new Engine("en1");
          Engine engineSecond = new Engine("en2");
          dao.createEngine(engineFirst);
          dao.createEngine(engineSecond);
          assertThat(dao.findAllEngines().size(), greaterThanOrEqualTo(2));
     }
}