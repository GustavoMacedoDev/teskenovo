/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.macedo.sistemas.domain;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p"),
    @NamedQuery(name = "Pagamento.findByIdPagamento", query = "SELECT p FROM Pagamento p WHERE p.idPagamento = :idPagamento"),
    @NamedQuery(name = "Pagamento.findByValorPago", query = "SELECT p FROM Pagamento p WHERE p.valorPago = :valorPago")})
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pagamento")
    private Integer idPagamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_pago")
    private double valorPago;
    
    @JoinColumn(name = "forma_pagamento_id", referencedColumnName = "id_forma_pagamento")
    @ManyToOne(optional = false)
    private FormaPagamento formaPagamentoId;
    @JoinColumn(name = "mesa_mesa_id", referencedColumnName = "mesa_id")
    @ManyToOne(optional = false)
    private Mesa mesaMesaId;

    public Pagamento() {
    }

    public Pagamento(Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Pagamento(Integer idPagamento, double valorPago) {
        this.idPagamento = idPagamento;
        this.valorPago = valorPago;
    }

    public Integer getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public FormaPagamento getFormaPagamentoId() {
        return formaPagamentoId;
    }

    public void setFormaPagamentoId(FormaPagamento formaPagamentoId) {
        this.formaPagamentoId = formaPagamentoId;
    }

    public Mesa getMesaMesaId() {
        return mesaMesaId;
    }

    public void setMesaMesaId(Mesa mesaMesaId) {
        this.mesaMesaId = mesaMesaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPagamento != null ? idPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.idPagamento == null && other.idPagamento != null) || (this.idPagamento != null && !this.idPagamento.equals(other.idPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.macedo.sistemas.domain.Pagamento[ idPagamento=" + idPagamento + " ]";
    }
    
}
