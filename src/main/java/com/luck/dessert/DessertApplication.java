package com.luck.dessert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.Socket;
import java.sql.Array;

@SpringBootApplication
public class DessertApplication {

	public static void main(String[] args) {
		ClassLoader classLoader1 = String.class.getClassLoader();
		ClassLoader classLoader2 = Socket.class.getClassLoader();
		ClassLoader classLoader3 = Array.class.getClassLoader();
		SpringApplication.run(DessertApplication.class, args);
	}

}
