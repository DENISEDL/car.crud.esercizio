package com.example.crud.car.esempio.services;

import com.example.crud.car.esempio.entities.Car;
import com.example.crud.car.esempio.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository repository;

    public Car addcar(Car car){
        //salviamo l'oggetto e poi lo ritorniamo
        return repository.save(car);
    }
    public List<Car> getCar(){
        //ritorniamo tutta la lista degli oggetti
        return repository.findAll();
    }
    public Optional<Car> getCarId(Long id){
        //cerchiamo e poi ritorniamo l'oggetto tramite id
        return repository.findById(id);
    }
    public Optional<Car> updateCar(Car car,Long id){
        //recuperiamo l'oggetto da modificare tramite id
        Optional<Car> carOptional = repository.findById(id);
        //se l'oggetto è presente
        if(carOptional.isPresent()){
            //modifichiamo tutti i parametri dell'oggetto
            carOptional.get().setType(car.getType());
            //salviamo l'oggetto aggiornato
            Car carUpdate = repository.save(carOptional.get());
            //ritorniamo l'oggetto aggiornato
            return Optional.of(carUpdate);
        } else  {
            //se non presente torna un oggetto vuoto
            return Optional.empty();
        }

    }
    public Optional<Car> deleteCarById(Long id){
        //prendiamo l'oggetto da eliminare con id
        Optional<Car> carOptional=repository.findById(id);
        if(carOptional.isPresent()){
            //cancelliamo l'oggetto
            repository.delete(carOptional.get());
        }else{
            //se non presente torna un oggetto vuoto
            return Optional.empty();
        }
        //return oggetto eliminato
        return carOptional;
    }
}
