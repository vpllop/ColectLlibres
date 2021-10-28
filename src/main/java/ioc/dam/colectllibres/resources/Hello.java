
package ioc.dam.colectllibres.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;
import static java.util.Collections.singletonMap;

/**
 * The REST resource implementation class.
 */
@Path("hello")
public class Hello {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloWorld() {
        Map<String, String> response = singletonMap("message",
                "Building Web Services with Java EE 8.");
        return Response.ok(response).build();
    }
}
