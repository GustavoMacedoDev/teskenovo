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
@Table(name = "itens_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItensPedido.findAll", query = "SELECT i FROM ItensPedido i"),
    @NamedQuery(name = "ItensPedido.findByIdItensPedido", query = "SELECT i FROM ItensPedido i WHERE i.idItensPedido = :idItensPedido"),
    @NamedQuery(name = "ItensPedido.findByQuantidade", query = "SELECT i FROM ItensPedido i WHERE i.quantidade = :quantidade"),
    @NamedQuery(name = "ItensPedido.findByValorTotal", query = "SELECT i FROM ItensPedido i WHERE i.valorTotal = :valorTotal"),
    @NamedQuery(name = "ItensPedido.findByValorUnitario", query = "SELECT i FROM ItensPedido i WHERE i.valorUnitario = :valorUnitario")})
public class ItensPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_itens_pedido")
    private Long idItensPedido;
    @Column(name = "quantidade")
    private Integer quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_total")
    private Double valorTotal;
    @Column(name = "valor_unitario")
    private Double valorUnitario;
    @JoinColumn(name = "pedido_id_pedido", referencedColumnName = "id_pedido")
    @ManyToOne
    private Pedido pedidoIdPedido;
    @JoinColumn(name = "produto_id_produto", referencedColumnName = "id_produto")
    @ManyToOne
    private Produto produtoIdProduto;

    public ItensPedido() {
    }

    public ItensPedido(Long idItensPedido) {
        this.idItensPedido = idItensPedido;
    }

    public Long getIdItensPedido() {
        return idItensPedido;
    }

    public void setIdItensPedido(Long idItensPedido) {
        this.idItensPedido = idItensPedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Pedido getPedidoIdPedido() {
        return pedidoIdPedido;
    }

    public void setPedidoIdPedido(Pedido pedidoIdPedido) {
        this.pedidoIdPedido = pedidoIdPedido;
    }

    public Produto getProdutoIdProduto() {
        return produtoIdProduto;
    }

    public void setProdutoIdProduto(Produto produtoIdProduto) {
        this.produtoIdProduto = produtoIdProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItensPedido != null ? idItensPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensPedido)) {
            return false;
        }
        ItensPedido other = (ItensPedido) object;
        if ((this.idItensPedido == null && other.idItensPedido != null) || (this.idItensPedido != null && !this.idItensPedido.equals(other.idItensPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.ItensPedido[ idItensPedido=" + idItensPedido + " ]";
    }
    
}
