/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import sv.edu.udb.www.entities.UsuarioEntity;
import sv.edu.udb.www.model.UsuariosModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author ivanm
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    @EJB
    private UsuariosModel usuariosModel;
    
    private String correo;
    private String contra;
    
    public LoginBean() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
    
   
    
    public String iniciarSesion(){
        UsuarioEntity user= usuariosModel.verificarCredenciales(correo, contra);
        if(user==null){
            JsfUtils.addErrorMessage(null, "Correo y/o contraseña incorrecta");
            return null;
        }
        else{
            HttpServletRequest request= JsfUtils.getRequest();
            request.getSession().setAttribute("correo", correo);
            request.getSession().setAttribute("rol",user.getIdTipoUsuario().getIdTipoUsuario());
            if(user.getIdTipoUsuario().getIdTipoUsuario() == 1){
                return "/adminGeneral/InicioAdminG?faces-redirect=true";
            }
            else{
                return null;
            }
        }
    }
    
    public String cerrarSesion(){
        JsfUtils.getRequest().getSession().invalidate();
        return "/login?faces-redirect=true";
    }
    
}
