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
import sv.edu.udb.www.entities.DepartamentoEntity;


@Stateless
public class DepartamentosModel {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public List<DepartamentoEntity> listarDepartamentos() {
        Query query = em.createNamedQuery("DepartamentoEntity.findAll");
        return query.getResultList();
    }

    public int insertarDepartamento(DepartamentoEntity departamento) {
        try {
            em.persist(departamento);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int modificarDepartamento(DepartamentoEntity departamento) {
        try {

            em.merge(departamento);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public DepartamentoEntity obtenerDepartamento(int codigo) {
        return em.find(DepartamentoEntity.class,codigo);
    }


    
}
