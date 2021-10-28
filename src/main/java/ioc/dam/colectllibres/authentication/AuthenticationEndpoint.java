package ioc.dam.colectllibres.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
// http://localhost:8080/hellojax/res/authentication/username=admin&password=123456
 /* When consuming application/x-www-form-urlencoded, 
*  the client must to send the credentials in the following format in the request payload: 
* username=admin&password=123456
* The client should send the token in the standard HTTP Authorization header of the request. 
* For example: Authorization: Bearer <token-goes-here> 
* Advanced REST Client
*/

@Path("authentication")
public class AuthenticationEndpoint {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Consumes(MediaType.APPLICATION_JSON)
    //public Response authenticateUser(@FormParam("username") String username,@FormParam("password") String password) {
    public Response authenticateUser(Credentials credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        boolean validat = false;
        String output = "Credenciasl incorrectes";
        try {

            // Authenticate the user using the credentials provided
            validat = authenticate(username, password);
            
            // Issue a token for the user
            String token = issueToken(username);
            if (validat) {output = token;}

            // Return the token on the response
            //return Response.ok(token).build();
            //return Response.status(200).entity(output).build();
            
            // Devolvemos el token en la cabecera "Authorization". 
            // Se podría devolver también en la respuesta directamente.eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1MSIsImlzc
            return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + output).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private boolean authenticate(String usuari, String pass) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
        boolean validat = false;
        System.out.println("PostgreSQL JDBC Connection Testing");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            //return false;
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
            ResultSet rs = stmt.executeQuery("SELECT usuari, pass FROM usuaris WHERE usuari ='" + usuari + "' and pass ='" + pass +"'");
            while (rs.next()) {
                String Username = rs.getString("usuari");
                String Password = rs.getString("pass");
               if (Username.equals(usuari) && Password.equals(pass)) validat = true;
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            validat = false;
        }
        return validat;

    }

    private String issueTokenRandom(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
        Random random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32);
        return token;
    }
    private String issueToken(String username) {
    	//Calculamos la fecha de expiración del token
    	Date issueDate = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(issueDate);
    	calendar.add(Calendar.MINUTE, 60);
        Date expireDate = calendar.getTime();
        
        //Creamos el token
        String jwtToken = Jwts.builder()
                .setSubject(username)
                .setIssuer("http://www.infointernet.es")
                .setIssuedAt(issueDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, RestSecurityFilter.KEY)
                .compact();
        return jwtToken;
    }
}
