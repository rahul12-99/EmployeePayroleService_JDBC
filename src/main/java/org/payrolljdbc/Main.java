package org.payrolljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome to employee payroll service using JDBC");
        /*
         * Connection for connecting with the url,user and password
         */
        Connection connection = DriverManager.getConnection(Sys.url, Sys.user, Sys.password);
        Statement statement = connection.createStatement();

        /*
         * Commends for creating database and use database
         */
        String createDatabase = "CREATE DATABASE payroll_service_JDBC";
        String useDatabase = "USE payroll_service_JDBC";
        /*
         * Commends for create table and insert in table
         */
        String createTable = "CREATE TABLE employee_payroll" +
                "(id INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
                "name VARCHAR(150) NOT NULL, salary DOUBLE NOT NULL,start DATE NOT NULL)";

        String insertIntoTable = "INSERT INTO employee_payroll(name,salary,start) VALUES " +
                "('Rahul',9878,'2020-05-28'), " +
                "('Kundan',1787.00,'2021-01-01')," +
                "('Terisa',999.99,'2022-05-20');";

        statement.execute(createDatabase);
        statement.execute(useDatabase);
        statement.execute(createTable);
        statement.execute(insertIntoTable);

        // commands for delete database
        String deleteDatabase = "DROP DATABASE payroll_service_JDBC";


        System.out.print("Do you want to delete the database (Yes/No) - ");
        String choice = new Scanner(System.in).next();

        if (choice.equalsIgnoreCase("yes")) {
            statement.execute(deleteDatabase);
            System.out.println("database deleted successfully....");
        }

    }
}
