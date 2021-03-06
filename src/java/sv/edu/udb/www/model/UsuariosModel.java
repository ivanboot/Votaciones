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
import sv.edu.udb.www.entities.CiudadanoEntity;
import sv.edu.udb.www.entities.UsuarioEntity;
import sv.edu.udb.www.utils.SecurityUtils;

@Stateless
public class UsuariosModel {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public UsuarioEntity verificarCredenciales(String correo, String contra) {
        try {
            Query query = em.createQuery("SELECT u FROM UsuarioEntity u WHERE u.correo=:correo AND u.contra=:contra");
            query.setParameter("correo", correo);
            query.setParameter("contra", SecurityUtils.encriptarSHA(contra));
            return (UsuarioEntity) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }      
    
    public List<UsuarioEntity> listarUsuarios() {
        Query query = em.createNamedQuery("UsuarioEntity.findAll");
        return query.getResultList();
    }
    
    public List<UsuarioEntity> listarPresidentes(int id) {
        Query query = em.createQuery("SELECT u FROM UsuarioEntity u WHERE u.idTipoUsuario.idTipoUsuario = 4 AND u.idCiudadano.idCentroVotacion.idMunicipio.idMunicipio =:idMunicipio");
        query.setParameter("idMunicipio", id);
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
        return em.find(UsuarioEntity.class, codigo);
    }

}
