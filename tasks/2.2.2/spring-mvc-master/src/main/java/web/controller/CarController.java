package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.models.Car;
import web.service.CarService;


import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class CarController {

    private CarService carService;

    public CarController(@Autowired CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/cars")
    public String printWelcome(ModelMap model, HttpServletRequest request) {
        String countParam = request.getParameter("count");
        List<Car> cars = countParam == null ? carService.getCars()
                                            : carService.getCars(Integer.parseInt(countParam));
        model.addAttribute("cars", cars);
        return "cars";
    }
}
