package org.ufba.dcc.mata60.controller;

import org.zkoss.zul.Messagebox;

public abstract class Mensagem {
	
	public static final String CADASTRO_SUCESSO = "Cadastro Realizado com Sucesso!"; 
	public static final String CADASTRO_INSUCESSO = "Não foi Possível Realizar o Cadastro, \n"
			+ "Por Favor Verifique as Informações Contidas nos Campos e Tente Novamente!";
	
	
	public static void sucesso(){
		
		Messagebox msg = new Messagebox();
		msg.show(Mensagem.CADASTRO_SUCESSO, "", Messagebox.OK, Messagebox.ON_OK);
		
	}
	
	public static void insucesso(){
		
		Messagebox msg = new Messagebox();
		msg.show(Mensagem.CADASTRO_INSUCESSO, "", Messagebox.OK, Messagebox.EXCLAMATION);
		
	}
	
	
}
