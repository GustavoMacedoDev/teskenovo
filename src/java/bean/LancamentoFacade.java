/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import domain.Lancamento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Gustavo
 */
@Stateless
public class LancamentoFacade extends AbstractFacade<Lancamento> {

    @PersistenceContext(unitName = "TeskePU")
    private EntityManager em;
    private Class<Lancamento> entityClass;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LancamentoFacade() {
        super(Lancamento.class);
    }

    public void create(Lancamento lancamento) {
        
        double total;
        double valorProduto = lancamento.getProdutoIdProduto().getValorProduto();
        int quantidade = lancamento.getQuantidadeId().getQuantidade();
        
        total = valorProduto * quantidade;
        
        lancamento.setValorTotalLancamento(total);
        
        em.persist(lancamento);
        System.out.println("Awquiiii" + lancamento);
    }
    
    
    public List<Lancamento> getByMesa(Long id) {
        
        System.out.println("id chegado" + id);
        
        String jpql = "from Lancamento l where l.mesa.mesaId = :id";
        TypedQuery<Lancamento> query = em.createQuery(jpql, Lancamento.class);
        query.setParameter("id", id);
        
        System.out.println("Resultado " + query.getResultList());
        
        return query.getResultList();
        
    }
    
    
   
    
}
