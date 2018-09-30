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
import sv.edu.udb.www.entities.PartidosEntity;
import sv.edu.udb.www.model.PartidosModel;

/**
 *
 * @author Ferh
 */
@FacesConverter(forClass=PartidosEntity.class)
public class PartidoConverter implements Converter{

    PartidosModel partidosModel = lookupPartidosModelBean();

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value==null || value.isEmpty()){
            return null;
        }
        return partidosModel.obtenerPartido(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value==null){
            return "";
        }
        
        if(value instanceof PartidosEntity){
            return ((PartidosEntity) value).getIdPartido().toString();
        }
        
        return "";
    }

    private PartidosModel lookupPartidosModelBean() {
        try {
            Context c = new InitialContext();
            return (PartidosModel) c.lookup("java:global/Votacion/PartidosModel!sv.edu.udb.www.model.PartidosModel");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
