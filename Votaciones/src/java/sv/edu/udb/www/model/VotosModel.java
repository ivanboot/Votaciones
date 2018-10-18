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
import sv.edu.udb.www.entities.VotoEntity;


@Stateless
public class VotosModel {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public List<VotoEntity> listarVotos() {
        Query query = em.createNamedQuery("VotoEntity.findAll");
        return query.getResultList();
    }
    
    public List<Object[]> listarVotacionTotal(int eleccion){
        Query query = em.createQuery("Select v.idCandidato.nombres, SUM(v.cantidad) from VotoEntity v where v.idJrv.idElecciones.idEleccion=:idEleccion GROUP BY v.idCandidato.nombres");
        query.setParameter("idEleccion", eleccion);
        return query.getResultList();
    }
    
    public int insertarVoto(VotoEntity voto) {
        try {
            em.persist(voto);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int modificarVoto(VotoEntity voto) {
        try {

            em.merge(voto);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public VotoEntity obtenerVoto(int codigo) {
        return em.find(VotoEntity.class,codigo);
    }
    
}
