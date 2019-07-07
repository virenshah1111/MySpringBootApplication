package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Role;
import com.example.demo.model.RoleName;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner, ApplicationRunner{

	@Autowired
	UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//		System.out.println(BCrypt.hashpw("user",BCrypt.gensalt(4)));
	}

	public static int counter = 0;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("-----------: "+counter++);
		System.out.println(Arrays.toString(args));
		
		List<User> userList = new ArrayList<>();
		userList.add(new User("user","$2a$04$vXDE0wYhpiI2/zAdHHh5luRS3cp73f3BSBFELbmSKlA.vzQSZwLPO",true,false,Collections.singleton(new Role(RoleName.USER))));
		userList.add(new User("admin","$2a$04$PhbhpTJ0BLF5uTeqSUyruOcdbmb7eciXG.rjkYoL/S.k.zhUwqnHK",true,false,Collections.singleton(new Role(RoleName.ADMIN))));
		
		userService.saveAll(userList);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		System.out.println("-----------app----: "+counter++);
		System.out.println(args);
	}

	
	
	
}
