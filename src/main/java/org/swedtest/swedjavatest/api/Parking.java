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

    @GetMapping("cars/{id}")
    public ResponseEntity<?> getSingleCar(@PathVariable("id") String plateNumber) {
        try {
            Car carToGet = carParkingService.getCarByPlateNumber(plateNumber);
            return ResponseEntity.ok(carToGet);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/cars/park")
    public ResponseEntity<?> parkCar(@RequestBody Car car) throws Exception {
        car.setParkingTimeStart(new Date(System.currentTimeMillis()));
        try {
            Floor parkedToFloor = carParkingService.parkCar(car);
            return ResponseEntity.ok("Parked car with plate number: " + car.getPlateNumber() + " and height of: "
                    + car.getHeight() + " and weight of: " + car.getWeight() + ". On the floor with the nr: "
                    + parkedToFloor.getFloorNumber());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/cars/unpark/{id}")
    public ResponseEntity<?> unparkCar(@PathVariable("id") String plateNumber) throws Exception {
        try {
            Car currCar = carParkingService.getCarByPlateNumber(plateNumber);
            Float parkingPrice = carParkingService.unparkCar(currCar);
            return ResponseEntity.ok(new UnparkResponse(plateNumber, parkingPrice));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/floors/{id}")
    public ResponseEntity<?> singleFloor(@PathVariable("id") int floorNumber) {
        try {
            Floor floorToGet = carParkingService.getSingleFLoor(floorNumber);
            return ResponseEntity.ok(floorToGet);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/floors")
    public List<Floor> listAllFloors() {
        return carParkingService.getAllFloors();
    }

    @GetMapping("/floors/{id}/platenumbers")
    public ResponseEntity<?> listAllCarPlateNumbersOnSpecifiedFloor(@PathVariable("id") int floorId) {
        try {
            List<String> carPlateNumbersToGetFromFloor = carParkingService.getAllCarPlateNumbersFromFloor(floorId);
            return ResponseEntity.ok(carPlateNumbersToGetFromFloor);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/initialize")
    public List<Floor> initializeParkingHouse() {
        return carParkingService.initializeParkingHouse();
    }

}
