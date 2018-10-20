package sv.edu.udb.www.managed_beans;

import java.io.File;
import java.nio.file.Files;
import java.io.InputStream;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import sv.edu.udb.www.entities.CandidatoEntity;
import sv.edu.udb.www.entities.DetalleCandidatoEleccionEntity;
import sv.edu.udb.www.entities.MunicipioEntity;
import sv.edu.udb.www.entities.PartidosEntity;
import sv.edu.udb.www.model.CandidatosModel;
import sv.edu.udb.www.model.DetalleCandidatoEleccionModel;
import sv.edu.udb.www.model.EleccionesModel;
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
    private DetalleCandidatoEleccionModel detalleCandidatoEleccionModel;

    @EJB
    private EleccionesModel eleccionesModel;

    @EJB
    private PartidosModel partidosModel;

    @EJB
    private MunicipiosModel municipiosModel;

    @EJB
    private CandidatosModel candidatosModel;
    
    List<CandidatoEntity> listaCandidatos;

    List<MunicipioEntity> listaMunicipios;

    List<PartidosEntity> listaPartidos;

    private CandidatoEntity candidato = new CandidatoEntity();

    private Part imagen;

    public CandidatosBean() {

    }

    public List<CandidatoEntity> getListaCandidatos() {
        listaCandidatos = candidatosModel.listarCandidatos();
        return listaCandidatos;
    }

    public List<MunicipioEntity> getListaMunicipios() {
        listaMunicipios = municipiosModel.listarMunicipios();
        return listaMunicipios;
    }

    public List<PartidosEntity> getListaPartidos() {
        listaPartidos = partidosModel.listarPartidos();
        return listaPartidos;
    }

    public CandidatoEntity getCandidato() {
        return candidato;
    }

    public void setCandidato(CandidatoEntity candidato) {
        this.candidato = candidato;
    }

    public Part getImagen() {
        return imagen;
    }

    public void setImagen(Part imagen) {
        this.imagen = imagen;
    }

    public String nuevoCandidatoPresidencial() {
        if (eleccionesModel.listaEleccionesDisponibles().isEmpty()) {
            JsfUtils.addFlashMessage("fracaso", "No hay elecciones activas disponibles para ingresar candidatos");
            return "/adminGeneral/ListaCandidatos?faces-redirect=true";
        }

        if (imagen.getSubmittedFileName().toString().endsWith("jpg") || imagen.getSubmittedFileName().toString().endsWith("jpeg") || imagen.getSubmittedFileName().toString().endsWith("png")) {
            
        }else{
            JsfUtils.addErrorMessage("idCandidatos", "Debe seleccionar un archivo de imagen .jpg, .jpeg o .png");
            return null;
        }

        candidato.setIdMunicipio(null);
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

        try {

            InputStream input = imagen.getInputStream();
            candidato.setUrlFoto(imagen.getSubmittedFileName());
            Files.copy(input, new File(path + "/resources/candidatos/", candidato.getUrlFoto()).toPath());
        } catch (Exception e) {
            JsfUtils.addErrorMessage("idCandidatos", e.toString());
            return null;
        }
        
        if(detalleCandidatoEleccionModel.listarCandidatosPresidentes(Integer.parseInt(candidato.getIdPartido().getIdPartido().toString()))!=null){
            JsfUtils.addErrorMessage("idCandidatos", "Este partido ya tiene un candidato");
        }
        

        if (candidatosModel.insertarCandidadto(candidato) == 0) {
            JsfUtils.addErrorMessage("idCandidatos", "Ya existe un cadidato con este id");
            return null;
        }
        
        DetalleCandidatoEleccionEntity detalle = new DetalleCandidatoEleccionEntity();
        
        detalle.setIdCandidatos(candidato);
        detalle.setIdEleccion(eleccionesModel.obtenerEleccionActiva());
        
        detalleCandidatoEleccionModel.insertarDetalle(detalle);
        
        JsfUtils.addFlashMessage("exito", "Candidato a presidente ingresado con exito");
        return "/adminGeneral/ListaCandidatos?faces-redirect=true";
    }

    public String hola() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        JsfUtils.addErrorMessage("idCandidatos", path);
        return null;
    }

    public String obtenerCandidato() {
        int codigo = Integer.parseInt(JsfUtils.getRequest().getParameter("codigo"));
        candidato = candidatosModel.obtenerCandidato(codigo);
        return "/adminGeneral/ModificarCandidato";
    }
    
    public String obtenerCandidato1() {
        int codigo = Integer.parseInt(JsfUtils.getRequest().getParameter("codigo"));
        candidato = candidatosModel.obtenerCandidato(codigo);
        return "/adminDepartamental/ModificarCandidato";
    }

    public String modificarCandidatoPresidencial() {

        candidato.setIdMunicipio(null);
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

        if (imagen.getSubmittedFileName().toString().endsWith("jpg") || imagen.getSubmittedFileName().toString().endsWith("jpeg") || imagen.getSubmittedFileName().toString().endsWith("png")) {
            
        }else{
            JsfUtils.addErrorMessage("idCandidatos", "Debe seleccionar un archivo de imagen .jpg, .jpeg o .png");
            return null;
        }

        try {
            InputStream input = imagen.getInputStream();
            candidato.setUrlFoto(imagen.getSubmittedFileName());
            Files.copy(input, new File(path + "/resources/candidatos/", candidato.getUrlFoto()).toPath());
        } catch (Exception e) {
            JsfUtils.addErrorMessage("idCandidatos", e.toString());
            return null;
        }
        
        if(detalleCandidatoEleccionModel.listarCandidatos(Integer.parseInt(candidato.getIdMunicipio().getIdMunicipio().toString()),Integer.parseInt(candidato.getIdPartido().getIdPartido().toString()))!=null){
            JsfUtils.addErrorMessage("idCandidatos", "Este partido ya tiene un candidato en el municipio seleccionado");
        }

        if (candidatosModel.modificarCandidato(candidato) == 0) {
            JsfUtils.addErrorMessage("idCandidatos", "Ya existe un cadidato con este id");
            return null;
        }
        
        
        
        JsfUtils.addFlashMessage("exito", "Candidato a presidente modificado con exito");
        return "/adminGeneral/ListaCandidatos?faces-redirect=true";
    }

    public List<MunicipioEntity> getListaMunicipiosDepartamento() {
        HttpServletRequest request = JsfUtils.getRequest();
        try {
            return municipiosModel.listarMunicipiosDepartamento(Integer.parseInt(request.getSession().getAttribute("departamento").toString()));
        } catch (Exception e) {
            return null;
        }

    }
    
    
    public String nuevoCandidatoMunicipal() {
        if (eleccionesModel.listaEleccionesDisponibles1().isEmpty()) {
            JsfUtils.addFlashMessage("fracaso", "No hay elecciones activas disponibles para ingresar candidatos");
            return "/adminGeneral/ListaCandidatos?faces-redirect=true";
        }

        if (imagen.getSubmittedFileName().toString().endsWith("jpg") || imagen.getSubmittedFileName().toString().endsWith("jpeg") || imagen.getSubmittedFileName().toString().endsWith("png")) {
            
        }else{
            JsfUtils.addErrorMessage("idCandidatos", "Debe seleccionar un archivo de imagen .jpg, .jpeg o .png");
            return null;
        }
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

        try {

            InputStream input = imagen.getInputStream();
            candidato.setUrlFoto(imagen.getSubmittedFileName());
            Files.copy(input, new File(path + "/resources/candidatos/", candidato.getUrlFoto()).toPath());
        } catch (Exception e) {
            JsfUtils.addErrorMessage("idCandidatos", e.toString());
            return null;
        }
        
        if(detalleCandidatoEleccionModel.listarCandidatos(Integer.parseInt(candidato.getIdMunicipio().getIdMunicipio().toString()),Integer.parseInt(candidato.getIdPartido().getIdPartido().toString()))!=null){
            JsfUtils.addErrorMessage("idCandidatos", "Este partido ya tiene un candidato en el municipio seleccionado");
        }

        if (candidatosModel.insertarCandidadto(candidato) == 0) {
            JsfUtils.addErrorMessage("idCandidatos", "Ya existe un cadidato con este id");
            return null;
        }
        
        DetalleCandidatoEleccionEntity detalle = new DetalleCandidatoEleccionEntity();
        
        detalle.setIdCandidatos(candidato);
        detalle.setIdEleccion(eleccionesModel.obtenerEleccionActiva());
        
        detalleCandidatoEleccionModel.insertarDetalle(detalle);
        
        JsfUtils.addFlashMessage("exito", "Candidato a presidente ingresado con exito");
        return "/adminGeneral/ListaCandidatos?faces-redirect=true";
    }
    
    public String modificarCandidatoMunicipal() {
        if (eleccionesModel.listaEleccionesDisponibles1().isEmpty()) {
            JsfUtils.addFlashMessage("fracaso", "No hay elecciones activas disponibles para modificar candidatos");
            return "/adminDepartamental/ListaCandidatos?faces-redirect=true";
        }

        if (imagen.getSubmittedFileName().toString().endsWith("jpg") || imagen.getSubmittedFileName().toString().endsWith("jpeg") || imagen.getSubmittedFileName().toString().endsWith("png")) {
            
        }else{
            JsfUtils.addErrorMessage("idCandidatos", "Debe seleccionar un archivo de imagen .jpg, .jpeg o .png");
            return null;
        }
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");

        try {

            InputStream input = imagen.getInputStream();
            candidato.setUrlFoto(imagen.getSubmittedFileName());
            Files.copy(input, new File(path + "/resources/candidatos/", candidato.getUrlFoto()).toPath());
        } catch (Exception e) {
            JsfUtils.addErrorMessage("idCandidatos", e.toString());
            return null;
        }
        
        if(detalleCandidatoEleccionModel.listarCandidatos(Integer.parseInt(candidato.getIdMunicipio().getIdMunicipio().toString()),Integer.parseInt(candidato.getIdPartido().getIdPartido().toString()))!=null){
            JsfUtils.addErrorMessage("idCandidatos", "Este partido ya tiene un candidato en el municipio seleccionado");
        }

        if (candidatosModel.modificarCandidato(candidato) == 0) {
            JsfUtils.addErrorMessage("idCandidatos", "Ya existe un cadidato con este id");
            return null;
        }

        JsfUtils.addFlashMessage("exito", "Candidato a presidente modificado con exito");
        return "/adminDepartamental/ListaCandidatos?faces-redirect=true";
    }

}
