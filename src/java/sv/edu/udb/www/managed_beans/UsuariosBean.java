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
import sv.edu.udb.www.entities.TipoUsuarioEntity;
import sv.edu.udb.www.entities.UsuarioEntity;
import sv.edu.udb.www.model.TipoUsuarioModel;
import sv.edu.udb.www.model.UsuariosModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author ivanm
 */
@Named(value = "usuariosBean")
@RequestScoped
public class UsuariosBean {

    @EJB
    private TipoUsuarioModel tipoUsuarioModel;

    @EJB
    private UsuariosModel usuariosModel;
        
    List<UsuarioEntity> listaUsuarios;
    
    List<TipoUsuarioEntity> listaTipoUsuario;
    
    UsuarioEntity usuario = new UsuarioEntity();
    
    public UsuariosBean() {
    }

    public List<UsuarioEntity> getListaUsuarios() {
        listaUsuarios=usuariosModel.listarUsuarios();
        return listaUsuarios;
    }

    public List<TipoUsuarioEntity> getListaTipoUsuario() {
        listaTipoUsuario=tipoUsuarioModel.listarTipoUsuario();
        return listaTipoUsuario;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
    
    public String nuevoUsuario(){
        if(usuariosModel.insertarUsuario(usuario)==0){
            JsfUtils.addErrorMessage("idUsuario", "No se pudo ingresar el usuario");
            return null;
        }
        JsfUtils.addFlashMessage("exito", "Usuario ingresado con exito");
        return "/adminGeneral/ListaUsuarios?faces-redirect=true";
    }
    
    public String obtenerUsuario(){
        int codigo = Integer.parseInt(JsfUtils.getRequest().getParameter("codigo"));
        usuario=usuariosModel.obtenerUsuario(codigo);
        return "/adminGeneral/ModificarUsuario";
    }
    
    public String modificarUsuario(){
        if(usuariosModel.modificarUsuario(usuario)==0){
            JsfUtils.addErrorMessage("idUsuario", "No se pudo modificar el usuario");
            return null;
        }
        JsfUtils.addFlashMessage("exito", "Usuario modificado con exito");
        return "/adminGeneral/ListaUsuarios?faces-redirect=true";
    }
}
