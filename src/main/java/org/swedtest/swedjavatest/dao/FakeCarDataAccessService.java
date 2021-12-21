package org.swedtest.swedjavatest.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.swedtest.swedjavatest.models.Car;

@Repository("fakedao")
public class FakeCarDataAccessService implements CarDao{
    private static List<Car> DB = new ArrayList<>();

    @Override
    public int addCar(Car car) {
        DB.add(car);
        return 1;
    }
}
