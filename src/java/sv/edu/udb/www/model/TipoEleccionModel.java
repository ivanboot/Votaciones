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
import sv.edu.udb.www.entities.TipoEleccionEntity;


@Stateless
public class TipoEleccionModel {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public List<TipoEleccionEntity> listarTipoEleccion() {
        Query query = em.createNamedQuery("TipoEleccionEntity.findAll");
        return query.getResultList();
    }

    public int insertarTipoEleccion(TipoEleccionEntity tipoEleccion) {
        try {
            em.persist(tipoEleccion);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int modificarTipoEleccion(TipoEleccionEntity tipoEleccion) {
        try {
            em.merge(tipoEleccion);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public TipoEleccionEntity obtenerTipoEleccion(int codigo) {
        return em.find(TipoEleccionEntity.class,codigo);
    }

    
    
}
