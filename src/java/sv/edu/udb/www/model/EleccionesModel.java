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
import sv.edu.udb.www.entities.EleccionEntity;



@Stateless
public class EleccionesModel {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public List<EleccionEntity> listarElecciones() {
        Query query = em.createNamedQuery("EleccionEntity.findAll");
        return query.getResultList();
    }
    
    public List<EleccionEntity> listaEleccionesDisponibles(){
        Query query = em.createQuery("SELECT e FROM EleccionEntity e where e.fechaInicioCandidato < CURRENT_DATE and e.fechaFinCandidato > CURRENT_DATE and e.estado = 1");
        return query.getResultList();
    }
    
    public List<EleccionEntity> listarEleccionesActivas(){
        Query query = em.createQuery("SELECT e FROM EleccionEntity e WHERE e.estado = 1");
        return query.getResultList();
    }

    public int insertarEleccion(EleccionEntity eleccion) {
        try {
            em.persist(eleccion);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int modificarEleccion(EleccionEntity eleccion) {
        try {

            em.merge(eleccion);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public EleccionEntity obtenerEleccion(int codigo) {
        return em.find(EleccionEntity.class,codigo);
    }
    
    
    
}
