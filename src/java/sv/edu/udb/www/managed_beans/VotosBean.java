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
import sv.edu.udb.www.entities.VotoEntity;
import sv.edu.udb.www.model.EleccionesModel;
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
    private EleccionesModel eleccionesModel;

    @EJB
    private VotosModel votosModel;

    List<Object[]> listaVotosTotales;

    private PieChartModel grafico;

    VotoEntity votos = new VotoEntity();

    public VotosBean() {

    }

    @PostConstruct
    public void init() {
        // getListaVotosTotales();
        createPieModels();
    }

    public PieChartModel getGrafico() {
        return grafico;
    }

    public void setGrafico(PieChartModel grafico) {
        this.grafico = grafico;
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

    public String obtenerEleccionPublica() {
        return "/resultados/listaResultado";
    }

    private void createPieModels() {
        createPieModel1();
    }

    private void createPieModel1() {
        int codigo = Integer.parseInt(JsfUtils.getRequest().getParameter("codigo"));
        grafico = new PieChartModel();
// 
        listaVotosTotales = votosModel.listarVotacionTotal(codigo);
        for (Object[] a : listaVotosTotales) {
            grafico.set(a[0].toString(), Integer.parseInt(a[1].toString()));
        }
//        listaVotosTotales.stream().forEach((record) -> {
//            String candidato = (String) record[0];
//            int cantidad = (Integer) record[1];
//            grafico.set(candidato, cantidad);
//        });

        grafico.setTitle("Simple Pie");
        grafico.setLegendPosition("w");
        grafico.setShadow(false);

    }

}
