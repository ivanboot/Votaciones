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
@Table(name = "detalle_ciudadano_jrv")
@NamedQueries({
    @NamedQuery(name = "DetalleCiudadanoJrvEntity.findAll", query = "SELECT d FROM DetalleCiudadanoJrvEntity d")
    , @NamedQuery(name = "DetalleCiudadanoJrvEntity.findByIdCiudadanoJrv", query = "SELECT d FROM DetalleCiudadanoJrvEntity d WHERE d.idCiudadanoJrv = :idCiudadanoJrv")})
public class DetalleCiudadanoJrvEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ciudadano_jrv")
    private Integer idCiudadanoJrv;
    @JoinColumn(name = "id_ciudadano", referencedColumnName = "id_ciudadano")
    @ManyToOne(optional = false)
    private CiudadanoEntity idCiudadano;
    @JoinColumn(name = "id_jrv", referencedColumnName = "id_jrv")
    @ManyToOne(optional = false)
    private JrvEntity idJrv;

    public DetalleCiudadanoJrvEntity() {
    }

    public DetalleCiudadanoJrvEntity(Integer idCiudadanoJrv) {
        this.idCiudadanoJrv = idCiudadanoJrv;
    }

    public Integer getIdCiudadanoJrv() {
        return idCiudadanoJrv;
    }

    public void setIdCiudadanoJrv(Integer idCiudadanoJrv) {
        this.idCiudadanoJrv = idCiudadanoJrv;
    }

    public CiudadanoEntity getIdCiudadano() {
        return idCiudadano;
    }

    public void setIdCiudadano(CiudadanoEntity idCiudadano) {
        this.idCiudadano = idCiudadano;
    }

    public JrvEntity getIdJrv() {
        return idJrv;
    }

    public void setIdJrv(JrvEntity idJrv) {
        this.idJrv = idJrv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCiudadanoJrv != null ? idCiudadanoJrv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCiudadanoJrvEntity)) {
            return false;
        }
        DetalleCiudadanoJrvEntity other = (DetalleCiudadanoJrvEntity) object;
        if ((this.idCiudadanoJrv == null && other.idCiudadanoJrv != null) || (this.idCiudadanoJrv != null && !this.idCiudadanoJrv.equals(other.idCiudadanoJrv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.DetalleCiudadanoJrvEntity[ idCiudadanoJrv=" + idCiudadanoJrv + " ]";
    }
    
}
