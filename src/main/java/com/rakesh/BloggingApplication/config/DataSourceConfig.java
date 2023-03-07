package com.rakesh.BloggingApplication.config;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
	@Bean
	public DataSource getDataSource() {
		return DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver")
				.url("jdbc:mysql://localhost:3306/bloggingapplication").username("root").password("admin").build();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}