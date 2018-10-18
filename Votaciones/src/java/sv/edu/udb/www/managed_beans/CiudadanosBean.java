package sv.edu.udb.www.managed_beans;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import sv.edu.udb.www.entities.CentroVotacionEntity;
import sv.edu.udb.www.entities.CiudadanoEntity;
import sv.edu.udb.www.model.CentroVotacionesModel;
import sv.edu.udb.www.model.CiudadanosModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author Rodriguez
 */
@Named(value = "ciudadanosBean")
@RequestScoped
public class CiudadanosBean {

    @EJB
    private CiudadanosModel ciudadanosModel;

    //Inyecciones para poder llenar ComboBox
    @EJB
    private CentroVotacionesModel centroVotacionesModel;

    List<CiudadanoEntity> listaCiudadanos;

    private Part imagen;

    private CiudadanoEntity ciudadano = new CiudadanoEntity();

    public CiudadanosBean() {
    }

    public List<CiudadanoEntity> getListaCiudadanos() {
        listaCiudadanos = ciudadanosModel.listarCiudadanos();
        return listaCiudadanos;
    }

    //Para llenar los comboBOX
    public List<CentroVotacionEntity> getListaCentroVotacion() {
        return centroVotacionesModel.listarCentroVotaciones();
    }

    public CiudadanoEntity getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(CiudadanoEntity ciudadano) {
        this.ciudadano = ciudadano;
    }

    public Part getImagen() {
        return imagen;
    }

    public void setImagen(Part imagen) {
        this.imagen = imagen;
    }

    public String insertarCiudadano() {
        try {
            InputStream input = imagen.getInputStream();
            ciudadano.setUrlImagen(imagen.getSubmittedFileName());
            
            if (ciudadanosModel.insertarCiudadano(ciudadano) > 0) {
                String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

                Files.copy(input, new File(path + "/resources/ciudadanos/", ciudadano.getUrlImagen()).toPath());

                JsfUtils.addFlashMessage("exito", "Ciudadano registrado exitosamente");
            } else {
                JsfUtils.addFlashMessage("fracaso", "No se pudo registrar al ciudadano");
            }

            return "/RNPN/listaCiudadanos?faces-redirect=true";
        } catch (Exception e) {
            JsfUtils.addErrorMessage("idCiudadano", e.toString());
            return null;
        }
    }
    
    public String obtenerCuidadano(){
        int codigo = Integer.parseInt(JsfUtils.getRequest().getParameter("codigo"));
        ciudadano = ciudadanosModel.obtenerCiudadano(codigo);
        return "/RNPN/modificarCiudadano";
    }
    
    public String modificarCiudadano(){
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        try {
            InputStream input = imagen.getInputStream();
            ciudadano.setUrlImagen(imagen.getSubmittedFileName());
            Files.copy(input, new File(path + "/resources/ciudadanos", ciudadano.getUrlImagen()).toPath());
        } catch (Exception e) {
            JsfUtils.addErrorMessage("idCiudadano", e.toString());
            return null;
        }
        
        if(ciudadanosModel.modificarCiudadano(ciudadano) == 0){
            JsfUtils.addErrorMessage("idCiudadano", "Ya existe un Ciudadano con este ID");
            return null;
        }
        JsfUtils.addErrorMessage("exito", "Cuidadano modificado con exito");
        return "/RNPN/listaCiudadanos?faces-redirect=true";
    }

}
