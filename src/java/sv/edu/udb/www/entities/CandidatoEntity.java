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


@Entity
@Table(name = "candidatos")
@NamedQueries({
    @NamedQuery(name = "CandidatoEntity.findAll", query = "SELECT c FROM CandidatoEntity c")
    , @NamedQuery(name = "CandidatoEntity.findByIdCandidatos", query = "SELECT c FROM CandidatoEntity c WHERE c.idCandidatos = :idCandidatos")
    , @NamedQuery(name = "CandidatoEntity.findByNombres", query = "SELECT c FROM CandidatoEntity c WHERE c.nombres = :nombres")
    , @NamedQuery(name = "CandidatoEntity.findByApellidos", query = "SELECT c FROM CandidatoEntity c WHERE c.apellidos = :apellidos")
    , @NamedQuery(name = "CandidatoEntity.findByUrlFoto", query = "SELECT c FROM CandidatoEntity c WHERE c.urlFoto = :urlFoto")})
public class CandidatoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_candidatos")
    private Integer idCandidatos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "url_foto")
    private String urlFoto;
    @JoinColumn(name = "id_partido", referencedColumnName = "id_partido")
    @ManyToOne(optional = false)
    private PartidosEntity idPartido;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne
    private MunicipioEntity idMunicipio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCandidato")
    private List<VotoEntity> votoEntityList;

    public CandidatoEntity() {
    }

    public CandidatoEntity(Integer idCandidatos) {
        this.idCandidatos = idCandidatos;
    }

    public CandidatoEntity(Integer idCandidatos, String nombres, String apellidos, String urlFoto) {
        this.idCandidatos = idCandidatos;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.urlFoto = urlFoto;
    }

    public Integer getIdCandidatos() {
        return idCandidatos;
    }

    public void setIdCandidatos(Integer idCandidatos) {
        this.idCandidatos = idCandidatos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public PartidosEntity getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(PartidosEntity idPartido) {
        this.idPartido = idPartido;
    }

    public MunicipioEntity getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(MunicipioEntity idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public List<VotoEntity> getVotoEntityList() {
        return votoEntityList;
    }

    public void setVotoEntityList(List<VotoEntity> votoEntityList) {
        this.votoEntityList = votoEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCandidatos != null ? idCandidatos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CandidatoEntity)) {
            return false;
        }
        CandidatoEntity other = (CandidatoEntity) object;
        if ((this.idCandidatos == null && other.idCandidatos != null) || (this.idCandidatos != null && !this.idCandidatos.equals(other.idCandidatos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.udb.www.entities.CandidatoEntity[ idCandidatos=" + idCandidatos + " ]";
    }
    
}
