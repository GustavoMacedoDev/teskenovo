/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import domain.Lancamento;
import domain.Mesa;
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LancamentoFacade() {
        super(Lancamento.class);
    }
    
    
    public List<Lancamento> getByMesa(String id) {
        
        System.out.println("Id : " + id);
        
        String jpql = "select lc from Lancamento lc where lc.mesa.mesaId = :id";
	TypedQuery<Lancamento> query = em.createQuery(jpql, Lancamento.class);
	query.setParameter("id", id);
	
        System.out.println("Resultado " + query.getResultList() );
        
        return query.getResultList();
    }
    
}
