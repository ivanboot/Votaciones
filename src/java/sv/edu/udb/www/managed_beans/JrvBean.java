/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sv.edu.udb.www.entities.CentroVotacionEntity;
import sv.edu.udb.www.entities.CiudadanoEntity;
import sv.edu.udb.www.entities.EleccionEntity;
import sv.edu.udb.www.entities.JrvEntity;
import sv.edu.udb.www.entities.UsuarioEntity;
import sv.edu.udb.www.model.CentroVotacionesModel;
import sv.edu.udb.www.model.CiudadanosModel;
import sv.edu.udb.www.model.EleccionesModel;
import sv.edu.udb.www.model.JrvModel;
import sv.edu.udb.www.model.UsuariosModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author Ferh
 */
@Named(value = "jrvBean")
@RequestScoped
public class JrvBean {

    @EJB
    private CentroVotacionesModel centroVotacionesModel;

    @EJB
    private EleccionesModel eleccionesModel;

    @EJB
    private UsuariosModel usuariosModel;

    @EJB
    private CiudadanosModel ciudadanosModel;

    @EJB
    private JrvModel jrvModel;

    List <JrvEntity> listaJrvs;
    
    List <CiudadanoEntity> listaCiudadanos;
    
    List <UsuarioEntity> listaUsuarios;
    
    List <EleccionEntity> listaElecciones;
    
    List <CentroVotacionEntity> listaCentroVotaciones;
    
    JrvEntity jrv = new JrvEntity();
    
    public JrvBean() {
    }

    public List<JrvEntity> getListaJrvs() {
        return jrvModel.listarJrv();
    }
    
    public List<JrvEntity> getListaJrvsActivas() {
        return jrvModel.listarJrvActivas();
    }
    
    public List<JrvEntity> getListaJrvsFinalizadas() {
        return jrvModel.listarJrvFinalizadas();
    }

    public List<CiudadanoEntity> getListaCiudadanos() {
        return ciudadanosModel.listarCiudadanos();
    }

    public List<UsuarioEntity> getListaUsuarios() {
        return usuariosModel.listarUsuarios();
    }

    public List<EleccionEntity> getListaElecciones() {
        return eleccionesModel.listarEleccionesActivas();
    }

    public List<CentroVotacionEntity> getListaCentroVotaciones() {
        return centroVotacionesModel.listarCentroVotaciones();
    }

    public JrvEntity getJrv() {
        return jrv;
    }

    public void setJrv(JrvEntity jrv) {
        this.jrv = jrv;
    }
    
    
    public String nuevaJrv(){
        
        if(jrv.getIdSecretario().getIdCiudadano() == jrv.getIdVocal().getIdCiudadano()){
            JsfUtils.addErrorMessage("vocal", "Debe seleccionar otro vocal. Este ciudadano ya tiene un cargo");
            return null;
        }
        if(jrvModel.insertarJrv(jrv)==0){
            JsfUtils.addErrorMessage("idJrv", "No se pudo ingresar la JRV");
            return null;
        }
        JsfUtils.addFlashMessage("exito", "JRV ingresada con exito");
        return "/adminDepartamental/ListaJrv?faces-redirect=true";
    }
    
    public String modificarJrv(){
        
        if(jrv.getIdSecretario().getIdCiudadano() == jrv.getIdVocal().getIdCiudadano()){
            JsfUtils.addErrorMessage("vocal", "Debe seleccionar otro vocal. Este ciudadano ya tiene un cargo");
            return null;
        }
        if(jrvModel.insertarJrv(jrv)==0){
            JsfUtils.addErrorMessage("idJrv", "No se pudo ingresar la JRV");
            return null;
        }
        JsfUtils.addFlashMessage("exito", "JRV ingresada con exito");
        return "/adminDepartamental/ListaJrv?faces-redirect=true";
    }
    
    
    
    
}
