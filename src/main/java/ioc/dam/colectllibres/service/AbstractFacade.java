
package ioc.dam.colectllibres.service;

import java.util.List;
import jakarta.persistence.EntityManager;

/**
* <h1>Classe AbstratFacade</h1>
* Classe que defineix el metodes per interactuar amb la Base de Dades <p>
* <b>Nota:</b> 
* 
* @author  Valentí Pérez Llop
* @version 1.0
* @since   20-10-2021
*/
public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;

    /**
   * Aquest es un metode constructor de la clase
   * rep un Generic Object Type  i crea l'objecte.
   * @param entityClass Generic Object Type
   * 
   */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
   * 
   * Metode @return: Un EntityManager
   */
    protected abstract EntityManager getEntityManager();
   
    /**
   * Aquest metode crea un registre a la base de dades
   * @param Generic Object Type
   */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }
    
    /**
   * Aquest metode actualitza un registre a la base de dades
   * @param Generic Object Type
   */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }
    
    /**
   * Aquest metode elimina un registre a la base de dades
   * @param Generic Object Type
   */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

   /**
   * Aquest metode busca un objecte per el seu identificador
   * @param id Object Type
   * @return Generic Object Type
   */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
   * Aquest metode busca tots els objectes
   * @param void
   * @return Lits Generic Object Types
   */
    public List<T> findAll() {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    // findByUsuari http://localhost:8080/hellojax/res/usuaris/usuari/u1
    public String findByUsuari(String user) {
        jakarta.persistence.Query cq;
        //jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        //cq.select(cq.from(entityClass));
        cq = getEntityManager().createNamedQuery("Usuaris.findByUsuari", entityClass);
        cq.setParameter("usuari", "u1");
        //cq.getSingleResult();
        //return getEntityManager().createQuery(cq).getResultList();
        return ((String) cq.getSingleResult()).toString();
    }
     /**
   * Aquest metode busca en un rang de registres
   * @param Array inters
   * @return Llista de Generic Object Types
   */
    public List<T> findRange(int[] range) {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        jakarta.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
 /**
   * Aquest metode conta el numero de registres
   * @param void
   * @return enter
   */
    public int count() {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        jakarta.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        jakarta.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
