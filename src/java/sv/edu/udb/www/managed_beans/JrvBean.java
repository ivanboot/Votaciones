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
import javax.faces.view.ViewScoped;
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
@ViewScoped
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

    List<JrvEntity> listaJrvs;

    List<CiudadanoEntity> listaCiudadanos;

    List<UsuarioEntity> listaUsuarios;

    List<EleccionEntity> listaElecciones;

    List<CentroVotacionEntity> listaCentroVotaciones;

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

        try {
            return ciudadanosModel.listarCiudadanosMunicipio(1);
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
        return centroVotacionesModel.listarCentroVotaciones();
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
        JsfUtils.addFlashMessage("exito", "JRV modificada con exito");
        return "/adminDepartamental/ListaJrv?faces-redirect=true";
    }

    public String obtenerJrv() {

        int id = Integer.parseInt(JsfUtils.getRequest().getParameter("id"));
        jrv = jrvModel.obtenerJrv(id);
        return "/adminDepartamental/ModificarJrv?faces-redirect=true";

    }

}
