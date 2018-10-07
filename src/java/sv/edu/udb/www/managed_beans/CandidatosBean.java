/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.File;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import sv.edu.udb.www.entities.CandidatoEntity;
import sv.edu.udb.www.entities.MunicipioEntity;
import sv.edu.udb.www.entities.PartidosEntity;
import sv.edu.udb.www.model.CandidatosModel;
import sv.edu.udb.www.model.MunicipiosModel;
import sv.edu.udb.www.model.PartidosModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author ivanm
 */
@Named(value = "candidatosBean")
@RequestScoped
public class CandidatosBean {

    @EJB
    private PartidosModel partidosModel;

    @EJB
    private MunicipiosModel municipiosModel;

    @EJB
    private CandidatosModel candidatosModel;   
      
    
    List <CandidatoEntity> listaCandidatos;
    
    List <MunicipioEntity> listaMunicipios;
    
    List <PartidosEntity> listaPartidos;
    
    private CandidatoEntity candidato = new CandidatoEntity();
    /**
     * Creates a new instance of CandidatosBean
     */
    public CandidatosBean() {
        
    }

    public List<CandidatoEntity> getListaCandidatos() {
        listaCandidatos=candidatosModel.listarCandidatos();
        return listaCandidatos;
    }

    public List<MunicipioEntity> getListaMunicipios() {
        listaMunicipios=municipiosModel.listarMunicipios();
        return listaMunicipios;
    }

    public List<PartidosEntity> getListaPartidos() {
        listaPartidos=partidosModel.listarPartidos();
        return listaPartidos;
    }        

    public CandidatoEntity getCandidato() {
        return candidato;
    }

    public void setCandidato(CandidatoEntity candidato) {
        this.candidato = candidato;
    }

    public String nuevoCandidatoPresidencial(){
        candidato.setIdMunicipio(null);
        if(candidatosModel.insertarCandidadto(candidato)==0){
            JsfUtils.addErrorMessage("idCandidatos", "Ya existe un cadidato con este id");
            return null;
        }
        JsfUtils.addFlashMessage("exito", "Candidato a presidente ingresado con exito");
        return "/adminGeneral/ListaCandidatos?faces-redirect=true";
    }
    
}
