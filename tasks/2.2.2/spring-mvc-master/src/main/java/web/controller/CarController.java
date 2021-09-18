package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.models.Car;
import web.service.CarService;

import java.util.List;


@Controller
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/cars")
    public String printWelcome(ModelMap model, @RequestParam(required = false) String count) {
        List<Car> cars = null;
        if (count != null) {
            cars = carService.getCars(Integer.parseInt(count));
        } else {
            cars = carService.getCars();
        }
        model.addAttribute("cars", cars);
        return "cars";
    }
}
