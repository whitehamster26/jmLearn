package web.dao;

import org.springframework.stereotype.Repository;
import web.models.Car;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CarDaoImpl implements CarDao {
    private List<Car> cars;

    public CarDaoImpl() {
        cars = new LinkedList<>();
        cars.add(new Car(0, "Honda civic 6", (short) 1998));
        cars.add(new Car(1, "Honda CR-V", (short) 2002));
        cars.add(new Car(2, "Honda accord", (short) 2006));
        cars.add(new Car(3, "Nissan skyline", (short) 2002));
        cars.add(new Car(4, "Toyota supra", (short) 1990));
    }

    @Override
    public List<Car> getCars() {
        return cars;
    }

    @Override
    public List<Car> getCars(int count) {
        return cars.stream().limit(count).collect(Collectors.toList());
    }
}