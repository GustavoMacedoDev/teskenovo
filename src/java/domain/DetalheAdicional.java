/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "detalhe_adicional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalheAdicional.findAll", query = "SELECT d FROM DetalheAdicional d"),
    @NamedQuery(name = "DetalheAdicional.findByIdDetalheAdicional", query = "SELECT d FROM DetalheAdicional d WHERE d.idDetalheAdicional = :idDetalheAdicional")})
public class DetalheAdicional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalhe_adicional")
    private Long idDetalheAdicional;
    @JoinColumn(name = "adicional_id_adicional", referencedColumnName = "id_adicional")
    @ManyToOne
    private Adicional adicionalIdAdicional;

    public DetalheAdicional() {
    }

    public DetalheAdicional(Long idDetalheAdicional) {
        this.idDetalheAdicional = idDetalheAdicional;
    }

    public Long getIdDetalheAdicional() {
        return idDetalheAdicional;
    }

    public void setIdDetalheAdicional(Long idDetalheAdicional) {
        this.idDetalheAdicional = idDetalheAdicional;
    }

    public Adicional getAdicionalIdAdicional() {
        return adicionalIdAdicional;
    }

    public void setAdicionalIdAdicional(Adicional adicionalIdAdicional) {
        this.adicionalIdAdicional = adicionalIdAdicional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalheAdicional != null ? idDetalheAdicional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalheAdicional)) {
            return false;
        }
        DetalheAdicional other = (DetalheAdicional) object;
        if ((this.idDetalheAdicional == null && other.idDetalheAdicional != null) || (this.idDetalheAdicional != null && !this.idDetalheAdicional.equals(other.idDetalheAdicional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.DetalheAdicional[ idDetalheAdicional=" + idDetalheAdicional + " ]";
    }
    
}
