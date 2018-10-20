/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.model.chart.PieChartModel;
import sv.edu.udb.www.entities.DepartamentoEntity;
import sv.edu.udb.www.entities.JrvEntity;
import sv.edu.udb.www.entities.MunicipioEntity;
import sv.edu.udb.www.entities.VotoEntity;
import sv.edu.udb.www.model.DepartamentosModel;
import sv.edu.udb.www.model.EleccionesModel;
import sv.edu.udb.www.model.JrvModel;
import sv.edu.udb.www.model.MunicipiosModel;
import sv.edu.udb.www.model.VotosModel;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author ivanm
 */
@Named(value = "votosBean")
@RequestScoped
public class VotosBean implements Serializable {

    @EJB
    private JrvModel jrvModel;

    @EJB
    private MunicipiosModel municipiosModel;

    @EJB
    private DepartamentosModel departamentosModel;

    @EJB
    private EleccionesModel eleccionesModel;

    @EJB
    private VotosModel votosModel;
    
    List<JrvEntity> listaJrv;
    
    List<MunicipioEntity> listaMunicipios;
    
    List<DepartamentoEntity> listaDepartamentos;
    
    List<Object[]> listaVotosTotales;
    List<Object[]> listaVotosDepartamentales;
    List<Object[]> listaVotosMunicipales;
    List<Object[]> listaVotosJrv;

    private PieChartModel grafico;
    private PieChartModel graficoDepartamental;
    private PieChartModel graficoMunicipal;
    private PieChartModel graficoJrv;

    VotoEntity votos = new VotoEntity();

    public VotosBean() {

    }

    @PostConstruct
    public void init() {
        getListaVotosTotales();
        createPieModels();
    }

    public PieChartModel getGrafico() {
        return grafico;
    }

    public void setGrafico(PieChartModel grafico) {
        this.grafico = grafico;
    }

    public PieChartModel getGraficoDepartamental() {
        return graficoDepartamental;
    }

    public void setGraficoDepartamental(PieChartModel graficoDepartamental) {
        this.graficoDepartamental = graficoDepartamental;
    }

    public PieChartModel getGraficoMunicipal() {
        return graficoMunicipal;
    }

    public void setGraficoMunicipal(PieChartModel graficoMunicipal) {
        this.graficoMunicipal = graficoMunicipal;
    }

    public PieChartModel getGraficoJrv() {
        return graficoJrv;
    }

    public void setGraficoJrv(PieChartModel graficoJrv) {
        this.graficoJrv = graficoJrv;
    }        
    
    public VotoEntity getVotos() {
        return votos;
    }

    public void setVotos(VotoEntity votos) {
        this.votos = votos;
    }

    public List<Object[]> getListaVotosTotales() {
        int eleccion = Integer.parseInt(JsfUtils.getRequest().getParameter("codigo"));
        listaVotosTotales = votosModel.listarVotacionTotal(eleccion);
        return listaVotosTotales;
    }
    
    public List<Object[]> getListaVotosDepartamentales() {
        int eleccion = Integer.parseInt(JsfUtils.getRequest().getParameter("codigo"));
        listaVotosTotales = votosModel.listarVotacionTotal(eleccion);
        return listaVotosTotales;
    }
    public List<Object[]> getListaVotosMunicipales() {
        int eleccion = Integer.parseInt(JsfUtils.getRequest().getParameter("codigo"));
        listaVotosTotales = votosModel.listarVotacionTotal(eleccion);
        return listaVotosTotales;
    }
    public List<Object[]> getListaVotosJrvs() {
        int eleccion = Integer.parseInt(JsfUtils.getRequest().getParameter("codigo"));
        listaVotosTotales = votosModel.listarVotacionTotal(eleccion);
        return listaVotosTotales;
    }
    
    public String obtenerEleccionPublica() {
        return "/resultados/listaResultado";
    }

    private void createPieModels() {
        crearGrafico();
    }

    private void crearGrafico() {       
        grafico = new PieChartModel();        
        for (Object[] a : listaVotosTotales) {
            grafico.set(a[0].toString(), Integer.parseInt(a[1].toString()));
        }

        grafico.setTitle("Resultados a nivel nacional");
        grafico.setLegendPosition("e");
        grafico.setShadow(true);

    }

}
