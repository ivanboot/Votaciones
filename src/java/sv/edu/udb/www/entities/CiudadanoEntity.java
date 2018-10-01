/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Rodriguez
 */
@Entity
@Table(name = "ciudadanos")
@NamedQueries({
    @NamedQuery(name = "CiudadanoEntity.findAll", query = "SELECT c FROM CiudadanoEntity c")
    , @NamedQuery(name = "CiudadanoEntity.findByIdCiudadano", query = "SELECT c FROM CiudadanoEntity c WHERE c.idCiudadano = :idCiudadano")
    , @NamedQuery(name = "CiudadanoEntity.findByNombres", query = "SELECT c FROM CiudadanoEntity c WHERE c.nombres = :nombres")
    , @NamedQuery(name = "CiudadanoEntity.findByApellidos", query = "SELECT c FROM CiudadanoEntity c WHERE c.apellidos = :apellidos")
    , @NamedQuery(name = "CiudadanoEntity.findByDireccion", query = "SELECT c FROM CiudadanoEntity c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "CiudadanoEntity.findByDui", query = "SELECT c FROM CiudadanoEntity c WHERE c.dui = :dui")
    , @NamedQuery(name = "CiudadanoEntity.findByTelefono", query = "SELECT c FROM CiudadanoEntity c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "CiudadanoEntity.findByUrlImagen", query = "SELECT c FROM CiudadanoEntity c WHERE c.urlImagen = :urlImagen")})
public class CiudadanoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ciudadano")
    private Integer idCiudadano;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    private String dui;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "url_imagen")
    private String urlImagen;
    @JoinColumn(name = "id_centro_votacion", referencedColumnName = "id_centro_votacion")
    @ManyToOne(optional = false)
    private CentroVotacionEntity idCentroVotacion;

    public CiudadanoEntity() {
    }

    public CiudadanoEntity(Integer idCiudadano) {
        this.idCiudadano = idCiudadano;
    }

    public CiudadanoEntity(Integer idCiudadano, String nombres, String apellidos, String direccion, String dui, String telefono, String urlImagen) {
        this.idCiudadano = idCiudadano;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dui = dui;
        this.telefono = telefono;
        this.urlImagen = urlImagen;
    }

    public Integer getIdCiudadano() {
        return idCiudadano;
    }

    public void setIdCiudadano(Integer idCiudadano) {
        this.idCiudadano = idCiudadano;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public CentroVotacionEntity getIdCentroVotacion() {
        return idCentroVotacion;
    }

    public void setIdCentroVotacion(CentroVotacionEntity idCentroVotacion) {
        this.idCentroVotacion = idCentroVotacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCiudadano != null ? idCiudadano.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CiudadanoEntity)) {
            return false;
        }
        CiudadanoEntity other = (CiudadanoEntity) object;
        if ((this.idCiudadano == null && other.idCiudadano != null) || (this.idCiudadano != null && !this.idCiudadano.equals(other.idCiudadano))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.CiudadanoEntity[ idCiudadano=" + idCiudadano + " ]";
    }
    
}
