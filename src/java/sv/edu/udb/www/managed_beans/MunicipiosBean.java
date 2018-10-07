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
import sv.edu.udb.www.entities.DepartamentoEntity;
import sv.edu.udb.www.entities.MunicipioEntity;
import sv.edu.udb.www.model.DepartamentosModel;
import sv.edu.udb.www.model.MunicipiosModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author ivanm
 */
@Named(value = "municipiosBean")
@RequestScoped
public class MunicipiosBean {

    @EJB
    private DepartamentosModel departamentosModel;

    @EJB
    private MunicipiosModel municipiosModel;

    List<MunicipioEntity> listaMunicipio;
    
    List<DepartamentoEntity> listaDepartamento;
    
    private MunicipioEntity municipio = new MunicipioEntity();
    
    public MunicipiosBean() {
    }

    public List<MunicipioEntity> getListaMunicipio() {
        listaMunicipio=municipiosModel.listarMunicipios();
        return listaMunicipio;
    }

    public List<DepartamentoEntity> getListaDepartamento() {
        listaDepartamento = departamentosModel.listarDepartamentos();
        return listaDepartamento;
    }

    public MunicipioEntity getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioEntity municipio) {
        this.municipio = municipio;
    }
    
    public String nuevoMunicipio(){
        if(municipiosModel.insertarMunicipio(municipio)==0){
            JsfUtils.addErrorMessage("municipio", "Ya existe un municipio con ese nombre");
            return null;
        }
        JsfUtils.addFlashMessage("exito", "Municipio ingresado con exito");
        return "/adminGeneral/ListaMunicipios?faces-redirect=true";
    }
}
