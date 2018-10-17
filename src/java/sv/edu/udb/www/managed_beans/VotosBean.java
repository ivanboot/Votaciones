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
import sv.edu.udb.www.entities.VotoEntity;
import sv.edu.udb.www.model.EleccionesModel;
import sv.edu.udb.www.model.VotosModel;

/**
 *
 * @author ivanm
 */
@Named(value = "votosBean")
@RequestScoped
public class VotosBean {

    @EJB
    private EleccionesModel eleccionesModel;

    @EJB
    private VotosModel votosModel;

    List<VotoEntity> listaVotosTotales;    
    
    VotoEntity votos = new VotoEntity(); 
    
    public VotosBean() {
        
    }        

    public VotoEntity getVotos() {
        return votos;
    }

    public void setVotos(VotoEntity votos) {
        this.votos = votos;
    }

    public List<VotoEntity> getListaVotosTotales(int eleccion) {
        listaVotosTotales = votosModel.listarVotacionTotal(eleccion);
        return listaVotosTotales;
    }
    
    
}
