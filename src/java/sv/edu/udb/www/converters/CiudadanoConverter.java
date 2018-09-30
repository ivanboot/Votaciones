/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.converters;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sv.edu.udb.www.entities.CiudadanoEntity;
import sv.edu.udb.www.model.CiudadanosModel;

/**
 *
 * @author Ferh
 */
@FacesConverter(forClass=CiudadanoEntity.class)
public class CiudadanoConverter implements Converter{

    CiudadanosModel ciudadanosModel = lookupCiudadanosModelBean();
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value==null || value.isEmpty()){
            return null;
        }
        return ciudadanosModel.obtenerCiudadano(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value==null){
            return "";
        }
        
        if(value instanceof CiudadanoEntity){
            return ((CiudadanoEntity) value).getIdCiudadano().toString();
        }
        
        return "";
    }

    private CiudadanosModel lookupCiudadanosModelBean() {
        try {
            Context c = new InitialContext();
            return (CiudadanosModel) c.lookup("java:global/Votacion/CiudadanosModel!sv.edu.udb.www.model.CiudadanosModel");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
