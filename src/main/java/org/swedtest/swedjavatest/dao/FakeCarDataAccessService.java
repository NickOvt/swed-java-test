package org.swedtest.swedjavatest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.swedtest.swedjavatest.models.Car;

@Repository()
public class FakeCarDataAccessService implements CarDao {
    private static List<Car> DB = new ArrayList<>();

    @Override
    public boolean addCar(Car car) {
        // Check if that car already exists (by its plate number)
        for (int i = 0; i < DB.size(); i++) {
            Car currCarFromDB = DB.get(i);
            if (currCarFromDB.getPlateNumber().equals(car.getPlateNumber())) {
                return false;
            }
        }
        if (DB.add(car)) {
            return true;
        } else {
            throw new RuntimeException("Error adding car to DB");
        }
    }

    @Override
    public void deleteCar(Car car) {
        Optional<Car> carToDelete = getCarByPlateNumber(car.getPlateNumber());
        if (carToDelete.isPresent()) {
            if (!DB.remove(carToDelete.get())) {
                throw new RuntimeException("Error removing car from DB");
            }
        }
    }

    @Override
    public List<Car> getAllCars() {
        return DB;
    }

    @Override
    public Optional<Car> getCarByPlateNumber(String plateNumber) {
        return DB.stream().filter(car -> car.getPlateNumber().equals(plateNumber)).findFirst();
    }
}
