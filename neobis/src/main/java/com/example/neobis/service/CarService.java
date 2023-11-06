package com.example.neobis.service;

import com.example.neobis.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Car save(Car car);
    Optional<Car> getById(Long id);
    Car update(Car car);
    void deleteCar(Long id);
    List<Car> getAllCars();
}
