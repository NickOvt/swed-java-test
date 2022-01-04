package org.swedtest.swedjavatest.api;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.swedtest.swedjavatest.models.Car;
import org.swedtest.swedjavatest.models.Floor;
import org.swedtest.swedjavatest.models.UnparkResponse;
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

    @GetMapping("/cars")
    public List<Car> listAllCars() {
        return carParkingService.getAllCars();
    }

    @GetMapping("/floors")
    public List<Floor> listAllFloors() {
        return carParkingService.getAllFloors();
    }

    @GetMapping("/floors/{id}/platenumbers")
    public List<String> listAllCarPlateNumbersOnSpecifiedFloor(@PathVariable("id") int floorId) {
        return carParkingService.getAllCarPlateNumbersFromFloor(floorId);
    }

    @PostMapping("/initialize")
    public List<Floor> initializeParkingHouse() {
        return carParkingService.initializeParkingHouse();
    }

    @PostMapping("/cars/park")
    public ResponseEntity<?> parkCar(@RequestBody Car car) throws Exception {
        car.setParkingTimeStart(new Date(System.currentTimeMillis()));
        ResponseEntity<?> res;
        try {
            Floor parkedToFloor = carParkingService.parkCar(car);
            res = ResponseEntity.ok("Parked car with plate number: " + car.getPlateNumber() + " and height of: " + car.getHeight() + " and weight of: " + car.getWeight() + ". On the floor with the nr: " + parkedToFloor.getFloorNumber());
        } catch (Exception e) {
            res = ResponseEntity.status(400).body(e.getMessage());
        }
        return res;
    }

    @PostMapping("/cars/unpark/{id}")
    public ResponseEntity<?> unparkCar(@PathVariable("id") String plateNumber) throws Exception {
        Optional<Car> carToUnpark = carParkingService.getCarByPlateNumber(plateNumber);
        if (carToUnpark.isPresent()) {
            Car currCar = carToUnpark.get();
            try {
                Float parkingPrice = carParkingService.unparkCar(currCar);
                return ResponseEntity.ok(new UnparkResponse(plateNumber, parkingPrice));
            } catch (Exception e) {
                return ResponseEntity.status(400).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(400).body("No such car!");
        }
    }
    
}
