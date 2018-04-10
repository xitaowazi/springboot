package com.springboot;

import com.springboot.domain.Car;
import com.springboot.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


//基于SpringRunner 单元测试类
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void hello(){
		//通过 restTemplate 来发起 get请求，请求本服务的地址 /hello
		ResponseEntity<String> entity = restTemplate.getForEntity("/hello",String.class);
		//System.out.println(entity.getBody());

		//通过 assertThat 进行断言判断
		assertThat(entity.getBody()).isEqualTo("hello Spring Boot");
		assertThat(entity.getStatusCodeValue()).isEqualTo(200);
	}
	//加载上下文
	@Test
	public void contextLoads() {
	}

	@Test
    public void list(){
        CarService carService = new CarService();
        List<Car> cars = carService.list();
        System.out.println(cars);
    }

}
