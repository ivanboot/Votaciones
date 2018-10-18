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
import sv.edu.udb.www.entities.PartidosEntity;
import sv.edu.udb.www.model.PartidosModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author ivanm
 */
@Named(value = "partidosBean")
@RequestScoped
public class PartidosBean {

    @EJB
    private PartidosModel partidosModel;
    
    List<PartidosEntity> listaPartidos;
    
    PartidosEntity partido = new PartidosEntity();
    
    public PartidosBean() {
    }

    public PartidosEntity getPartido() {
        return partido;
    }

    public void setPartido(PartidosEntity partido) {
        this.partido = partido;
    }

    public List<PartidosEntity> getListaPartidos() {
        listaPartidos=partidosModel.listarPartidos();
        return listaPartidos;
    }
    
    public String nuevoPartido(){
        if(partidosModel.insertarPartido(partido)==0){
            JsfUtils.addErrorMessage("partido", "Ya existe un partido con ese nombre");
            return null;
        }
        JsfUtils.addFlashMessage("exito", "Partido ingresado con exito");
        return "/adminGeneral/ListaPartidos?faces-redirect=true";
    }
    
    public String obtenerPartido(){
        int codigo = Integer.parseInt(JsfUtils.getRequest().getParameter("codigo"));
        partido = partidosModel.obtenerPartido(codigo);
        return "/adminGeneral/ModificarPartido";
    }
    
    public String modificarPartido(){
        if(partidosModel.modificarPartido(partido)==0){
            JsfUtils.addErrorMessage("partido", "Ya existe un partido con ese nombre");
            return null;
        }
        JsfUtils.addFlashMessage("exito", "Partido modificado con exito");
        return "/adminGeneral/ListaPartidos?faces-redirect=true";
    }
}
