package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

/**
 *
 * @author Lap.lk
 */
public class mysql {

    /**
     * @param args the command line arguments
     */
    static Connection connection;

    public static Connection create_connection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        if (connection == null) {
            connection = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shop",
                    "root", "Che123//"
            );
        }
        return connection;
    }

    public static void iud(String query) throws Exception {
        mysql.create_connection();

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    public static ResultSet search(String query) throws Exception {
        mysql.create_connection();

        Statement stmt = connection.createStatement();

        return stmt.executeQuery(query);

    }

    public static void main(String[] args) throws Exception {
        mysql.create_connection();

    }
//jasper kallak
    public static Connection getConnection() throws Exception {
        if(connection==null){
            return create_connection();
        }
        return connection;
    }

    
    
}
