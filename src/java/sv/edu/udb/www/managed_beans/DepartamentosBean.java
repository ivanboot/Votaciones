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
import sv.edu.udb.www.model.DepartamentosModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author ivanm
 */
@Named(value = "departamentosBean")
@RequestScoped
public class DepartamentosBean {

    @EJB
    private DepartamentosModel departamentosModel;

    List<DepartamentoEntity> listaDepartamento;
    
    DepartamentoEntity departamento = new DepartamentoEntity();
    
    public DepartamentosBean() {
        
    }

    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoEntity departamento) {
        this.departamento = departamento;
    }

    public List<DepartamentoEntity> getListaDepartamento() {
        listaDepartamento=departamentosModel.listarDepartamentos();
        return listaDepartamento;
    }
    
    public String nuevoDepartamento(){
        if(departamentosModel.insertarDepartamento(departamento)==0){
            JsfUtils.addErrorMessage("departamento", "Ya existe un departamento con ese nombre");
            return null;
        }
        JsfUtils.addFlashMessage("exito", "Departamento agregado con exito");
        return "/adminGeneral/ListaDepartamentos?faces-redirect=true";
    }
    
    public String obtenerDepartamento(){
        int codigo = Integer.parseInt(JsfUtils.getRequest().getParameter("codigo"));
        departamento=departamentosModel.obtenerDepartamento(codigo);
        return "/adminGeneral/ModificarDepartamento";
    }
    
    public String modificarDepartamento(){
        if(departamentosModel.modificarDepartamento(departamento)==0){
            JsfUtils.addErrorMessage("departamento", "Ya existe un departamento con ese nombre");
            return null;
        }
        JsfUtils.addFlashMessage("exito", "Departamento modificado con exito");
        return "/adminGeneral/ListaDepartamentos?faces-redirect=true";
    }
}
