
package sv.edu.udb.www.managed_beans;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.servlet.http.HttpServletRequest;
import sv.edu.udb.www.entities.CentroVotacionEntity;
import sv.edu.udb.www.entities.MunicipioEntity;
import sv.edu.udb.www.model.CentroVotacionesModel;
import sv.edu.udb.www.model.MunicipiosModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author Rodriguez
 */
@Named(value = "centroVotacionBean")
@Dependent
public class CentroVotacionBean {

    @EJB
    private MunicipiosModel municipiosModel;

    

    @EJB
    private CentroVotacionesModel centroVotacionesModel;
    
    List<CentroVotacionEntity> listaCentroVotacion;
    
    List<MunicipioEntity> listaMunicipios;
    
    private CentroVotacionEntity centro = new CentroVotacionEntity();
    
    public CentroVotacionBean() {
    }

    public List<CentroVotacionEntity> getListaCentroVotacion() {
        HttpServletRequest request = JsfUtils.getRequest();
        listaCentroVotacion = centroVotacionesModel.listarCentroVotacionesDepartamento(Integer.parseInt(request.getSession().getAttribute("departamento").toString()));
        return listaCentroVotacion;
    }

    public List<MunicipioEntity> getListaMunicipios() {
        HttpServletRequest request = JsfUtils.getRequest();
        listaMunicipios = municipiosModel.listarMunicipiosDepartamento(Integer.parseInt(request.getSession().getAttribute("departamento").toString()));
        return listaMunicipios;
    }
   

    public CentroVotacionEntity getCentro() {
        return centro;
    }

    public void setCentro(CentroVotacionEntity centro) {
        this.centro = centro;
    }
    
    
    
}
