/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.services;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Sergio
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(sv.edu.udb.www.services.CandidatoEntityFacadeREST.class);
        resources.add(sv.edu.udb.www.services.CentroVotacionEntityFacadeREST.class);
        resources.add(sv.edu.udb.www.services.CiudadanoEntityFacadeREST.class);
        resources.add(sv.edu.udb.www.services.DepartamentoEntityFacadeREST.class);
        resources.add(sv.edu.udb.www.services.DetalleCandidatoEleccionEntityFacadeREST.class);
        resources.add(sv.edu.udb.www.services.DetalleCiudadanoEleccionEntityFacadeREST.class);
        resources.add(sv.edu.udb.www.services.DetalleCiudadanoJrvEntityFacadeREST.class);
        resources.add(sv.edu.udb.www.services.EleccionEntityFacadeREST.class);
        resources.add(sv.edu.udb.www.services.JrvEntityFacadeREST.class);
        resources.add(sv.edu.udb.www.services.MunicipioEntityFacadeREST.class);
        resources.add(sv.edu.udb.www.services.PartidosEntityFacadeREST.class);
        resources.add(sv.edu.udb.www.services.TipoEleccionEntityFacadeREST.class);
        resources.add(sv.edu.udb.www.services.TipoUsuarioEntityFacadeREST.class);
        resources.add(sv.edu.udb.www.services.UsuarioEntityFacadeREST.class);
        resources.add(sv.edu.udb.www.services.VotoEntityFacadeREST.class);
    }
    
}
