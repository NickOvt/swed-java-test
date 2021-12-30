package org.swedtest.swedjavatest.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.swedtest.swedjavatest.models.Car;

@Repository("fakedao")
public class FakeCarDataAccessService implements CarDao{
    private static List<Car> DB = new ArrayList<>();

    @Override
    public boolean addCar(Car car) throws Exception {
        if (DB.contains(car)) {
            return false;
        }
        try {
            DB.add(car);
            return true;
        } catch (Exception e) {
            throw new Exception("Error adding car to DB");
        }
    }

    @Override
    public List<Car> getAllCars() {
        return DB;
    }
}
