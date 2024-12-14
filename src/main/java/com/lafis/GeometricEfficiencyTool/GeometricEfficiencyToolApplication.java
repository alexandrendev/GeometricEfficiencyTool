package com.lafis.GeometricEfficiencyTool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GeometricEfficiencyToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeometricEfficiencyToolApplication.class, args);
	}

}
