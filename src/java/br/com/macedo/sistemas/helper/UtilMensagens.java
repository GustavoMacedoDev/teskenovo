/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.macedo.sistemas.helper;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gustavo
 */
public class UtilMensagens {
    public static void mensagemErro(String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensagem,"");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public static void mensagemInformacao(String mensagem){
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,"");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}	

}
