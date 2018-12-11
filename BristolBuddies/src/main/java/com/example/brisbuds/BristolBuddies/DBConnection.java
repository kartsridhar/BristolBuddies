package com.example.brisbuds.BristolBuddies;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

import oracle.jdbc.pool.OracleDataSource;

public class DBConnection {

<<<<<<< HEAD
    private static final String URL = "jdbc:mysql://@129.150.124.250/mydatabase";
=======
    private static final String URL = "jdbc:mysql://@129.150.124.250:3306/mydatabase";
>>>>>>> ba4b2431ec6619ca67cfdc034061d02bc5aafe52
    private static final String username = "root";
    private static final String password = "Br!st0lBudd!e5";


    public static Connection getConnection(){
     Connection con = null;
     System.out.println("connecting");
     try{
         Class.forName("com.mysql.cj.jdbc.Driver");
         con = DriverManager.getConnection(URL,username,password);
         System.out.println("connected");
     }
     catch(ClassNotFoundException e) {
         System.out.println("driver not found");
        }
     catch (SQLException e){
         System.out.println("SQL fail");
         e.printStackTrace();
     }
     return con;
    }
//    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
//    public static final Optional<String> DBAAS_USERNAME = Optional.ofNullable(System.getenv("DBAAS_USER_NAME"));
//    public static final Optional<String> DBAAS_PASSWORD = Optional.ofNullable(System.getenv("DBAAS_USER_PASSWORD"));
//    public static final Optional<String> DBAAS_DEFAULT_CONNECT_DESCRIPTOR = Optional.ofNullable(System.getenv("DBAAS_DEFAULT_CONNECT_DESCRIPTOR"));
//
//    //Local settings
//    public static final String LOCAL_USERNAME = "oracleusr";
//    public static final String LOCAL_PASSWORD = "oracle";
//    public static final String LOCAL_DEFAULT_CONNECT_DESCRIPTOR = "localhost:1521/PDB1.identity-domain.oraclecloud.internal";
//
//    private static Connection connection = null;
//    private static DBConnection instance = null;
//
//    private DBConnection() {
//        try {
//            Class.forName(DRIVER).newInstance();
//        } catch (Exception sqle) {
//            sqle.getMessage();
//        }
//    }
//
//    public static DBConnection getInstance() {
//        if (connection == null) {
//            instance = new DBConnection();
//        }
//        return instance;
//    }
//
//    public Connection getConnection() {
//        if (connection == null) {
//            try {
//                OracleDataSource ods = new OracleDataSource();
//                ods.setURL(URL + DBAAS_DEFAULT_CONNECT_DESCRIPTOR.orElse(LOCAL_DEFAULT_CONNECT_DESCRIPTOR));
//                ods.setUser(DBAAS_USERNAME.orElse(LOCAL_USERNAME));
//                ods.setPassword(DBAAS_PASSWORD.orElse(LOCAL_PASSWORD));
//                connection = ods.getConnection();
//            } catch (SQLException e) {
//                e.getMessage();
//            }
//        }
//        return connection;
//    }
}
