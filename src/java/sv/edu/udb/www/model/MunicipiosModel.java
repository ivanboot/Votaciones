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
import sv.edu.udb.www.entities.MunicipioEntity;


@Stateless
public class MunicipiosModel {

    @PersistenceContext(unitName = "VotacionPU")
    private EntityManager em;

    public List<MunicipioEntity> listarMunicipios(){
        Query query=em.createNamedQuery("MunicipioEntity.findAll");
        return query.getResultList();
    }
    
    public List<MunicipioEntity> listarMunicipiosDepartamento(int id){
        Query query=em.createNamedQuery("SELECT m FROM MunicipioEntity m WHERE m.idDepartamento.idDepartamento=:idDepartamento");
        query.setParameter("idDepartamento", id);
        return query.getResultList();
    }

    public int insertarMunicipio(MunicipioEntity municipio){
        try{
            em.persist(municipio);
            em.flush();
            return 1;
        }catch(Exception e){
            return 0;
        }
    }
    
    public int modificarMunicipio(MunicipioEntity municipio){
        try{
            em.merge(municipio);
            em.flush();
            return 1;
        }catch(Exception e){
            return 0;
        }
    }
    
    public MunicipioEntity obtenerMunicipio(int codigo){
        return em.find(MunicipioEntity.class,codigo);
    }
}
