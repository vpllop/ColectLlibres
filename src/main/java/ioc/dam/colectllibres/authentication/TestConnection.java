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
public class TestConnection {

    public static void main(String[] args) {


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
            String usuari = "u1";
            String pass = "u2";

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://54.77.189.163:5432/llibresdb",
                    "llibres",
                    "llibres");
            System.out.println("Tancada?" + connection.isClosed());
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT usuari, pass FROM usuaris WHERE usuari ='" + usuari + "' and pass ='" + pass + "'");
                       
            //if (rs.isFirst()){ System.out.println("correcte"); }
            
            while (rs.next()) {
                String Username = rs.getString("usuari");
                String Password = rs.getString("pass");
                //int sid = rs.getInt("idusuari");
                //System.out.println("id = " + sid);
                System.out.println("Username = " + Username);
                System.out.println("Password = " + Password);
                System.out.println();
                if (Username.equals(usuari) && Password.equals(pass)) System.out.println("correcte"); 
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
