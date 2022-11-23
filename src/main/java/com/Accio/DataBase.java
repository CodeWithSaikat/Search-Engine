package com.Accio;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
    static Connection connection = null;

    public static Connection getConnection(){
        if(connection != null){
            return connection;
        }
        String db = "Search";
        String user = "root";
        String pwd = "Saikat@123";
        return getConnection(db, user, pwd);
    }
    private static Connection getConnection(String db, String user, String pwd){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+db+"?user="+user+"&password="+pwd);
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Search","root","Saikat@123");
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return  connection;
    }
}
