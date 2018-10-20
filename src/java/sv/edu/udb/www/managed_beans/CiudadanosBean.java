package sv.edu.udb.www.managed_beans;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;
import sv.edu.udb.www.entities.CentroVotacionEntity;
import sv.edu.udb.www.entities.CiudadanoEntity;
import sv.edu.udb.www.entities.DepartamentoEntity;
import sv.edu.udb.www.entities.MunicipioEntity;
import sv.edu.udb.www.model.CentroVotacionesModel;
import sv.edu.udb.www.model.CiudadanosModel;
import sv.edu.udb.www.model.DepartamentosModel;
import sv.edu.udb.www.model.MunicipiosModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author Rodriguez
 */
@Named(value = "ciudadanosBean")
@RequestScoped
public class CiudadanosBean implements Serializable{

    @EJB
    private CiudadanosModel ciudadanosModel;
    
    //Inyecciones para poder llenar ComboBox
    @EJB
    private CentroVotacionesModel centroVotacionesModel;
    
    @EJB
    private MunicipiosModel municipiosModel;
    @EJB
    private DepartamentosModel deparatamentosModel;

    List<CiudadanoEntity> listaCiudadanos;

    private Part imagen;

    private CiudadanoEntity ciudadano = new CiudadanoEntity();
    private MunicipioEntity municipio = new MunicipioEntity();
    private DepartamentoEntity departamento = new DepartamentoEntity();

    public CiudadanosBean() {
    }

    public MunicipioEntity getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioEntity municipio) {
        this.municipio = municipio;
    }

    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoEntity departamento) {
        this.departamento = departamento;
    }

    
    public List<CiudadanoEntity> getListaCiudadanos() {
        listaCiudadanos = ciudadanosModel.listarCiudadanos();
        return listaCiudadanos;
    }

    //Para llenar los comboBOX
    public List<CentroVotacionEntity> getListaCentroVotacion() {
        try {
            
            return centroVotacionesModel.listarCentroVotaciones();
        } catch (Exception e) {
            return null;
        }
        
    }
    
   public List<DepartamentoEntity> getListaDepartamentos(){
       return deparatamentosModel.listarDepartamentos();
   }

    public List<MunicipioEntity> getListarMunicipiosDepartamento(){
        try {
            
            return municipiosModel.listarMunicipiosDepartamento(Integer.parseInt(departamento.getIdDepartamento().toString()));
        } catch (Exception e) {
            return null;
        }   
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
