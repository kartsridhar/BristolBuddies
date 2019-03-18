package com.example.brisbuds.BristolBuddies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.util.Optional;
import java.util.Properties;

import static com.example.brisbuds.BristolBuddies.DBConnection.getConnection;

@SpringBootApplication
public class BristolBuddiesApplication {

	public static final Properties myProps = new Properties();

	public static void main(String[] args) {
		SpringApplication.run(BristolBuddiesApplication.class,args);
	}
}
