
package ioc.dam.colectllibres.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.crypto.MacProvider;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;

/*
El filtro será el encargado de interceptar las peticiones a recursos REST protegidos y validar el token recibido en la cabecera. Para ellos debe implementar la interfaz ContainerRequestFilter, que forma parte del estándar JAX-RS.
Para generar la cadena secreta hemos utilizado la clase “MacProvider” de la propia libreria JWT. El método usado genera una clave secreta aleatoria de 512 bits. Podría utilizarse también alguna clave almacenada en un keystore.

La anotación “@Provider” será la que nos permitirá registrar automáticamente nuestro filtro en nuestra aplicación REST.
*/
@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class RestSecurityFilter implements ContainerRequestFilter {
	
    public static final Key KEY = MacProvider.generateKey();
 
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
 
        // Recupera la cabecera HTTP Authorization de la petición
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
 
        try {
        	// Extrae el token de la cabecera
            String token = authorizationHeader.substring("Bearer".length()).trim();
 
            // Valida el token utilizando la cadena secreta
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
 
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}