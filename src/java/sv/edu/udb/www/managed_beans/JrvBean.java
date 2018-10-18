/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import sv.edu.udb.www.entities.CentroVotacionEntity;
import sv.edu.udb.www.entities.CiudadanoEntity;
import sv.edu.udb.www.entities.DetalleCiudadanoJrvEntity;
import sv.edu.udb.www.entities.EleccionEntity;
import sv.edu.udb.www.entities.JrvEntity;
import sv.edu.udb.www.entities.UsuarioEntity;
import sv.edu.udb.www.model.CentroVotacionesModel;
import sv.edu.udb.www.model.CiudadanosModel;
import sv.edu.udb.www.model.DetalleCiudadanoJrvModel;
import sv.edu.udb.www.model.EleccionesModel;
import sv.edu.udb.www.model.JrvModel;
import sv.edu.udb.www.model.UsuariosModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author Ferh
 */
@Named(value = "jrvBean")
@SessionScoped
public class JrvBean implements Serializable{

    @EJB
    private DetalleCiudadanoJrvModel detalleCiudadanoJrvModel;
    
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
    
    

    List<JrvEntity> listaJrvs;

    List<CiudadanoEntity> listaCiudadanos;

    List<UsuarioEntity> listaUsuarios;

    List<EleccionEntity> listaElecciones;

    List<CentroVotacionEntity> listaCentroVotaciones;
    
    List<DetalleCiudadanoJrvEntity> listaDetalleCiudadanoJrv;

    JrvEntity jrv = new JrvEntity();

    public JrvBean() {
    }

    public List<JrvEntity> getListaJrvs() {
        return jrvModel.listarJrv();
    }

    public List<JrvEntity> getListaJrvsActivas() {
        
        HttpServletRequest request = JsfUtils.getRequest();
        return jrvModel.listarJrvActivas(Integer.parseInt(request.getSession().getAttribute("departamento").toString()));
    }

    public List<JrvEntity> getListaJrvsFinalizadas() {
        HttpServletRequest request = JsfUtils.getRequest();
        return jrvModel.listarJrvFinalizadas(Integer.parseInt(request.getSession().getAttribute("departamento").toString()));
    }

    public List<CiudadanoEntity> getListaCiudadanos() {

        try {
            return ciudadanosModel.listarCiudadanosMunicipio(jrv.getIdCentroVotacion().getIdMunicipio().getIdMunicipio());
        } catch (Exception e) {

            return null;
        }

    }

    public List<UsuarioEntity> getListaUsuarios() {
        try {
            return usuariosModel.listarPresidentes(jrv.getIdCentroVotacion().getIdMunicipio().getIdMunicipio());
        } catch (Exception e) {

            return null;
        }
    }

    public List<CentroVotacionEntity> getListaCentroVotaciones() {
        HttpServletRequest request = JsfUtils.getRequest();
        return centroVotacionesModel.listarCentroVotacionesDepartamento(Integer.parseInt(request.getSession().getAttribute("departamento").toString()));
    }
    
    public EleccionEntity getEleccionActiva(){
        try{
            return eleccionesModel.obtenerEleccionActiva();
        }catch(Exception e){
            return null;
        }
    }
    
    public List<EleccionEntity> getEleccionDisponible(){
        return eleccionesModel.listaEleccionesDisponibles();
    }
    

    public JrvEntity getJrv() {
        return jrv;
    }

    public void setJrv(JrvEntity jrv) {
        this.jrv = jrv;
    }

    public String nuevaJrv() {
        short estado = 1;
        jrv.setEstado(estado);
        jrv.setIdElecciones(eleccionesModel.obtenerEleccionActiva());
        
        if (jrv.getIdSecretario().getIdCiudadano() == jrv.getIdPresidente().getIdCiudadano().getIdCiudadano()) {
            JsfUtils.addErrorMessage("vocal", "Debe seleccionar otro secretario. Este ciudadano ya tiene un cargo");
            return null;
        }
        
        if (jrv.getIdPresidente().getIdCiudadano().getIdCiudadano() == jrv.getIdVocal().getIdCiudadano()) {
            JsfUtils.addErrorMessage("vocal", "Debe seleccionar otro vocal. Este ciudadano ya tiene un cargo");
            return null;
        }
        
        if (jrv.getIdSecretario().getIdCiudadano() == jrv.getIdVocal().getIdCiudadano()) {
            JsfUtils.addErrorMessage("vocal", "Debe seleccionar otro vocal. Este ciudadano ya tiene un cargo");
            return null;
        }
        if (jrvModel.insertarJrv(jrv) == 0) {
            JsfUtils.addErrorMessage("idJrv", "No se pudo ingresar la JRV");
            return null;
        }
        
        
        jrv = new JrvEntity();
        JsfUtils.addFlashMessage("exito", "JRV ingresada con exito");
        return "/adminDepartamental/ListaJrv?faces-redirect=true";
    }

    public String modificarJrv() {
        
        jrv.setIdElecciones(eleccionesModel.obtenerEleccionActiva());

        if (jrv.getIdSecretario().getIdCiudadano() == jrv.getIdVocal().getIdCiudadano()) {
            JsfUtils.addErrorMessage("vocal", "Debe seleccionar otro vocal. Este ciudadano ya tiene un cargo");
            return null;
        }
        if (jrvModel.modificarJrv(jrv) == 0) {
            JsfUtils.addErrorMessage("idJrv", "No se pudo modificar la JRV");
            return null;
        }
        jrv = new JrvEntity();
        JsfUtils.addFlashMessage("exito", "JRV modificada con exito");
        return "/adminDepartamental/ListaJrv?faces-redirect=true";
    }

    public String obtenerJrv() {

        int id = Integer.parseInt(JsfUtils.getRequest().getParameter("id"));
        jrv = jrvModel.obtenerJrv(id);
        System.out.println(jrv.getIdCentroVotacion().getIdMunicipio().getIdMunicipio());
        return "/adminDepartamental/ModificarJrv";

    }
    
    public void obtenerListaCiudadanosJrv(String id) {

        
        int idJrv = Integer.parseInt(id);
        listaDetalleCiudadanoJrv = detalleCiudadanoJrvModel.listaCiudadanosJrv(idJrv);        
    }

    public List<DetalleCiudadanoJrvEntity> getListaDetalleCiudadanoJrv() {
        return listaDetalleCiudadanoJrv;
    }
    
    

}
