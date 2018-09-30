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
import sv.edu.udb.www.entities.JrvEntity;
import sv.edu.udb.www.entities.PartidosEntity;


@Stateless
public class JrvModel {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public List<JrvEntity> listarJrv() {
        Query query = em.createNamedQuery("JrvEntity.findAll");
        return query.getResultList();
    }

    public int insertarJrv(JrvEntity jrv) {
        try {
            em.persist(jrv);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int modificarJrv(JrvEntity jrv) {
        try {

            em.merge(jrv);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public JrvEntity obtenerJrv(int codigo) {
        return em.find(JrvEntity.class,codigo);
    }

    
}
