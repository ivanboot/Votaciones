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
import sv.edu.udb.www.entities.TipoUsuarioEntity;
import sv.edu.udb.www.model.TipoUsuarioModel;

/**
 *
 * @author Ferh
 */
@FacesConverter(forClass=TipoUsuarioEntity.class)
public class TipoUsuarioConverter implements Converter{

    TipoUsuarioModel tipoUsuarioModel = lookupTipoUsuarioModelBean();

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value==null || value.isEmpty()){
            return null;
        }
        return tipoUsuarioModel.obtenerTipoUsuario(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value==null){
            return "";
        }
        
        if(value instanceof TipoUsuarioEntity){
            return ((TipoUsuarioEntity) value).getIdTipoUsuario().toString();
        }
        
        return "";
    }

    private TipoUsuarioModel lookupTipoUsuarioModelBean() {
        try {
            Context c = new InitialContext();
            return (TipoUsuarioModel) c.lookup("java:global/Votacion/TipoUsuarioModel!sv.edu.udb.www.model.TipoUsuarioModel");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
