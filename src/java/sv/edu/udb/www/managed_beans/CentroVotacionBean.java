
package sv.edu.udb.www.managed_beans;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import sv.edu.udb.www.entities.CentroVotacionEntity;
import sv.edu.udb.www.model.CentroVotacionesModel;

/**
 *
 * @author Rodriguez
 */
@Named(value = "centroVotacionBean")
@Dependent
public class CentroVotacionBean {

    @EJB
    private CentroVotacionesModel centroVotacionesModel;

    
    List<CentroVotacionEntity> listaCentroVotacion;
    
    private CentroVotacionEntity centro = new CentroVotacionEntity();
    
    public CentroVotacionBean() {
    }

    public List<CentroVotacionEntity> getListaCentroVotacion() {
        listaCentroVotacion = centroVotacionesModel.listarCentroVotaciones();
        return listaCentroVotacion;
    }

    public CentroVotacionEntity getCentro() {
        return centro;
    }

    public void setCentro(CentroVotacionEntity centro) {
        this.centro = centro;
    }
    
    
    
}
