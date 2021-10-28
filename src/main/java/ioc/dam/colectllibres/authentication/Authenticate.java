/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.colectllibres.authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author vpllo
 */
public class Authenticate {

    public void connect() {
        String usuari=null;
        
        System.out.println("PostgreSQL JDBC Connection Testing");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://54.77.189.163:5432/llibresdb",
                    "llibres",
                    "llibres");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT password FROM userlogin WHERE username ='" + usuari + "'");
            while (rs.next()) {
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                int sid = rs.getInt("sid");
                System.out.println("Username = " + Username);
                System.out.println("Password = " + Password);
                System.out.println("sid = " + sid);
                System.out.println();
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

    }

}
