package org.ufba.dcc.mata60.controller;

import org.zkoss.zul.Messagebox;

public abstract class Mensagem {

	private static final String CADASTRO_SUCESSO = "Cadastro Realizado com Sucesso!";
	private static final String CADASTRO_INSUCESSO = "Não foi Possível Realizar o Cadastro, \n"
			+ "Por Favor Verifique as Informações Contidas nos Campos e Tente Novamente!";

	private static final String UPDATE_SUCESSO = "Atualização Realizada com Sucesso!";
	private static final String UPDATE_INSUCESSO = "Atualização não Realizada!";
	private static final String DELETE_SUCESSO = "Deleção Realizada com Sucesso!";
	private static final String DELETE_INSUCESSO = "Deleção não Realizada!";
	private static final String UPLOAD_ATA_SUCESSO = "Arquivo enviado!";
	private static final String UPLOAD_ATA_INSUCESSO = "Arquivo deve está no formato pdf,"
			+ " arquivo não enviado!";


	public static void sucesso() {

		Messagebox msg = new Messagebox();
		msg.show(Mensagem.CADASTRO_SUCESSO, "", Messagebox.OK, Messagebox.ON_OK);

	}

	public static void insucesso() {

		Messagebox msg = new Messagebox();
		msg.show(Mensagem.CADASTRO_INSUCESSO, "", Messagebox.OK,
				Messagebox.EXCLAMATION);

	}

	public static void sucessoUpdate() {

		Messagebox msg = new Messagebox();
		msg.show(Mensagem.UPDATE_SUCESSO, "", Messagebox.OK, Messagebox.ON_OK);

	}

	public static void insucessoUpdate() {

		Messagebox msg = new Messagebox();
		msg.show(Mensagem.UPDATE_INSUCESSO, "", Messagebox.OK,
				Messagebox.EXCLAMATION);

	}
	
	public static void sucessoDelete() {

		Messagebox msg = new Messagebox();
		msg.show(Mensagem.DELETE_SUCESSO, "", Messagebox.OK, Messagebox.ON_OK);

	}

	public static void insucessoDelete() {

		Messagebox msg = new Messagebox();
		msg.show(Mensagem.DELETE_INSUCESSO, "", Messagebox.OK,
				Messagebox.EXCLAMATION);

	}
	
	public static void sucessoUploadAta() {

		Messagebox msg = new Messagebox();
		msg.show(Mensagem.UPLOAD_ATA_SUCESSO, "", Messagebox.OK,
				Messagebox.ON_OK);

	}
	
	public static void insucessoUploadAta() {

		Messagebox msg = new Messagebox();
		msg.show(Mensagem.UPLOAD_ATA_INSUCESSO, "", Messagebox.OK,
				Messagebox.EXCLAMATION);

	}

}
