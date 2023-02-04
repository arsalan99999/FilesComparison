package com.example.twodatabases;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@SpringBootApplication
public class TwodatabasesApplication {

	public static void main(String[] args) {

		SpringApplication.run(TwodatabasesApplication.class, args);
	}
}
