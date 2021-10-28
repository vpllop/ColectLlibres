
package ioc.dam.colectllibres.service;

import ioc.dam.colectllibres.Usuaris;
import ioc.dam.colectllibres.authentication.Secured;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * http://localhost:8080/hellojax/res/usuaris
 */
@jakarta.ejb.Stateless
@jakarta.ws.rs.Path("usuaris")
public class UsuarisFacadeREST extends AbstractFacade<Usuaris> {

    @PersistenceContext(unitName = "MyPersistenceUnit")
    private EntityManager em;

    public UsuarisFacadeREST() {
        super(Usuaris.class);
    }

    @jakarta.ws.rs.POST
    @Override
    @jakarta.ws.rs.Consumes({jakarta.ws.rs.core.MediaType.APPLICATION_XML, jakarta.ws.rs.core.MediaType.APPLICATION_JSON})
    public void create(Usuaris entity) {
        super.create(entity);
    }

    @jakarta.ws.rs.PUT
    @jakarta.ws.rs.Path("{id}")
    @jakarta.ws.rs.Consumes({jakarta.ws.rs.core.MediaType.APPLICATION_XML, jakarta.ws.rs.core.MediaType.APPLICATION_JSON})
    public void edit(@jakarta.ws.rs.PathParam("id") Short id, Usuaris entity) {
        super.edit(entity);
    }

    @jakarta.ws.rs.DELETE
    @jakarta.ws.rs.Path("{id}")
    public void remove(@jakarta.ws.rs.PathParam("id") Short id) {
        super.remove(super.find(id));
    }

    @jakarta.ws.rs.GET
    @jakarta.ws.rs.Path("{id}")
    @jakarta.ws.rs.Produces({jakarta.ws.rs.core.MediaType.APPLICATION_XML, jakarta.ws.rs.core.MediaType.APPLICATION_JSON})
    public Usuaris find(@jakarta.ws.rs.PathParam("id") Short id) {
        return super.find(id);
    }
    
    // Get Username
    @jakarta.ws.rs.GET
    @Override
    @jakarta.ws.rs.Path("usuari/{user}")
    @jakarta.ws.rs.Produces(jakarta.ws.rs.core.MediaType.TEXT_PLAIN)
    public String findByUsuari(@jakarta.ws.rs.PathParam("user") String usuari) {
        return super.findByUsuari(usuari);
    }
    
    @jakarta.ws.rs.GET
    @Secured 
    @Override
    @jakarta.ws.rs.Produces({jakarta.ws.rs.core.MediaType.APPLICATION_XML, jakarta.ws.rs.core.MediaType.APPLICATION_JSON})
    public List<Usuaris> findAll() {
        return super.findAll();
    }
    

    @jakarta.ws.rs.GET
    @jakarta.ws.rs.Path("{from}/{to}")
    @jakarta.ws.rs.Produces({jakarta.ws.rs.core.MediaType.APPLICATION_XML, jakarta.ws.rs.core.MediaType.APPLICATION_JSON})
    public List<Usuaris> findRange(@jakarta.ws.rs.PathParam("from") Integer from, @jakarta.ws.rs.PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @jakarta.ws.rs.GET
    @jakarta.ws.rs.Path("count")
    @jakarta.ws.rs.Produces(jakarta.ws.rs.core.MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
