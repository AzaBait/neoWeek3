package com.example.neobis.service;

import com.example.neobis.entity.Car;
import com.example.neobis.repository.CarRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepo carRepo;
    @Override
    public Car save(Car car) {
        return carRepo.save(car);
    }

    @Override
    public Optional<Car> getById(Long id) {
        return carRepo.findById(id);
    }

    @Override
    public Car update(Car car) {
        return carRepo.save(car);
    }

    @Override
    public void deleteCar(Long id) {
        carRepo.deleteById(id);

    }

    @Override
    public List<Car> getAllCars() {
        return carRepo.findAll();
    }
}
