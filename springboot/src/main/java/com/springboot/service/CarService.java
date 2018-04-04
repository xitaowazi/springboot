package com.springboot.service;

import com.springboot.domain.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarService {


    public List<Car> list(){
        List<Car> list = new ArrayList<>();
        Car car1 = new Car(2,"宝马",123456d,new Date());
        Car car2 = new Car(3,"奔驰",166666d,new Date());
        list.add(car1);
        list.add(car2);
        return list;
    }
}
