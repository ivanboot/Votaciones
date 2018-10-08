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
import javax.validation.constraints.Size;

/**
 *
 * @author ivanm
 */
@Entity
@Table(name = "elecciones")
@NamedQueries({
    @NamedQuery(name = "EleccionEntity.findAll", query = "SELECT e FROM EleccionEntity e")
    , @NamedQuery(name = "EleccionEntity.findByIdEleccion", query = "SELECT e FROM EleccionEntity e WHERE e.idEleccion = :idEleccion")
    , @NamedQuery(name = "EleccionEntity.findByTitulo", query = "SELECT e FROM EleccionEntity e WHERE e.titulo = :titulo")
    , @NamedQuery(name = "EleccionEntity.findByFechaInicioCandidato", query = "SELECT e FROM EleccionEntity e WHERE e.fechaInicioCandidato = :fechaInicioCandidato")
    , @NamedQuery(name = "EleccionEntity.findByFechaFinCandidato", query = "SELECT e FROM EleccionEntity e WHERE e.fechaFinCandidato = :fechaFinCandidato")
    , @NamedQuery(name = "EleccionEntity.findByFechaEleccion", query = "SELECT e FROM EleccionEntity e WHERE e.fechaEleccion = :fechaEleccion")
    , @NamedQuery(name = "EleccionEntity.findByEstado", query = "SELECT e FROM EleccionEntity e WHERE e.estado = :estado")})
public class EleccionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_eleccion")
    private Integer idEleccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio_candidato")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioCandidato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin_candidato")
    @Temporal(TemporalType.DATE)
    private Date fechaFinCandidato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_eleccion")
    @Temporal(TemporalType.DATE)
    private Date fechaEleccion;
    @Basic(optional = false)
    @NotNull
    private short estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEleccion")
    private List<DetalleCiudadanoEleccionEntity> detalleCiudadanoEleccionEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idElecciones")
    private List<JrvEntity> jrvEntityList;
    @JoinColumn(name = "id_tipo_eleccion", referencedColumnName = "id_eleccion")
    @ManyToOne(optional = false)
    private TipoEleccionEntity idTipoEleccion;

    public EleccionEntity() {
    }

    public EleccionEntity(Integer idEleccion) {
        this.idEleccion = idEleccion;
    }

    public EleccionEntity(Integer idEleccion, String titulo, Date fechaInicioCandidato, Date fechaFinCandidato, Date fechaEleccion, short estado) {
        this.idEleccion = idEleccion;
        this.titulo = titulo;
        this.fechaInicioCandidato = fechaInicioCandidato;
        this.fechaFinCandidato = fechaFinCandidato;
        this.fechaEleccion = fechaEleccion;
        this.estado = estado;
    }

    public Integer getIdEleccion() {
        return idEleccion;
    }

    public void setIdEleccion(Integer idEleccion) {
        this.idEleccion = idEleccion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaInicioCandidato() {
        return fechaInicioCandidato;
    }

    public void setFechaInicioCandidato(Date fechaInicioCandidato) {
        this.fechaInicioCandidato = fechaInicioCandidato;
    }

    public Date getFechaFinCandidato() {
        return fechaFinCandidato;
    }

    public void setFechaFinCandidato(Date fechaFinCandidato) {
        this.fechaFinCandidato = fechaFinCandidato;
    }

    public Date getFechaEleccion() {
        return fechaEleccion;
    }

    public void setFechaEleccion(Date fechaEleccion) {
        this.fechaEleccion = fechaEleccion;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public List<DetalleCiudadanoEleccionEntity> getDetalleCiudadanoEleccionEntityList() {
        return detalleCiudadanoEleccionEntityList;
    }

    public void setDetalleCiudadanoEleccionEntityList(List<DetalleCiudadanoEleccionEntity> detalleCiudadanoEleccionEntityList) {
        this.detalleCiudadanoEleccionEntityList = detalleCiudadanoEleccionEntityList;
    }

    public List<JrvEntity> getJrvEntityList() {
        return jrvEntityList;
    }

    public void setJrvEntityList(List<JrvEntity> jrvEntityList) {
        this.jrvEntityList = jrvEntityList;
    }

    public TipoEleccionEntity getIdTipoEleccion() {
        return idTipoEleccion;
    }

    public void setIdTipoEleccion(TipoEleccionEntity idTipoEleccion) {
        this.idTipoEleccion = idTipoEleccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEleccion != null ? idEleccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EleccionEntity)) {
            return false;
        }
        EleccionEntity other = (EleccionEntity) object;
        if ((this.idEleccion == null && other.idEleccion != null) || (this.idEleccion != null && !this.idEleccion.equals(other.idEleccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.EleccionEntity[ idEleccion=" + idEleccion + " ]";
    }
    
}
