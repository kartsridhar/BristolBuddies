package com.example.brisbuds.BristolBuddies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.util.Optional;
import java.util.Properties;

import static com.example.brisbuds.BristolBuddies.DBConnection.getConnection;

@SpringBootApplication
public class BristolBuddiesApplication {



	public static final Optional<String> host;
	public static final Optional<String> port;
	public static final Properties myProps = new Properties();


	static{
		host = Optional.ofNullable(System.getenv("HOSTNAME"));
		port = Optional.ofNullable(System.getenv("PORT"));
	}


	public static void main(String[] args) {
		Connection c = getConnection();
		myProps.setProperty("server.address",host.orElse("localhost"));
		myProps.setProperty("server.port",port.orElse("8080"));
		SpringApplication app = new SpringApplication(BristolBuddiesApplication.class);
		app.setDefaultProperties(myProps);
		app.setDefaultProperties(myProps);
		app.run(args);

	}
}
