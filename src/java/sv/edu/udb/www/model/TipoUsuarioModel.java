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
import sv.edu.udb.www.entities.TipoUsuarioEntity;


@Stateless
public class TipoUsuarioModel {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public List<TipoUsuarioEntity> listarTipoUsuario() {
        Query query = em.createNamedQuery("TipoEleccionEntity.findAll");
        return query.getResultList();
    }

    public int insertarTipoUsuario(TipoUsuarioEntity tipoUsuario) {
        try {
            em.persist(tipoUsuario);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int modificarTipoUsuario(TipoUsuarioEntity tipoUsuario) {
        try {

            em.merge(tipoUsuario);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public TipoUsuarioEntity obtenerTipoUsuario(int codigo) {
        return em.find(TipoUsuarioEntity.class,codigo);
    }

    
}
