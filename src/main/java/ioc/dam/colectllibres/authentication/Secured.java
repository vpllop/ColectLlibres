/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.colectllibres.authentication;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;

/*
<dependency>
  <groupId>io.jsonwebtoken</groupId>
  <artifactId>jjwt</artifactId>
  <version>0.7.0</version>
</dependency>
*/

/*
El filtro creado anteriormente no debe ejecutarse siempre, sino sólo en aquellos casos en los que el recurso esté protegido. Para ello crearemos una anotación que utilizará la meta-anotación “@NameBinding”, que forma parte de JAX-RS.
*/
@jakarta.ws.rs.NameBinding
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Secured {

}