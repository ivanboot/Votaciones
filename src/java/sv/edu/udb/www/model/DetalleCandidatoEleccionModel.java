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
import sv.edu.udb.www.entities.DetalleCandidatoEleccionEntity;
import sv.edu.udb.www.entities.EleccionEntity;

/**
 *
 * @author Ferh
 */
@Stateless
public class DetalleCandidatoEleccionModel {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public List<DetalleCandidatoEleccionEntity> listarCandidatos(int idC, int idP){
        Query query = em.createQuery("SELECT d FROM DetalleCandidatoEleccionEntity d WHERE d.idEleccion.estado=1 AND d.idEleccion.idTipoEleccion.idEleccion=2 AND d.idCandidatos.idMunicipio.idMunicipio=:idMunicipio AND d.idCandidatos.idPartido.idPartido=:idPartido");
        query.setParameter("idMunicipio", idC);
        query.setParameter("idPartido", idP);
        return query.getResultList();
    }
    
    public List<DetalleCandidatoEleccionEntity> listarCandidatosPresidentes(int id){
        Query query = em.createQuery("SELECT d FROM DetalleCandidatoEleccionEntity d WHERE d.idEleccion.estado=1 AND d.idEleccion.idTipoEleccion.idEleccion=1 AND d.idCandidatos.idPartido.idPartido=:idPartido");
        query.setParameter("idPartido", id);
        return query.getResultList();
    }
    
    public int insertarDetalle(DetalleCandidatoEleccionEntity detalle) {
        try {
            em.persist(detalle);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
   
}
