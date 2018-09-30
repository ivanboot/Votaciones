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
import sv.edu.udb.www.entities.UsuarioEntity;


@Stateless
public class UsuariosModel {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public List<UsuarioEntity> listarUsuarios() {
        Query query = em.createNamedQuery("UsuarioEntity.findAll");
        return query.getResultList();
    }

    public int insertarUsuario(UsuarioEntity usuario) {
        try {
            em.persist(usuario);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public int modificarUsuario(UsuarioEntity usuario) {
        try {

            em.merge(usuario);
            em.flush();
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public UsuarioEntity obtenerUsuario(int codigo) {
        return em.find(UsuarioEntity.class,codigo);
    }

    
}
