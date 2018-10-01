
package sv.edu.udb.www.managed_beans;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
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
@Dependent
public class CiudadanosBean {

    @EJB
    private CiudadanosModel ciudadanosModel;
    
    //Inyecciones para poder llenar ComboBox
    @EJB
    private CentroVotacionesModel centroModel;       
    
    List<CiudadanoEntity> listaCiudadanos;
    
    private CiudadanoEntity ciudadano = new CiudadanoEntity();
    
    public CiudadanosBean() {
    }

    public List<CiudadanoEntity> getListaCiudadanos() {
        listaCiudadanos = ciudadanosModel.listarCiudadanos();
        return listaCiudadanos;
    }
    
    //Para llenar los comboBOX
    public List<CentroVotacionEntity> getListaCentroVotacion(){
        return centroModel.listarCentroVotaciones();
    }

    public CiudadanoEntity getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(CiudadanoEntity ciudadano) {
        this.ciudadano = ciudadano;
    }
    
    public String insertarCiudadano(){
        if(ciudadanosModel.insertarCiudadano(ciudadano) > 0){
            JsfUtils.addFlashMessage("exito", "Ciudadano registrado exitosamente");
        }
        return "/RNPN/listaCiudadanos?faces-rediret=true";
    }
    
    
    
    
    
}
