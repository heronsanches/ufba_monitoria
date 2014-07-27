package org.ufba.dcc.mata60.model;

import java.util.Date;

public class Edital {
	
	private int cod;
	private String informacoes_adicionais;
	private int n_vagas; //banco: inteiro de tamanho 2
	private Date data_inicio; //banco: datetime
	private Date data_fim; //banco: datetime
	private String documentos_necessarios;
	private int projeto_cod;
	
	
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getInformacoes_adicionais() {
		return informacoes_adicionais;
	}
	public void setInformacoes_adicionais(String informacoes_adicionais) {
		this.informacoes_adicionais = informacoes_adicionais;
	}
	public int getN_vagas() {
		return n_vagas;
	}
	public void setN_vagas(int n_vagas) {
		this.n_vagas = n_vagas;
	}
	public Date getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}
	public Date getData_fim() {
		return data_fim;
	}
	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}
	public String getDocumentos_necessarios() {
		return documentos_necessarios;
	}
	public void setDocumentos_necessarios(String documentos_necessarios) {
		this.documentos_necessarios = documentos_necessarios;
	}
	public int getProjeto_cod() {
		return projeto_cod;
	}
	public void setProjeto_cod(int projeto_cod) {
		this.projeto_cod = projeto_cod;
	}
	
	
	

}
