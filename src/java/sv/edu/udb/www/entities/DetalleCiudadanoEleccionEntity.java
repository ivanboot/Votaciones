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

/**
 *
 * @author ivanm
 */
@Entity
@Table(name = "detalle_ciudadano_eleccion")
@NamedQueries({
    @NamedQuery(name = "DetalleCiudadanoEleccionEntity.findAll", query = "SELECT d FROM DetalleCiudadanoEleccionEntity d")
    , @NamedQuery(name = "DetalleCiudadanoEleccionEntity.findByIdCiudadanoEleccion", query = "SELECT d FROM DetalleCiudadanoEleccionEntity d WHERE d.idCiudadanoEleccion = :idCiudadanoEleccion")})
public class DetalleCiudadanoEleccionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ciudadano_eleccion")
    private Integer idCiudadanoEleccion;
    @JoinColumn(name = "id_ciudadano", referencedColumnName = "id_ciudadano")
    @ManyToOne(optional = false)
    private CiudadanoEntity idCiudadano;
    @JoinColumn(name = "id_eleccion", referencedColumnName = "id_eleccion")
    @ManyToOne(optional = false)
    private EleccionEntity idEleccion;

    public DetalleCiudadanoEleccionEntity() {
    }

    public DetalleCiudadanoEleccionEntity(Integer idCiudadanoEleccion) {
        this.idCiudadanoEleccion = idCiudadanoEleccion;
    }

    public Integer getIdCiudadanoEleccion() {
        return idCiudadanoEleccion;
    }

    public void setIdCiudadanoEleccion(Integer idCiudadanoEleccion) {
        this.idCiudadanoEleccion = idCiudadanoEleccion;
    }

    public CiudadanoEntity getIdCiudadano() {
        return idCiudadano;
    }

    public void setIdCiudadano(CiudadanoEntity idCiudadano) {
        this.idCiudadano = idCiudadano;
    }

    public EleccionEntity getIdEleccion() {
        return idEleccion;
    }

    public void setIdEleccion(EleccionEntity idEleccion) {
        this.idEleccion = idEleccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCiudadanoEleccion != null ? idCiudadanoEleccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCiudadanoEleccionEntity)) {
            return false;
        }
        DetalleCiudadanoEleccionEntity other = (DetalleCiudadanoEleccionEntity) object;
        if ((this.idCiudadanoEleccion == null && other.idCiudadanoEleccion != null) || (this.idCiudadanoEleccion != null && !this.idCiudadanoEleccion.equals(other.idCiudadanoEleccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.DetalleCiudadanoEleccionEntity[ idCiudadanoEleccion=" + idCiudadanoEleccion + " ]";
    }
    
}
