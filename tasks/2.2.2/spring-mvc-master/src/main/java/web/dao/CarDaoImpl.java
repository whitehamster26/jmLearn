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

    {
        cars = new LinkedList<>();
        Map<String, Short> carsToAdd = new HashMap<>();
        carsToAdd.put("Honda civic 6", (short) 1998);
        carsToAdd.put("Honda CR-V", (short) 2002);
        carsToAdd.put("Honda accord", (short) 2006);
        carsToAdd.put("Nissan skyline", (short) 2002);
        carsToAdd.put("Toyota supra", (short) 1990);
        int lastId = 0;
        for (Map.Entry<String, Short> carToAdd: carsToAdd.entrySet()) {
            cars.add(new Car(++lastId, carToAdd.getKey(), carToAdd.getValue()));
        }
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