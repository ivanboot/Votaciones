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


@Entity
@Table(name = "tipo_eleccion")
@NamedQueries({
    @NamedQuery(name = "TipoEleccionEntity.findAll", query = "SELECT t FROM TipoEleccionEntity t")
    , @NamedQuery(name = "TipoEleccionEntity.findByIdEleccion", query = "SELECT t FROM TipoEleccionEntity t WHERE t.idEleccion = :idEleccion")
    , @NamedQuery(name = "TipoEleccionEntity.findByEleccion", query = "SELECT t FROM TipoEleccionEntity t WHERE t.eleccion = :eleccion")})
public class TipoEleccionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_eleccion")
    private Integer idEleccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    private String eleccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoEleccion")
    private List<EleccionEntity> eleccionEntityList;

    public TipoEleccionEntity() {
    }

    public TipoEleccionEntity(Integer idEleccion) {
        this.idEleccion = idEleccion;
    }

    public TipoEleccionEntity(Integer idEleccion, String eleccion) {
        this.idEleccion = idEleccion;
        this.eleccion = eleccion;
    }

    public Integer getIdEleccion() {
        return idEleccion;
    }

    public void setIdEleccion(Integer idEleccion) {
        this.idEleccion = idEleccion;
    }

    public String getEleccion() {
        return eleccion;
    }

    public void setEleccion(String eleccion) {
        this.eleccion = eleccion;
    }

    public List<EleccionEntity> getEleccionEntityList() {
        return eleccionEntityList;
    }

    public void setEleccionEntityList(List<EleccionEntity> eleccionEntityList) {
        this.eleccionEntityList = eleccionEntityList;
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
        if (!(object instanceof TipoEleccionEntity)) {
            return false;
        }
        TipoEleccionEntity other = (TipoEleccionEntity) object;
        if ((this.idEleccion == null && other.idEleccion != null) || (this.idEleccion != null && !this.idEleccion.equals(other.idEleccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.TipoEleccionEntity[ idEleccion=" + idEleccion + " ]";
    }
    
}
