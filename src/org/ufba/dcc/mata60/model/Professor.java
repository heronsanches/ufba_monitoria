package org.ufba.dcc.mata60.model;

public class Professor {
	
	private String cpf;
	private String matricula;
	private String nome;
	private int departamento_cod;
	private String tipo;
	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getDepartamento_cod() {
		return departamento_cod;
	}
	public void setDepartamento_cod(int departamento_cod) {
		this.departamento_cod = departamento_cod;
	}
	
	
}
