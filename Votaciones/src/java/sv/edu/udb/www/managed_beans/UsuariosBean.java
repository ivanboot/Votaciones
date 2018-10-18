/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sv.edu.udb.www.entities.CiudadanoEntity;
import sv.edu.udb.www.entities.TipoUsuarioEntity;
import sv.edu.udb.www.entities.UsuarioEntity;
import sv.edu.udb.www.model.CandidatosModel;
import sv.edu.udb.www.model.CiudadanosModel;
import sv.edu.udb.www.model.TipoUsuarioModel;
import sv.edu.udb.www.model.UsuariosModel;
import sv.edu.udb.www.utils.Correo;
import sv.edu.udb.www.utils.JsfUtils;

/**
 *
 * @author ivanm
 */
@Named(value = "usuariosBean")
@RequestScoped
public class UsuariosBean {

    @EJB
    private CiudadanosModel ciudadanosModel;
    
    @EJB
    private TipoUsuarioModel tipoUsuarioModel;

    @EJB
    private UsuariosModel usuariosModel;        
        
    List<UsuarioEntity> listaUsuarios;
    
    List<TipoUsuarioEntity> listaTipoUsuario;
    
    List<CiudadanoEntity> listaCiudadanos;
    
    UsuarioEntity usuario = new UsuarioEntity();
    
    public UsuariosBean() {
    }

    public List<UsuarioEntity> getListaUsuarios() {
        listaUsuarios=usuariosModel.listarUsuarios();
        return listaUsuarios;
    }

    public List<TipoUsuarioEntity> getListaTipoUsuario() {
        listaTipoUsuario=tipoUsuarioModel.listarTipoUsuario();
        return listaTipoUsuario;
    }

    public List<CiudadanoEntity> getListaCiudadanos() {
        listaCiudadanos = ciudadanosModel.listarCiudadanoDisponible();
        return listaCiudadanos;
    }
    
    
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
    
    public String nuevoUsuario(){
        //Creacion password
            char[] caracteres;
            caracteres = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            String pass = "";
            for (int i = 0; i < 8; i++) {
                pass += caracteres[new Random().nextInt(62)];
            }
            //fin contraseña
            usuario.setContra(pass);
        if(usuariosModel.insertarUsuario(usuario)==0){
            JsfUtils.addErrorMessage("idUsuario", "No se pudo ingresar el usuario");
            return null;
        }
        
        String texto = "Has sido registrado exitosamente al sistema electoral.<br>";
                    texto += "Anota tu contraseña: " + pass + "<br> ";
                    texto += "Accede con tu correo: "+ usuario.getCorreo()+".";
                    Correo correo = new Correo();
                    correo.setAsunto("Has sido registrado");
                    correo.setMensaje(texto);
                    correo.setDestinatario(usuario.getCorreo());
                    correo.enviarCorreo();
        JsfUtils.addFlashMessage("exito", "Usuario ingresado con exito");
        return "/adminGeneral/ListaUsuarios?faces-redirect=true";
    }
    
    public String obtenerUsuario(){
        int codigo = Integer.parseInt(JsfUtils.getRequest().getParameter("codigo"));
        usuario=usuariosModel.obtenerUsuario(codigo);
        return "/adminGeneral/ModificarUsuario";
    }
    
    public String modificarUsuario(){
        if(usuariosModel.modificarUsuario(usuario)==0){
            JsfUtils.addErrorMessage("idUsuario", "No se pudo modificar el usuario");
            return null;
        }
        JsfUtils.addFlashMessage("exito", "Usuario modificado con exito");
        return "/adminGeneral/ListaUsuarios?faces-redirect=true";
    }
}
