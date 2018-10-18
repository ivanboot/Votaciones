package sv.edu.udb.www.model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.entities.PartidosEntity;


@Stateless
public class PartidosModel {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public List<PartidosEntity> listarPartidos() {
        Query query = em.createNamedQuery("PartidosEntity.findAll");
        return query.getResultList();
    }

    public int insertarPartido(PartidosEntity partido) {
        try {
            em.persist(partido);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int modificarPartido(PartidosEntity partido) {
        try {

            em.merge(partido);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public PartidosEntity obtenerPartido(int codigo) {
        return em.find(PartidosEntity.class,codigo);
    }

}
