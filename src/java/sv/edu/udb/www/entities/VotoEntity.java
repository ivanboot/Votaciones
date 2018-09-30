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


@Entity
@Table(name = "votos")
@NamedQueries({
    @NamedQuery(name = "VotoEntity.findAll", query = "SELECT v FROM VotoEntity v")
    , @NamedQuery(name = "VotoEntity.findByIdVotos", query = "SELECT v FROM VotoEntity v WHERE v.idVotos = :idVotos")
    , @NamedQuery(name = "VotoEntity.findByCantidad", query = "SELECT v FROM VotoEntity v WHERE v.cantidad = :cantidad")
    , @NamedQuery(name = "VotoEntity.findByEstado", query = "SELECT v FROM VotoEntity v WHERE v.estado = :estado")})
public class VotoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_votos")
    private Integer idVotos;
    @Basic(optional = false)
    @NotNull
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    private short estado;
    @JoinColumn(name = "id_jrv", referencedColumnName = "id_jrv")
    @ManyToOne(optional = false)
    private JrvEntity idJrv;
    @JoinColumn(name = "id_candidato", referencedColumnName = "id_candidatos")
    @ManyToOne(optional = false)
    private CandidatoEntity idCandidato;

    public VotoEntity() {
    }

    public VotoEntity(Integer idVotos) {
        this.idVotos = idVotos;
    }

    public VotoEntity(Integer idVotos, int cantidad, short estado) {
        this.idVotos = idVotos;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public Integer getIdVotos() {
        return idVotos;
    }

    public void setIdVotos(Integer idVotos) {
        this.idVotos = idVotos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public JrvEntity getIdJrv() {
        return idJrv;
    }

    public void setIdJrv(JrvEntity idJrv) {
        this.idJrv = idJrv;
    }

    public CandidatoEntity getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(CandidatoEntity idCandidato) {
        this.idCandidato = idCandidato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVotos != null ? idVotos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VotoEntity)) {
            return false;
        }
        VotoEntity other = (VotoEntity) object;
        if ((this.idVotos == null && other.idVotos != null) || (this.idVotos != null && !this.idVotos.equals(other.idVotos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.VotoEntity[ idVotos=" + idVotos + " ]";
    }
    
}
