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
    @NamedQuery(name = "Lancamento.findByIdLancamento",
            query = "SELECT l FROM Lancamento l WHERE l.idLancamento = :idLancamento"),
    @NamedQuery(name = "Lancamento.findByMesa", 
            query = "SELECT l FROM Lancamento l, Mesa m")})

public class Lancamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_lancamento")
    private Long idLancamento;
    
    @JoinColumn(name = "mesa_mesa_id", referencedColumnName = "mesa_id")
    @ManyToOne
    private Mesa mesa;
    
    @JoinColumn(name = "produto_id_produto", referencedColumnName = "id_produto")
    @ManyToOne
    private Produto produtoIdProduto;
    
    @JoinColumn(name = "quantidade_id", referencedColumnName = "id")
    @ManyToOne
    private Quantidade quantidadeId;
    
    @Column
    private int impresso;
    
    @Column
    private String observacao;
    
    @Column(name = "valor_total_lancamento")
    private double valorTotalLancamento;
    
    @Column(name = "valor_pago")
    private double valorPago;
    
    @JoinColumn(name = "adicional_id", referencedColumnName = "id_adicional")
    @ManyToOne
    private Adicional adicional1;
    
    @Column(name = "pago")
    private int pago;

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
        return mesa;
    }

    public void setMesaMesaId(Mesa mesa) {
        this.mesa = mesa;
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
        
        System.out.println("" + produtoIdProduto);
        
        return "Produto: " + produtoIdProduto.getProduto() 
                + " Valor: " + produtoIdProduto.getValorProduto()
                + " Quantidade: " + quantidadeId.getQuantidade();
    }

    

    public int getImpresso() {
        return impresso;
    }

    public void setImpresso(int impresso) {
        this.impresso = impresso;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public double getValorTotalLancamento() {
        return valorTotalLancamento;
    }

    public void setValorTotalLancamento(double valorTotalLancamento) {
        this.valorTotalLancamento = valorTotalLancamento;
    }

    public Adicional getAdicional() {
        return adicional1;
    }

    public void setAdicional(Adicional adicional1) {
        this.adicional1 = adicional1;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    
    
}
