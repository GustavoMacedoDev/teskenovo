/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.macedo.sistemas.helper;

/**
 *
 * @author Gustavo
 */
public class UtilErros {
    public static String getMensagemErro(Exception e){
		while (e.getCause() != null){
			e = (Exception) e.getCause();
		}
		String retorno = e.getMessage();
		return retorno;
	}
}
