package com.example.demo;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.controller.MybController;

@SpringBootTest
class MybApplicationTests {

	@Test
	void contextLoads() {
	}
	
	public static void main(String[] args) {
		try {
			new MybController().myb();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
