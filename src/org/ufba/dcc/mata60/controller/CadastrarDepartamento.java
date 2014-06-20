package org.ufba.dcc.mata60.controller;

import java.sql.SQLException;

import org.ufba.dcc.mata60.model.Departamento;
import org.ufba.dcc.mata60.model.DepartamentoDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;


public class CadastrarDepartamento extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Textbox nome;
	
	@Listen("onClick = #btn_cadastrar")
	public void cadastrar() throws SQLException, Exception{
		
		DepartamentoDAO departamentoDAO = new DepartamentoDAO();
		Departamento departamento = new Departamento();
		departamento.setNome(nome.getValue());
		
		if(departamentoDAO.insert(departamento) > 0){
			
			Mensagem.sucesso();			
			//TODO clear();
			
		}else
			Mensagem.insucesso();
		
	}
	
	@Listen("onClick = #btn_apagarCampos")
	public void apagaCampos(){
		
	//TODO
		
	}
	
}

