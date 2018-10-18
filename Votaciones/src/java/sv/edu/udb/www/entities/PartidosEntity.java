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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "partidos")
@NamedQueries({
    @NamedQuery(name = "PartidosEntity.findAll", query = "SELECT p FROM PartidosEntity p")
    , @NamedQuery(name = "PartidosEntity.findByIdPartido", query = "SELECT p FROM PartidosEntity p WHERE p.idPartido = :idPartido")
    , @NamedQuery(name = "PartidosEntity.findByPartido", query = "SELECT p FROM PartidosEntity p WHERE p.partido = :partido")})
public class PartidosEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_partido")
    private Integer idPartido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    private String partido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPartido")
    private List<CandidatoEntity> candidatoEntityList;

    public PartidosEntity() {
    }

    public PartidosEntity(Integer idPartido) {
        this.idPartido = idPartido;
    }

    public PartidosEntity(Integer idPartido, String partido) {
        this.idPartido = idPartido;
        this.partido = partido;
    }

    public Integer getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Integer idPartido) {
        this.idPartido = idPartido;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public List<CandidatoEntity> getCandidatoEntityList() {
        return candidatoEntityList;
    }

    public void setCandidatoEntityList(List<CandidatoEntity> candidatoEntityList) {
        this.candidatoEntityList = candidatoEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartido != null ? idPartido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartidosEntity)) {
            return false;
        }
        PartidosEntity other = (PartidosEntity) object;
        if ((this.idPartido == null && other.idPartido != null) || (this.idPartido != null && !this.idPartido.equals(other.idPartido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.PartidosEntity[ idPartido=" + idPartido + " ]";
    }
    
}
