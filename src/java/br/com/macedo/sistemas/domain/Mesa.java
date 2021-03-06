/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.macedo.sistemas.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "mesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesa.findAll", query = "SELECT m FROM Mesa m"),
    @NamedQuery(name = "Mesa.findByMesaId", query = "SELECT m FROM Mesa m WHERE m.mesaId = :mesaId"),
    @NamedQuery(name = "Mesa.findByNumeroMesa", query = "SELECT m FROM Mesa m WHERE m.numeroMesa = :numeroMesa"),
    @NamedQuery(name = "Mesa.findByStatusMesa", query = "SELECT m FROM Mesa m WHERE m.statusMesa = :statusMesa")})
public class Mesa implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mesaMesaId")
    private Collection<Pagamento> pagamentoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mesa_id")
    private Long mesaId;
    @Size(max = 255)
    @Column(name = "numero_mesa", nullable = false)
    private String numeroMesa;
    @Column(name = "status_mesa")
    private Integer statusMesa;
    
    @OneToMany(mappedBy = "mesa", fetch = FetchType.EAGER)
    private List<Lancamento> lancamentos;
    
    @OneToMany(mappedBy = "mesaMesaId")
    private List<Pedido> pedidoList;
    
    @Column(scale = 12, precision = 2, name = "valor_pago")
    private Double valorPago = 0.0;
    
    @Column(scale = 12, precision = 2, name = "valor_total")
    private Double valorTotal;
    
    
    public Mesa() {
    }

    public Mesa(Long mesaId) {
        this.mesaId = mesaId;
    }

    public Long getMesaId() {
        return mesaId;
    }

    public void setMesaId(Long mesaId) {
        this.mesaId = mesaId;
    }

    public String getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(String numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Integer getStatusMesa() {
        return statusMesa;
    }

    public void setStatusMesa(Integer statusMesa) {
        this.statusMesa = statusMesa;
    }

    public List<Lancamento> getLancamentoList() {
        return lancamentos;
    }

    public void setLancamentoList(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mesaId != null ? mesaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesa)) {
            return false;
        }
        Mesa other = (Mesa) object;
        if ((this.mesaId == null && other.mesaId != null) || (this.mesaId != null && !this.mesaId.equals(other.mesaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + numeroMesa;
    }

   
    @XmlTransient
    public Collection<Pagamento> getPagamentoCollection() {
        return pagamentoCollection;
    }

    public void setPagamentoCollection(Collection<Pagamento> pagamentoCollection) {
        this.pagamentoCollection = pagamentoCollection;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
   
    
}
