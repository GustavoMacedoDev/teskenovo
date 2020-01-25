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
@Table(name = "lancamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lancamento.findAll", query = "SELECT l FROM Lancamento l"),
    @NamedQuery(name = "Lancamento.findByIdLancamento", query = "SELECT l FROM Lancamento l WHERE l.idLancamento = :idLancamento")})
public class Lancamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_lancamento")
    private Long idLancamento;
    @JoinColumn(name = "mesa_mesa_id", referencedColumnName = "mesa_id")
    @ManyToOne
    private Mesa mesaMesaId;
    @JoinColumn(name = "produto_id_produto", referencedColumnName = "id_produto")
    @ManyToOne
    private Produto produtoIdProduto;
    @JoinColumn(name = "quantidade_id", referencedColumnName = "id")
    @ManyToOne
    private Quantidade quantidadeId;

    public Lancamento() {
    }

    public Lancamento(Long idLancamento) {
        this.idLancamento = idLancamento;
    }

    public Long getIdLancamento() {
        return idLancamento;
    }

    public void setIdLancamento(Long idLancamento) {
        this.idLancamento = idLancamento;
    }

    public Mesa getMesaMesaId() {
        return mesaMesaId;
    }

    public void setMesaMesaId(Mesa mesaMesaId) {
        this.mesaMesaId = mesaMesaId;
    }

    public Produto getProdutoIdProduto() {
        return produtoIdProduto;
    }

    public void setProdutoIdProduto(Produto produtoIdProduto) {
        this.produtoIdProduto = produtoIdProduto;
    }

    public Quantidade getQuantidadeId() {
        return quantidadeId;
    }

    public void setQuantidadeId(Quantidade quantidadeId) {
        this.quantidadeId = quantidadeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLancamento != null ? idLancamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lancamento)) {
            return false;
        }
        Lancamento other = (Lancamento) object;
        if ((this.idLancamento == null && other.idLancamento != null) || (this.idLancamento != null && !this.idLancamento.equals(other.idLancamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Lancamento[ idLancamento=" + idLancamento + " ]";
    }
    
}
