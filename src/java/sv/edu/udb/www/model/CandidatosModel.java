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
import sv.edu.udb.www.entities.CandidatoEntity;


@Stateless
public class CandidatosModel {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public List<CandidatoEntity> listarCandidatos(){
        Query query= em.createNamedQuery("CandidatoEntity.findAll");
        return query.getResultList();
    }
    
    public List<CandidatoEntity> listarCandidatosPresidenciales(){
        Query query= em.createQuery("SELECT c FROM CandidatoEntity c WHERE c.detalleCandidatoEleccionEntityList.idEleccion.idEleccion=1");
        return query.getResultList();
    }
    
    public int insertarCandidadto(CandidatoEntity candidato){
        try{
            em.persist(candidato);
            em.flush();
            return 1;
        }catch(Exception e){
            return 0;
        }
    }
    
    public int modificarCandidato(CandidatoEntity candidato){
        try{
            em.merge(candidato);
            em.flush();
            return 1;
        }catch(Exception e){
            return 0;
        }
    }

    public CandidatoEntity obtenerCandidato(int codigo){
        return em.find(CandidatoEntity.class,codigo);
    }
    
    public CandidatoEntity obtenerCandidatoPartido(int codigo){
        return em.find(CandidatoEntity.class,codigo);
    }
}
