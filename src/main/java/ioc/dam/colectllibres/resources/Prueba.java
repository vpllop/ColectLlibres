/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.colectllibres.resources;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

public class Prueba {
    
    static HttpURLConnection con;
 
    public static void requestLogin() {
	
	String response;
	
	try {
	    
	    String input = "{\"username\":\"u1\",\"password\":\"u2\"}";
 
	    URL url = new URL("http://ec2-54-77-189-163.eu-west-1.compute.amazonaws.com:8080/ColectLlibres/res/authentication");
	    con = (HttpURLConnection) url.openConnection();
	    con.setRequestMethod("POST");
	    con.setRequestProperty("Content-Type", "application/json");
	    con.setDoOutput(true);
	    
//	    if (con.getResponseCode() != 200) {
//		throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
//	    }
 
	    // Envia les dades al servidor
	    DataOutputStream writer = new DataOutputStream(con.getOutputStream());
	    writer.writeBytes(input);
	    writer.close();
	    
	    // Llegeix la resposta del servidor
	    BufferedReader reader = new BufferedReader(new InputStreamReader((con.getInputStream())));
	    response = reader.lines().collect(Collectors.joining());
	    reader.close();
	    
	    System.out.println(response);
	    
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    con.disconnect();
	}
    }
    
    public static void main(String[] args) {
	requestLogin();
    }
    
}
