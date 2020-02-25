/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.macedo.sistemas.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "adicional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adicional.findAll", query = "SELECT a FROM Adicional a"),
    @NamedQuery(name = "Adicional.findByIdAdicional", query = "SELECT a FROM Adicional a WHERE a.idAdicional = :idAdicional"),
    @NamedQuery(name = "Adicional.findByDescricao", query = "SELECT a FROM Adicional a WHERE a.descricao = :descricao"),
    @NamedQuery(name = "Adicional.findByValorAdicional", query = "SELECT a FROM Adicional a WHERE a.valorAdicional = :valorAdicional")})
public class Adicional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_adicional")
    private Long idAdicional;
    @Size(max = 255)
    @Column(name = "descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_adicional")
    private Double valorAdicional;
    @OneToMany(mappedBy = "adicionalIdAdicional")
    private List<DetalheAdicional> detalheAdicionalList;

    public Adicional() {
    }

    public Adicional(Long idAdicional) {
        this.idAdicional = idAdicional;
    }

    public Long getIdAdicional() {
        return idAdicional;
    }

    public void setIdAdicional(Long idAdicional) {
        this.idAdicional = idAdicional;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(Double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    @XmlTransient
    public List<DetalheAdicional> getDetalheAdicionalList() {
        return detalheAdicionalList;
    }

    public void setDetalheAdicionalList(List<DetalheAdicional> detalheAdicionalList) {
        this.detalheAdicionalList = detalheAdicionalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdicional != null ? idAdicional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adicional)) {
            return false;
        }
        Adicional other = (Adicional) object;
        if ((this.idAdicional == null && other.idAdicional != null) || (this.idAdicional != null && !this.idAdicional.equals(other.idAdicional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Adicional[ idAdicional=" + idAdicional + " ]";
    }
    
}
