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
@Table(name = "detalle_candidato_eleccion")
@NamedQueries({
    @NamedQuery(name = "DetalleCandidatoEleccionEntity.findAll", query = "SELECT d FROM DetalleCandidatoEleccionEntity d")
    , @NamedQuery(name = "DetalleCandidatoEleccionEntity.findByIdCandidatoEleccion", query = "SELECT d FROM DetalleCandidatoEleccionEntity d WHERE d.idCandidatoEleccion = :idCandidatoEleccion")})
public class DetalleCandidatoEleccionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_candidato_eleccion")
    private Integer idCandidatoEleccion;
    @JoinColumn(name = "id_candidatos", referencedColumnName = "id_candidatos")
    @ManyToOne(optional = false)
    private CandidatoEntity idCandidatos;
    @JoinColumn(name = "id_eleccion", referencedColumnName = "id_eleccion")
    @ManyToOne(optional = false)
    private EleccionEntity idEleccion;

    public DetalleCandidatoEleccionEntity() {
    }

    public DetalleCandidatoEleccionEntity(Integer idCandidatoEleccion) {
        this.idCandidatoEleccion = idCandidatoEleccion;
    }

    public Integer getIdCandidatoEleccion() {
        return idCandidatoEleccion;
    }

    public void setIdCandidatoEleccion(Integer idCandidatoEleccion) {
        this.idCandidatoEleccion = idCandidatoEleccion;
    }

    public CandidatoEntity getIdCandidatos() {
        return idCandidatos;
    }

    public void setIdCandidatos(CandidatoEntity idCandidatos) {
        this.idCandidatos = idCandidatos;
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
        hash += (idCandidatoEleccion != null ? idCandidatoEleccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCandidatoEleccionEntity)) {
            return false;
        }
        DetalleCandidatoEleccionEntity other = (DetalleCandidatoEleccionEntity) object;
        if ((this.idCandidatoEleccion == null && other.idCandidatoEleccion != null) || (this.idCandidatoEleccion != null && !this.idCandidatoEleccion.equals(other.idCandidatoEleccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.DetalleCandidatoEleccionEntity[ idCandidatoEleccion=" + idCandidatoEleccion + " ]";
    }
    
}
