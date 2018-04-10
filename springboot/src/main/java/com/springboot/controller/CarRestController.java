package com.springboot.controller;

import com.springboot.domain.Car;
import com.springboot.domain.CustomType;
import com.springboot.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:8080",maxAge = 3600)
@RequestMapping("/api")
public class CarRestController {
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public ResponseEntity<?> list(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "price",required = false) Double price){
        System.out.println("接收的值："+"name:"+name+""+"price:"+price);
        List<Car> cars = carService.find(name, price);
        if(cars.isEmpty()){
            return new ResponseEntity<>(new CustomType(400,"没有东西"),HttpStatus.OK);
        }

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @RequestMapping(value = "/cars/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getCar(@PathVariable("id")Integer id){
        Car car = carService.find(id);

        if (car == null){
            return new ResponseEntity<>(new CustomType(400,id+"没有匹配的结果"),HttpStatus.OK);
        }

        return new ResponseEntity<>(car,HttpStatus.OK);
    }

    @RequestMapping(value = "/cars",method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Car car){
        int count = carService.add(car);
        CustomType customType = new CustomType(400,"新增失败");

        if (count > 0 ){
           customType =  new CustomType(200,"新增成功");
        }

        return new ResponseEntity<>(customType,HttpStatus.OK);
    }

    @RequestMapping(value = "/cars",method = RequestMethod.PUT)
    public ResponseEntity<?> modify(@RequestBody Car car){
        int count = carService.modify(car);
        CustomType customType = new CustomType(400,"修改失败");

        if (count > 0){
            customType = new CustomType(200,"修改成功");
        }
        return new ResponseEntity<>(customType,HttpStatus.OK);
    }

    @RequestMapping(value = "/cars/{id}",method =RequestMethod.DELETE)
    public ResponseEntity<?>remove(@PathVariable Integer id){
        int count = carService.remove(id);
        CustomType customType = new CustomType(400,"删除失败");

        if (count> 0){
            customType = new CustomType(200,"删除成功");
        }
        return new ResponseEntity<>(customType,HttpStatus.OK);
    }
}
