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
	private static final String ARQUIVO_CAMINHO_VAZIO = "Não existe arquivo de ata para exibir.\n"
			+ "Selecione a aba editar para selecionar e salvar um arquivo de ata.";
	private static final String ARQUIVO_NAO_EXISTE = "Arquivo de ata não existe mais no local atualmente"
			+ " especificado.\n";
	private static final String INSCRICAO_ENCERRADA = "Edital fechado.\n"
			+ "Período de inscrição encerrado.";
	private static final String INSCRICAO_SUCESSO = "Inscricao efetuada com sucesso.";
	private static final String INSCRICAO_EDITAL_INSUCESSO = "Não foi possível efetuar inscrição de aluno.\nVerifique se o mesmo possui cadastro no sistema";
	private static final String ALUNO_INSCRITO_EDITAL = "Não foi possível efetuar inscrição de aluno, pis ele já está inscrito nesse edital";

	
	public static void inscricaoEncerrada() {

		Messagebox msg = new Messagebox();
		msg.show(Mensagem.INSCRICAO_ENCERRADA, "", Messagebox.OK, Messagebox.EXCLAMATION);

	}
	
	public static void arquivoCaminhoVazio() {

		Messagebox msg = new Messagebox();
		msg.show(Mensagem.ARQUIVO_CAMINHO_VAZIO, "", Messagebox.OK, Messagebox.EXCLAMATION);

	}
	
	public static void arquivoNaoExiste() {

		Messagebox msg = new Messagebox();
		msg.show(Mensagem.ARQUIVO_NAO_EXISTE, "", Messagebox.OK, Messagebox.EXCLAMATION);

	}


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
	
	public static void sucessoInscricao() {
		
		Messagebox msg = new Messagebox();
		msg.show(Mensagem.INSCRICAO_SUCESSO, "", Messagebox.OK,
				Messagebox.ON_OK);
	}

	public static void insucessoInscricaoEdital() {
		
		Messagebox msg = new Messagebox();
		msg.show(Mensagem.INSCRICAO_EDITAL_INSUCESSO, "", Messagebox.OK,
				Messagebox.EXCLAMATION);
	}
	
public static void alunoInscritoEdital() {
		
		Messagebox msg = new Messagebox();
		msg.show(Mensagem.ALUNO_INSCRITO_EDITAL, "", Messagebox.OK,
				Messagebox.EXCLAMATION);
	}
	
}
