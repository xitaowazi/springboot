package com.springboot.controller;

import com.springboot.domain.Car;
import com.springboot.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/carlist",method = RequestMethod.GET)
    public ResponseEntity<?> list(){
        List<Car> cars = carService.list();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

}
