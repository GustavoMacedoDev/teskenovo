/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.macedo.sistemas.bean;

import br.com.macedo.sistemas.domain.Adicional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gustavo
 */
@Stateless
public class AdicionalFacade extends AbstractFacade<Adicional> {

    @PersistenceContext(unitName = "TeskePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdicionalFacade() {
        super(Adicional.class);
    }
    
}
