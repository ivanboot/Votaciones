package sv.edu.udb.www.managed_beans;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import sv.edu.udb.www.entities.UsuarioEntity;
import sv.edu.udb.www.model.UsuariosModel;
import sv.edu.udb.www.utils.JsfUtils;

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

    public String iniciarSesion() {
        UsuarioEntity user = usuariosModel.verificarCredenciales(correo, contra);
        if (user == null) {
            JsfUtils.addErrorMessage(null, "Correo y/o contraseña incorrecta");
            return null;
        } else {
            HttpServletRequest request = JsfUtils.getRequest();
            request.getSession().setAttribute("correo", correo);
            request.getSession().setAttribute("rol", user.getIdTipoUsuario().getIdTipoUsuario());
            
            if (user.getIdTipoUsuario().getIdTipoUsuario() == 1) {
                return "/adminGeneral/InicioAdminG?faces-redirect=true";
            } else if (user.getIdTipoUsuario().getIdTipoUsuario() == 2) {
                request.getSession().setAttribute("departamento", user.getIdCiudadano().getIdCentroVotacion().getIdMunicipio().getIdDepartamento().getIdDepartamento());
                return "/adminDepartamental/InicioAdminD?faces-redirect=true";
            } else if (user.getIdTipoUsuario().getIdTipoUsuario() == 3) {
                return "/RNPN/InicioRNPN?faces-redirect=true";
            } else if (user.getIdTipoUsuario().getIdTipoUsuario() == 4) {
               return "/GFJRV/InicioGJRV?faces-redirect=true";
            } else {
                JsfUtils.addErrorMessage(null, "Correo y/o contraseña incorrecta");
                return null;
            }
        }
    }

    public String cerrarSesion() {
        JsfUtils.getRequest().getSession().invalidate();
        return "/index?faces-redirect=true";
    }

}
