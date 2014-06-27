package org.ufba.dcc.mata60.model;

import java.util.Date;

public class Projeto {

	private int cod;
	private String ataAprovacao;
	private Date dataAprovacao;
	private String descricao;
	private String turmaNumero;
	private String professorCpf;
	private String turmaDisciplinaCod;
	private String turmaSemestre;

	public String getTurmaDisciplinaCod() {
		return turmaDisciplinaCod;
	}

	public void setTurmaDisciplinaCod(String turmaDisciplinaCod) {
		this.turmaDisciplinaCod = turmaDisciplinaCod;
	}

	public String getTurmaSemestre() {
		return turmaSemestre;
	}

	public void setTurmaSemestre(String turmaDisciplinaSemestre) {
		this.turmaSemestre = turmaDisciplinaSemestre;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getAtaAprovacao() {
		return ataAprovacao;
	}

	public void setAtaAprovacao(String ataAprovacao) {
		this.ataAprovacao = ataAprovacao;
	}

	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTurmaNumero() {
		return turmaNumero;
	}

	public void setTurmaNumero(String turmaNumero) {
		this.turmaNumero = turmaNumero;
	}

	public String getProfessorCpf() {
		return professorCpf;
	}

	public void setProfessorCpf(String cpf) {
		this.professorCpf = cpf;
	}

	public void setTurmaValues(Turma t) {
		this.turmaDisciplinaCod = t.getDisciplina_cod();
		this.turmaNumero = t.getNumero();
		this.turmaSemestre = t.getSemestre();
	}
	
}

