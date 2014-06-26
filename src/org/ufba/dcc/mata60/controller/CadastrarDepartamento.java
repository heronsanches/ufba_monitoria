package org.ufba.dcc.mata60.controller;

import java.sql.SQLException;

import org.ufba.dcc.mata60.model.Departamento;
import org.ufba.dcc.mata60.model.DepartamentoDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;
import org.zkoss.zul.Textbox;

import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;


public class CadastrarDepartamento extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Textbox nome;
	
	
	private void atualizar(){
		
		Include include = (Include)Selectors.iterable(nome.getPage(), "#mainInclude").iterator().next();
	    include.setSrc(null);
	    include.setSrc("/template_departamento.zul");
		
	}
	
	
	@Listen("onClick = #btn_cadastrar")
	public void cadastrar() throws SQLException, Exception{
		
		DepartamentoDAO departamentoDAO = new DepartamentoDAO();
		Departamento departamento = new Departamento();
		departamento.setNome( nome.getValue());
		
		if(departamentoDAO.insert(departamento) > 0){
			
			Mensagem.sucesso();
			atualizar();
			
		}else
			Mensagem.insucesso();
		
	}
	
	@Listen("onClick = #btn_apagarCampos")
	public void apagaCampos(){
		
	//TODO
		
	}
	
}

