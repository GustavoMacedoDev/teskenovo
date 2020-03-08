/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.macedo.sistemas.bean;

import br.com.macedo.sistemas.domain.Mesa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gustavo
 */
@Stateless
public class MesaFacade extends AbstractFacade<Mesa> {

    @PersistenceContext(unitName = "TeskePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MesaFacade() {
        super(Mesa.class);
    }
    
    public void insereValorMesa(Long mesa, Double valorLancado) {
        
        System.out.println("mesa chegada " + mesa + "valor " + valorLancado);
                         
        double valorParcial = 0.0;
        
        Mesa mesaParcial = em.find(Mesa.class, mesa);
        
        System.out.println("mesa parcial = " + mesaParcial.getNumeroMesa());
        
        valorParcial = mesaParcial.getValorPago();
        
        System.out.println("Valor parcial" + valorParcial);
        
        double total = 0.0;
        
        total = valorParcial + valorLancado;
        
        System.out.println("valor total " + total);
        
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
}
