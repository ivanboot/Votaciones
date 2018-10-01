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
 * @author Rodriguez
 */
@Entity
@Table(name = "municipio")
@NamedQueries({
    @NamedQuery(name = "MunicipioEntity.findAll", query = "SELECT m FROM MunicipioEntity m")
    , @NamedQuery(name = "MunicipioEntity.findByIdMunicipio", query = "SELECT m FROM MunicipioEntity m WHERE m.idMunicipio = :idMunicipio")
    , @NamedQuery(name = "MunicipioEntity.findByMunicipio", query = "SELECT m FROM MunicipioEntity m WHERE m.municipio = :municipio")})
public class MunicipioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_municipio")
    private Integer idMunicipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    private String municipio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMunicipio")
    private List<CentroVotacionEntity> centroVotacionEntityList;
    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
    @ManyToOne(optional = false)
    private DepartamentoEntity idDepartamento;

    public MunicipioEntity() {
    }

    public MunicipioEntity(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public MunicipioEntity(Integer idMunicipio, String municipio) {
        this.idMunicipio = idMunicipio;
        this.municipio = municipio;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public List<CentroVotacionEntity> getCentroVotacionEntityList() {
        return centroVotacionEntityList;
    }

    public void setCentroVotacionEntityList(List<CentroVotacionEntity> centroVotacionEntityList) {
        this.centroVotacionEntityList = centroVotacionEntityList;
    }

    public DepartamentoEntity getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(DepartamentoEntity idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMunicipio != null ? idMunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MunicipioEntity)) {
            return false;
        }
        MunicipioEntity other = (MunicipioEntity) object;
        if ((this.idMunicipio == null && other.idMunicipio != null) || (this.idMunicipio != null && !this.idMunicipio.equals(other.idMunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.MunicipioEntity[ idMunicipio=" + idMunicipio + " ]";
    }
    
}
