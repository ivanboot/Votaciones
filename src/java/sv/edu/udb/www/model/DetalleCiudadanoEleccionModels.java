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
import sv.edu.udb.www.entities.DetalleCiudadanoEleccionEntity;


@Stateless
public class DetalleCiudadanoEleccionModels {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public List<DetalleCiudadanoEleccionEntity> listarDetalleCiudadanoEeccion() {
        Query query = em.createNamedQuery("DetalleCiudadanoEleccionEntity.findAll");
        return query.getResultList();
    }

    public int insertarDetalleCiudadanoEleccion(DetalleCiudadanoEleccionEntity detalleCiudadnoEleccion) {
        try {
            em.persist(detalleCiudadnoEleccion);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int modificarDetalleCiudadanoEleccion(DetalleCiudadanoEleccionEntity detalleCiudadanoEleccion) {
        try {

            em.merge(detalleCiudadanoEleccion);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public DetalleCiudadanoEleccionEntity obtenerPartido(int codigo) {
        return em.find(DetalleCiudadanoEleccionEntity.class,codigo);
    }



}
