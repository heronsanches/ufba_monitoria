package org.ufba.dcc.mata60.model;

import java.util.Date;

public class Turma {

	private String numero;
	private Date hora_inicio;
	private Date hora_fim;
	private Date data_inicio;
	private String semestre; // ano+.+semestre
	private String disciplina_cod;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(Date hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public Date getHora_fim() {
		return hora_fim;
	}

	public void setHora_fim(Date hora_fim) {
		this.hora_fim = hora_fim;
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getDisciplina_cod() {
		return disciplina_cod;
	}

	public void setDisciplina_cod(String disciplina_cod) {
		this.disciplina_cod = disciplina_cod;
	}

}
