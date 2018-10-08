/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ivanm
 */
@Entity
@Table(name = "centro_votacion")
@NamedQueries({
    @NamedQuery(name = "CentroVotacionEntity.findAll", query = "SELECT c FROM CentroVotacionEntity c")
    , @NamedQuery(name = "CentroVotacionEntity.findByIdCentroVotacion", query = "SELECT c FROM CentroVotacionEntity c WHERE c.idCentroVotacion = :idCentroVotacion")
    , @NamedQuery(name = "CentroVotacionEntity.findByUbicacion", query = "SELECT c FROM CentroVotacionEntity c WHERE c.ubicacion = :ubicacion")
    , @NamedQuery(name = "CentroVotacionEntity.findByNombre", query = "SELECT c FROM CentroVotacionEntity c WHERE c.nombre = :nombre")})
public class CentroVotacionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_centro_votacion")
    private Integer idCentroVotacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String ubicacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCentroVotacion")
    private List<CiudadanoEntity> ciudadanoEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCentroVotacion")
    private List<JrvEntity> jrvEntityList;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false)
    private MunicipioEntity idMunicipio;

    public CentroVotacionEntity() {
    }

    public CentroVotacionEntity(Integer idCentroVotacion) {
        this.idCentroVotacion = idCentroVotacion;
    }

    public CentroVotacionEntity(Integer idCentroVotacion, String ubicacion, String nombre) {
        this.idCentroVotacion = idCentroVotacion;
        this.ubicacion = ubicacion;
        this.nombre = nombre;
    }

    public Integer getIdCentroVotacion() {
        return idCentroVotacion;
    }

    public void setIdCentroVotacion(Integer idCentroVotacion) {
        this.idCentroVotacion = idCentroVotacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CiudadanoEntity> getCiudadanoEntityList() {
        return ciudadanoEntityList;
    }

    public void setCiudadanoEntityList(List<CiudadanoEntity> ciudadanoEntityList) {
        this.ciudadanoEntityList = ciudadanoEntityList;
    }

    public List<JrvEntity> getJrvEntityList() {
        return jrvEntityList;
    }

    public void setJrvEntityList(List<JrvEntity> jrvEntityList) {
        this.jrvEntityList = jrvEntityList;
    }

    public MunicipioEntity getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(MunicipioEntity idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCentroVotacion != null ? idCentroVotacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CentroVotacionEntity)) {
            return false;
        }
        CentroVotacionEntity other = (CentroVotacionEntity) object;
        if ((this.idCentroVotacion == null && other.idCentroVotacion != null) || (this.idCentroVotacion != null && !this.idCentroVotacion.equals(other.idCentroVotacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.CentroVotacionEntity[ idCentroVotacion=" + idCentroVotacion + " ]";
    }
    
}
