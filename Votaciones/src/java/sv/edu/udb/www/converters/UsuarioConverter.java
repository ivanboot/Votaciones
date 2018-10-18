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
import sv.edu.udb.www.entities.UsuarioEntity;
import sv.edu.udb.www.model.UsuariosModel;

/**
 *
 * @author Ferh
 */
@FacesConverter(forClass=UsuarioEntity.class)
public class UsuarioConverter implements Converter{

    UsuariosModel usuariosModel = lookupUsuariosModelBean();

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value==null || value.isEmpty()){
            return null;
        }
        return usuariosModel.obtenerUsuario(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value==null){
            return "";
        }
        
        if(value instanceof UsuarioEntity){
            return ((UsuarioEntity) value).getIdUsuario().toString();
        }
        
        return "";
    }

    private UsuariosModel lookupUsuariosModelBean() {
        try {
            Context c = new InitialContext();
            return (UsuariosModel) c.lookup("java:global/Votacion/UsuariosModel!sv.edu.udb.www.model.UsuariosModel");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
