package org.payrolljdbc;

import java.sql.*;
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

        /*
         * Executing all statement
         */
        statement.execute(createDatabase);
        statement.execute(useDatabase);
        statement.execute(createTable);
        statement.execute(insertIntoTable);

        // updating the data
        String updateData = "UPDATE employee_payroll set salary = 30000 WHERE name='Terisa'";
        statement.execute(updateData);

        // updating the data using prepared statement
        String nameToEdit = "Terisa";
        String updateUsingPrepared = String.format("UPDATE employee_payroll set salary = ?  WHERE name='%s'; ", nameToEdit);
        PreparedStatement newStatement = connection.prepareStatement(updateUsingPrepared);
        newStatement.setDouble(1, 2500);
        newStatement.execute();

        //  retrieve data in particular date range
        String checkByDate = String.format("SELECT * FROM employee_payroll " +
                "WHERE start BETWEEN '%s' and '%s'; ", Date.valueOf("2020-12-29"), Date.valueOf("2022-01-01"));
        ResultSet result = statement.executeQuery(checkByDate);
        while (result.next()) {
            String id = result.getString("id");
            String name = result.getString("name");
            String salary = result.getString("salary");
            String start = result.getString("start");

            System.out.println(id + "- " + name + ", " + salary + ", " + start);
        }

        // commands for getting sum,avg,min,max of salary
        String sum = "SELECT SUM(salary) FROM employee_payroll ";
        String avg = "SELECT AVG(salary) FROM employee_payroll ";
        String min = "SELECT MIN(salary) FROM employee_payroll ";
        String max = "SELECT MAX(salary) FROM employee_payroll ";
        statement.execute(sum);
        statement.execute(avg);
        statement.execute(min);
        statement.execute(max);

        // Adding new employee to employee_Payroll
        String insertEmployee = "INSERT INTO employee_payroll(name,salary,start) VALUES " +
                "('Kalpesh',1090.87,'2021-09-22') ";
        statement.execute(insertEmployee);

        /*
         * Creating new table and inserting the value in new table
         */
        String createTablePayroll = "CREATE TABLE payroll" +
                "(department VARCHAR(150) NOT NULL PRIMARY KEY,basic_pay VARCHAR(150) NOT NULL," +
                "deductions VARCHAR(150) NOT NULL, taxable_pay VARCHAR(150) NOT NULL," +
                "income_tax VARCHAR(150) NOT NULL, net_pay VARCHAR(150) NOT NULL)";

        String insertIntoPayroll = "INSERT INTO payroll(department,basic_pay,deductions,taxable_pay,income_tax,net_pay) " +
                "VALUES ('Tech',999,5,6,33,1233);";

        statement.execute(createTablePayroll);
        statement.execute(insertIntoPayroll);


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
