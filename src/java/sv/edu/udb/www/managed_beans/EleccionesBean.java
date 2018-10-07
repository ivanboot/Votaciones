/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import sv.edu.udb.www.entities.EleccionEntity;
import sv.edu.udb.www.entities.TipoEleccionEntity;
import sv.edu.udb.www.model.EleccionesModel;
import sv.edu.udb.www.model.TipoEleccionModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author ivanm
 */
@Named(value = "eleccionesBean")
@RequestScoped
public class EleccionesBean {

    @EJB
    private TipoEleccionModel tipoEleccionModel;

    @EJB
    private EleccionesModel eleccionesModel;

    List <TipoEleccionEntity> listaTipoEleccion;
    
    List <EleccionEntity> listaElecciones;
    
    private EleccionEntity eleccion = new EleccionEntity();
    /**
     * Creates a new instance of EleccionesBean
     */
    public EleccionesBean() {
     
    }

    public EleccionEntity getEleccion() {
        return eleccion;
    }

    public void setEleccion(EleccionEntity eleccion) {
        this.eleccion = eleccion;
    }

    public List<TipoEleccionEntity> getListaTipoEleccion() {
        listaTipoEleccion=tipoEleccionModel.listarTipoEleccion();
        return listaTipoEleccion;
    }

    public List<EleccionEntity> getListaElecciones() {
        listaElecciones = eleccionesModel.listarElecciones();
        return listaElecciones;
    }
    
    public String nuevaEleccion(){
        eleccion.setEstado(Short.parseShort("1"));
        if(eleccion.getFechaInicioCandidato().before(new Date())){
            JsfUtils.addErrorMessage("fechaInicioCandidato", "La fecha inicial no puede ser menor a la fecha actual");
            return null;
        }
        if(eleccion.getFechaFinCandidato().before(eleccion.getFechaInicioCandidato())){
            JsfUtils.addErrorMessage("fechaFinCandidato", "La fecha limite no puede ser menor a la fecha inicial");
            return null;
        }
        
        if(eleccion.getFechaFinCandidato().after(eleccion.getFechaEleccion())){
            JsfUtils.addErrorMessage("fechaFinCandidato","La fecha limite no puede ser mayor a la fecha de eleccion");
            return null;
        }
        if(eleccion.getFechaEleccion().before(eleccion.getFechaInicioCandidato())){
            JsfUtils.addErrorMessage("fechaEleccion","La fecha de eleccion no puede ser menor a la fecha Inicial");
            return null;
        }
        if(eleccion.getFechaEleccion().before(eleccion.getFechaFinCandidato())){
            JsfUtils.addErrorMessage("fechaEleccion","La fecha de eleccion no puede ser menor a la fecha limite");
            return null;
        }
        
        if(eleccionesModel.insertarEleccion(eleccion)==0){
            JsfUtils.addErrorMessage("idEleccion", "No se pudo ingresar la eleccion");
        }else{
            JsfUtils.addFlashMessage("exito", "Proceso de eleccion iniciado con exito");
        }
        return "/adminGeneral/ListaElecciones?faces-redirect=true";
    }
    
}
