package ioc.dam.colectllibres;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
// 

/**
* <h1>Configuració dels serveis RESTful</h1>
* Classe que defineix la configuració de l'aplicació
* i el cami o context dels recursos dels serveis web
* <p>
* <b>Nota:</b>
* 
* @author  Valentí Pérez Llop
* @version 1.0
* @since   20-10-2021
*/
@ApplicationPath("res")
/**
   * Per definir la configuració la classe ha d'extends 
   */
public class JakartaRestConfiguration extends Application {
    
}
