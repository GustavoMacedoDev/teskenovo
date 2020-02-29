/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.macedo.sistemas.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "forma_pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormaPagamento.findAll", query = "SELECT f FROM FormaPagamento f"),
    @NamedQuery(name = "FormaPagamento.findByIdFormaPagamento", query = "SELECT f FROM FormaPagamento f WHERE f.idFormaPagamento = :idFormaPagamento"),
    @NamedQuery(name = "FormaPagamento.findByDescFormaPagamento", query = "SELECT f FROM FormaPagamento f WHERE f.descFormaPagamento = :descFormaPagamento")})
public class FormaPagamento implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formaPagamentoId")
    private Collection<Pagamento> pagamentoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_forma_pagamento")
    private Integer idFormaPagamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "desc_forma_pagamento")
    private String descFormaPagamento;

    public FormaPagamento() {
    }

    public FormaPagamento(Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public FormaPagamento(Integer idFormaPagamento, String descFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
        this.descFormaPagamento = descFormaPagamento;
    }

    public Integer getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public String getDescFormaPagamento() {
        return descFormaPagamento;
    }

    public void setDescFormaPagamento(String descFormaPagamento) {
        this.descFormaPagamento = descFormaPagamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormaPagamento != null ? idFormaPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormaPagamento)) {
            return false;
        }
        FormaPagamento other = (FormaPagamento) object;
        if ((this.idFormaPagamento == null && other.idFormaPagamento != null) || (this.idFormaPagamento != null && !this.idFormaPagamento.equals(other.idFormaPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + descFormaPagamento;
    }

    @XmlTransient
    public Collection<Pagamento> getPagamentoCollection() {
        return pagamentoCollection;
    }

    public void setPagamentoCollection(Collection<Pagamento> pagamentoCollection) {
        this.pagamentoCollection = pagamentoCollection;
    }
    
}
