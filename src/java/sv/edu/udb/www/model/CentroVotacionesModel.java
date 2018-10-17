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
import sv.edu.udb.www.entities.CentroVotacionEntity;


@Stateless
public class CentroVotacionesModel {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public List<CentroVotacionEntity> listarCentroVotaciones() {
        Query query = em.createNamedQuery("CentroVotacionEntity.findAll");
        return query.getResultList();
    }
    
    public List<CentroVotacionEntity> listarCentroVotacionesDepartamento(int id) {
        Query query = em.createQuery("SELECT c FROM CentroVotacionEntity c WHERE c.idMunicipio.idDepartamento.idDepartamento:idDepartamento");
        query.setParameter("idDepartamento", id);
        return query.getResultList();
    }


    public int insertarCentroVotacion(CentroVotacionEntity centroVotacion) {
        try {
            em.persist(centroVotacion);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int modificarCentroVotacion(CentroVotacionEntity centroVotacion) {
        try {

            em.merge(centroVotacion);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public CentroVotacionEntity obtenerCentroVotacion(int codigo) {
        return em.find(CentroVotacionEntity.class,codigo);
    }


    
}
