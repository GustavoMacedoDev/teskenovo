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
    
    public void insereValorMesa(Mesa mesa, Double valorLancado) {
        System.out.println("mesaaa " + mesa.getValorTotal());
        System.out.println("valor lancado " + valorLancado);
        
        Mesa mesaNova = valorTotalMesa(mesa);
        
        System.out.println("mesa nova" + mesaNova.getValorTotal());
        
        double valorParcial;
        
        if(mesaNova.getValorTotal() == null) {
            valorParcial = 0;
        } else {
            valorParcial = mesaNova.getValorTotal();
        }
        
        System.out.println("valor parcial " + valorParcial);
        
        double total = 0;
        total = valorLancado + valorParcial;
        mesa.setValorTotal(total);
        
        em.merge(mesa);
    }
    
    public Mesa valorTotalMesa(Mesa mesa) {
        return em.find(Mesa.class, mesa.getMesaId());
        
    }
}
