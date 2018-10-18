/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.entities.DetalleCiudadanoJrvEntity;

/**
 *
 * @author Ferh
 */
@Stateless
public class DetalleCiudadanoJrvModel {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public int insertarDetalleCiudadanoJrvEntity(DetalleCiudadanoJrvEntity ciudadanoJrv) {
        try {
            em.persist(ciudadanoJrv);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<DetalleCiudadanoJrvEntity> listaCiudadanosJrv(int id) {
        Query query = em.createQuery("SELECT d FROM DetalleCiudadanoJrvEntity d WHERE d.idJrv.idJrv=:idJrv");
        query.setParameter("idJrv", id);
        return query.getResultList();
    }
    
    
}
