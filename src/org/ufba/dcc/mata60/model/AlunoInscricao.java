package org.ufba.dcc.mata60.model;

import java.util.Date;

public class AlunoInscricao {

	private int editalCod;
	private String alunoCPF;
	private Date dataInscricao;
	private float notaProcessoSeletivo;
	private int numero;

	public int getEditalCod() {
		return editalCod;
	}

	public void setEditalCod(int editalCod) {
		this.editalCod = editalCod;
	}

	public String getAlunoCPF() {
		return alunoCPF;
	}

	public void setAlunoCPF(String alunoCPF) {
		this.alunoCPF = alunoCPF;
	}

	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public float getNotaProcessoSeletivo() {
		return notaProcessoSeletivo;
	}

	public void setNotaProcessoSeletivo(float notaProcessoSeletivo) {
		this.notaProcessoSeletivo = notaProcessoSeletivo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
