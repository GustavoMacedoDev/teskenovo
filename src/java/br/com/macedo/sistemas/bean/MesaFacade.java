/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.macedo.sistemas.bean;

import br.com.macedo.sistemas.domain.Mesa;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Gustavo
 */
@Stateless
public class MesaFacade extends AbstractFacade<Mesa> {

    @PersistenceContext(unitName = "TeskePU")
    private EntityManager em;
    
    @EJB
    private LancamentoFacade facade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MesaFacade() {
        super(Mesa.class);
    }
    
    public void insereValorMesa(Long mesa, Double valorLancado) {
        
        double valorParcial = 0.0;
        
        Mesa mesaParcial = em.find(Mesa.class, mesa);
        
        valorParcial = mesaParcial.getValorPago();
        
        double total = 0.0;
        
        total = valorParcial + valorLancado;
        
        mesaParcial.setValorPago(total);
        
        em.merge(mesaParcial);
        
    }
    
    public Mesa valorTotalMesa(Mesa mesa) {
        return em.find(Mesa.class, mesa.getMesaId());
        
    }
    
    public double buscaValorPago(Long id){
        double valorPago = 0.0;
        Mesa valorPagoMesa;
        valorPagoMesa = em.find(Mesa.class, id);
        
         System.out.println("valor pago do banco" + valorPagoMesa);
        
        valorPago = valorPagoMesa.getValorPago();
        
       
        
        return valorPago;
    }

    public void subtraiValorMesa(Long mesaId, double valorPago) {
        Mesa mesa;
        
        mesa = em.find(Mesa.class, mesaId);
        
        double valorPagoBusca = 0.0;
        
        valorPagoBusca = buscaValorPago(mesaId);
        
        double total = 0.0;
        
        total = valorPagoBusca - valorPago;
        
        mesa.setValorPago(total);
        
        em.merge(mesa);
        
        
        
    }
    
    public void fechaMesa(Long id) {
        
        System.out.println("id do encerrmaento" + id);
        
        String jpql = "UPDATE mesa SET status_mesa = 1 WHERE mesa_id = " + id;
            Query query = em.createNativeQuery(jpql);
            query.setParameter("id", id);
            query.executeUpdate();
            
        
         facade.atualizaPago(id);
            
    }
    
    
}
