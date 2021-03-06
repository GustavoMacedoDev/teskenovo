/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.macedo.sistemas.bean;

import br.com.macedo.sistemas.domain.Lancamento;
import br.com.macedo.sistemas.domain.Mesa;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Gustavo
 */
@Stateless
public class LancamentoFacade extends AbstractFacade<Lancamento> {

    @EJB
    private MesaFacade mesaFacade;

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

    @Override
    public void create(Lancamento lancamento) {
        
        double total;
        
        double valorProduto = lancamento.getProdutoIdProduto().getValorProduto();
        
        int quantidade = lancamento.getQuantidadeId().getQuantidade();
        
        total = valorProduto * quantidade;
        
        lancamento.setValorTotalLancamento(total);
        
       // mesaFacade.insereValorMesa(lancamento.getMesaMesaId().getMesaId(), total);
        
        em.persist(lancamento);
        
        System.out.println("Awquiiii" + lancamento);
    }
    
    
    public List<Lancamento> getByMesa(Long id) {
        
        String jpql = "from Lancamento l where l.mesa.mesaId = :id and l.impresso = 0";
        TypedQuery<Lancamento> query = em.createQuery(jpql, Lancamento.class);
        query.setParameter("id", id);
        
        System.out.println("Resultado " + query.getResultList());
        
        return query.getResultList();
        
    }
    
    public List<Lancamento> getLancamentosMesa(Long id) {
        
        System.out.println("id chegado" + id);
        
        String jpql = "from Lancamento l where l.mesa.mesaId = :id";
        TypedQuery<Lancamento> query = em.createQuery(jpql, Lancamento.class);
        query.setParameter("id", id);
        
        System.out.println("Resultado " + query.getResultList());
        
        return query.getResultList();
        
    }
    
    public void atualizaImpressao(List lancamentos) {
        List<Lancamento> lancs;
        lancs = lancamentos;
        
        for(Lancamento lanc: lancs) {
            Long id;
            id = lanc.getIdLancamento();
            
            System.out.println("ids no for: "+ id);
            
            String jpql = "UPDATE lancamento SET impresso = 1 WHERE id_lancamento = " + id;
            Query query = em.createNativeQuery(jpql);
            query.setParameter("id", id);
            query.executeUpdate();
            
            System.out.println("sql: "+ jpql);
        }
        
        
    }
    
   public double buscaValorTotalPorMesa(Long id) {
       
       String jpql = "from Lancamento l where l.mesa.mesaId = :id";
       TypedQuery<Lancamento> query = em.createQuery(jpql, Lancamento.class);
       query.setParameter("id", id);
       
       List<Lancamento> lancamentos;
       
       lancamentos = query.getResultList();
       
       double total = 0.0;
       
       for(Lancamento l: lancamentos) {
           total = l.getValorTotalLancamento();
       }
       
       System.out.println("total buscado" + total);
       
       
       return total;
   }
   
   public void atualizaPago(Long id) {
       
      
        String jpql = "UPDATE lancamento SET pago = 1 WHERE mesa_mesa_id = " + id;
        Query query = em.createNativeQuery(jpql);
        query.setParameter("id", id);
        query.executeUpdate();
   }      
    
}
