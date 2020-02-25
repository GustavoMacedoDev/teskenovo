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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
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

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mesa_id")
    private Long mesaId;
    @Size(max = 255)
    @Column(name = "numero_mesa")
    private String numeroMesa;
    @Column(name = "status_mesa")
    private Integer statusMesa;
    
    @OneToMany(mappedBy = "mesa", fetch = FetchType.EAGER)
    private List<Lancamento> lancamentos;
    
    @OneToMany(mappedBy = "mesaMesaId")
    private List<Pedido> pedidoList;
    
    @Transient
    private double totalMesa;

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

    @XmlTransient
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

    public double getTotalMesa() {
        return totalMesa;
    }

    public void setTotalMesa(double totalMesa) {
        this.totalMesa = totalMesa;
    }
    
   
    
}
