package web.dao;

import web.models.Car;

import java.util.List;

public interface CarDao {
    List<Car> getCars();
    List<Car> getCars(int count);
}
