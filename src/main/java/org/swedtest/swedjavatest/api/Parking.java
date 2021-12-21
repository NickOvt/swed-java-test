package org.swedtest.swedjavatest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.swedtest.swedjavatest.models.Car;
import org.swedtest.swedjavatest.services.CarParkingService;

@RequestMapping("api/v1/parking")
@RestController
public class Parking {

    private final CarParkingService carParkingService;

    @Autowired
    public Parking(CarParkingService carParkingService) {
        this.carParkingService = carParkingService;
    }

    @GetMapping
    public String hello() {
        return "Welcome to the parking API!";
    }

    @PostMapping
    public ResponseEntity<?> parkCar(@RequestBody Car car) {
        carParkingService.addCar(car);
        return ResponseEntity.ok("Parked car with plate number: " + car.getPlateNumber() + " and height of: " + car.getHeight() + " and weight of: " + car.getWeight());
    }

    // public void addCar(Car car) {
    //     carParkingService.addCar(car);
    // }
}
