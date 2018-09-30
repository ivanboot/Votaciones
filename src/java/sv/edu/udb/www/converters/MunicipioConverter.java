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
import sv.edu.udb.www.entities.MunicipioEntity;
import sv.edu.udb.www.model.MunicipiosModel;

/**
 *
 * @author Ferh
 */
@FacesConverter(forClass=MunicipioEntity.class)
public class MunicipioConverter implements Converter{

    MunicipiosModel municipiosModel = lookupMunicipiosModelBean();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value==null || value.isEmpty()){
            return null;
        }
        return municipiosModel.obtenerMunicipio(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value==null){
            return "";
        }
        
        if(value instanceof MunicipioEntity){
            return ((MunicipioEntity) value).getIdMunicipio().toString();
        }
        
        return "";
    }

    private MunicipiosModel lookupMunicipiosModelBean() {
        try {
            Context c = new InitialContext();
            return (MunicipiosModel) c.lookup("java:global/Votacion/MunicipiosModel!sv.edu.udb.www.model.MunicipiosModel");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
