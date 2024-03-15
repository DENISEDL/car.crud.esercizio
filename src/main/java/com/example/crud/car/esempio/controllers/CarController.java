package com.example.crud.car.esempio.controllers;


import com.example.crud.car.esempio.entities.Car;
import com.example.crud.car.esempio.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/createCar")
    public ResponseEntity<Car> creatCar(@RequestBody Car car){
        return ResponseEntity.ok().body(carService.addcar(car));
    }
    @GetMapping("/getListaCar")
    public ResponseEntity<List<Car>> getAllCar(){
        return  ResponseEntity.ok().body(carService.getCar());
    }
    @GetMapping("/cercaCarId/{id}")
    public ResponseEntity<Car> getCarId(@PathVariable Long id){
        Optional<Car> carOptional = carService.getCarId(id);
        if(carOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(carOptional.get());
    }
    @PutMapping("/updateCar/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id,@RequestBody Car car){
        Optional<Car> carOptional=carService.updateCar(car,id);
        if(carOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(carOptional.get());
    }
    @DeleteMapping("/deleteCar/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id){
        Optional<Car> carOptional=carService.deleteCarById(id);
        if(carOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(carOptional.get());
    }

}