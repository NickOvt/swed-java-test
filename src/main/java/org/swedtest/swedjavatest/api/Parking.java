package org.swedtest.swedjavatest.api;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.swedtest.swedjavatest.models.Car;
import org.swedtest.swedjavatest.models.Floor;
import org.swedtest.swedjavatest.responseObjects.UnparkResponse;
import org.swedtest.swedjavatest.services.CarParkingService;

@RequestMapping("api/v1/parking")
@RestController
public class Parking {

    private final CarParkingService carParkingService;

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
    public ResponseEntity<Car> getSingleCar(@PathVariable("id") String plateNumber) {
        Car carToGet = carParkingService.getCarByPlateNumber(plateNumber);
        return ResponseEntity.ok(carToGet);
    }

    @PostMapping("/cars/park")
    public ResponseEntity<String> parkCar(@RequestBody Car car) {
        car.setParkingTimeStart(new Date(System.currentTimeMillis()));
        Floor parkedToFloor = carParkingService.parkCar(car);
        return ResponseEntity.ok("Parked car with plate number: " + car.getPlateNumber() + " and height of: "
                + car.getHeight() + " and weight of: " + car.getWeight() + ". On the floor with the nr: "
                + parkedToFloor.getFloorNumber());
    }

    @PostMapping("/cars/unpark/{id}")
    public ResponseEntity<UnparkResponse> unparkCar(@PathVariable("id") String plateNumber) {
        Car currCar = carParkingService.getCarByPlateNumber(plateNumber);
        Float parkingPrice = carParkingService.unparkCar(currCar);
        return ResponseEntity.ok(new UnparkResponse(plateNumber, parkingPrice));
    }

    @GetMapping("/floors/{id}")
    public ResponseEntity<Floor> singleFloor(@PathVariable("id") int floorNumber) {
        Floor floorToGet = carParkingService.getSingleFLoor(floorNumber);
        return ResponseEntity.ok(floorToGet);
    }

    @GetMapping("/floors")
    public List<Floor> listAllFloors() {
        return carParkingService.getAllFloors();
    }

    @GetMapping("/floors/{id}/platenumbers")
    public ResponseEntity<List<String>> listAllCarPlateNumbersOnSpecifiedFloor(@PathVariable("id") int floorId) {
        List<String> carPlateNumbersToGetFromFloor = carParkingService.getAllCarPlateNumbersFromFloor(floorId);
        return ResponseEntity.ok(carPlateNumbersToGetFromFloor);
    }

    @PostMapping("/initialize")
    public List<Floor> initializeParkingHouse() {
        return carParkingService.initializeParkingHouse();
    }

}
