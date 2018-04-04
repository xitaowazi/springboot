package com.springboot.controller;

import com.springboot.domain.Car;
import com.springboot.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    //跳转版
    @GetMapping("carlist")
    public ModelAndView list(){
        List<Car> cars = carService.list();
        ModelAndView mv =new ModelAndView();
        mv.setViewName("carlist");
        mv.addObject("cars",cars);

        Car car = new Car();
        car.setId(100);
        car.setName("Volvo");
        car.setPrice(8989d);
        car.setCreateDate(new Date());
        mv.addObject("car", car);
        return mv;
    }

   /* @RequestMapping(value = "/carlist",method = RequestMethod.GET)
    public ResponseEntity<?> list(){
        List<Car> cars = carService.list();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }*/

    @RequestMapping("hithymeleaf")
    public String hiThymeleaf(@RequestParam(value = "name",required = false,defaultValue = "world")String name, Model model){
        //模型数据
        model.addAttribute("name",name);
        //视图(resources 下的模板中的 html:hello.html)
        return "hello";
    }

}
