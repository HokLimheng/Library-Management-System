package System;

import java.sql.Connection;
import java.sql.DriverManager;

public class Repositary {


    static String URL = "jdbc:mysql://localhost:3306/library_management";
    static String USERNAME = "root";
    static String PASSWORD = "heng1505";

    Connection connection = null;

    public Connection setConnection() {
//        1- load driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return connection;

    }
}