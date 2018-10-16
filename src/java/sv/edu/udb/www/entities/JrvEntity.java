/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ivanm
 */
@Entity
@Table(name = "jrv")
@NamedQueries({
    @NamedQuery(name = "JrvEntity.findAll", query = "SELECT j FROM JrvEntity j")
    , @NamedQuery(name = "JrvEntity.findByIdJrv", query = "SELECT j FROM JrvEntity j WHERE j.idJrv = :idJrv")
    , @NamedQuery(name = "JrvEntity.findByFechaInicio", query = "SELECT j FROM JrvEntity j WHERE j.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "JrvEntity.findByFechaFinal", query = "SELECT j FROM JrvEntity j WHERE j.fechaFinal = :fechaFinal")
    , @NamedQuery(name = "JrvEntity.findByEstado", query = "SELECT j FROM JrvEntity j WHERE j.estado = :estado")})
public class JrvEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_jrv")
    private Integer idJrv;    
    @Basic(optional = false)    
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)    
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Basic(optional = false)
    @NotNull
    private short estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idJrv")
    private List<VotoEntity> votoEntityList;
    @JoinColumn(name = "id_elecciones", referencedColumnName = "id_eleccion")
    @ManyToOne(optional = false)
    private EleccionEntity idElecciones;
    @JoinColumn(name = "id_centro_votacion", referencedColumnName = "id_centro_votacion")
    @ManyToOne(optional = false)
    private CentroVotacionEntity idCentroVotacion;
    @JoinColumn(name = "id_presidente", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private UsuarioEntity idPresidente;
    @JoinColumn(name = "id_secretario", referencedColumnName = "id_ciudadano")
    @ManyToOne(optional = false)
    private CiudadanoEntity idSecretario;
    @JoinColumn(name = "id_vocal", referencedColumnName = "id_ciudadano")
    @ManyToOne(optional = false)
    private CiudadanoEntity idVocal;

    public JrvEntity() {
    }

    public JrvEntity(Integer idJrv) {
        this.idJrv = idJrv;
    }

    public JrvEntity(Integer idJrv, Date fechaInicio, Date fechaFinal, short estado) {
        this.idJrv = idJrv;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.estado = estado;
    }

    public Integer getIdJrv() {
        return idJrv;
    }

    public void setIdJrv(Integer idJrv) {
        this.idJrv = idJrv;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public List<VotoEntity> getVotoEntityList() {
        return votoEntityList;
    }

    public void setVotoEntityList(List<VotoEntity> votoEntityList) {
        this.votoEntityList = votoEntityList;
    }

    public EleccionEntity getIdElecciones() {
        return idElecciones;
    }

    public void setIdElecciones(EleccionEntity idElecciones) {
        this.idElecciones = idElecciones;
    }

    public CentroVotacionEntity getIdCentroVotacion() {
        return idCentroVotacion;
    }

    public void setIdCentroVotacion(CentroVotacionEntity idCentroVotacion) {
        this.idCentroVotacion = idCentroVotacion;
    }

    public UsuarioEntity getIdPresidente() {
        return idPresidente;
    }

    public void setIdPresidente(UsuarioEntity idPresidente) {
        this.idPresidente = idPresidente;
    }

    public CiudadanoEntity getIdSecretario() {
        return idSecretario;
    }

    public void setIdSecretario(CiudadanoEntity idSecretario) {
        this.idSecretario = idSecretario;
    }

    public CiudadanoEntity getIdVocal() {
        return idVocal;
    }

    public void setIdVocal(CiudadanoEntity idVocal) {
        this.idVocal = idVocal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJrv != null ? idJrv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JrvEntity)) {
            return false;
        }
        JrvEntity other = (JrvEntity) object;
        if ((this.idJrv == null && other.idJrv != null) || (this.idJrv != null && !this.idJrv.equals(other.idJrv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.JrvEntity[ idJrv=" + idJrv + " ]";
    }
    
}
