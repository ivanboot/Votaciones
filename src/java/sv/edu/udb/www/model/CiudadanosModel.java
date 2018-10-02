
package sv.edu.udb.www.model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.entities.CiudadanoEntity;


@Stateless
public class CiudadanosModel {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public List<CiudadanoEntity> listarCiudadanos() {
        Query query = em.createNamedQuery("CiudadanoEntity.findAll");
        return query.getResultList();
    }

    public int insertarCiudadano(CiudadanoEntity ciudadano){
        try {
            em.persist(ciudadano);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int modificarCiudadano(CiudadanoEntity ciudadano) {
        try {

            em.merge(ciudadano);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public CiudadanoEntity obtenerCiudadano(int codigo) {
        return em.find(CiudadanoEntity.class,codigo);
    }

}
