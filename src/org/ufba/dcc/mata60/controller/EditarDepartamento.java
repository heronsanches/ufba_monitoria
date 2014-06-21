package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Departamento;
import org.ufba.dcc.mata60.model.DepartamentoDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.ListModels;
import org.zkoss.zul.Textbox;

public class EditarDepartamento extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Combobox cbx_departamento;
	
	@Wire
	private Button btn_atualizar;
	
	@Wire
	private Textbox novoNome;
	
	
	private void atualizar(){
		
		Include include = (Include)Selectors.iterable(cbx_departamento.getPage(), "#mainInclude").iterator().next();
	    include.setSrc(null);
	    include.setSrc("/template_departamento.zul");
		
	}
	
	
	private ArrayList<String> obtemDepartamentos(){
		
		DepartamentoDAO departamentoDAO = new DepartamentoDAO();
		ArrayList<String> departamentos = new ArrayList<String>();
				
		for(Departamento dep: departamentoDAO.getAll())
			departamentos.add(dep.getNome()+"-"+dep.getCod());
		
		return departamentos;
		
	}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {

		super.doAfterCompose(comp);
   	
		cbx_departamento.setModel(ListModels.toListSubModel(new ListModelList<>(obtemDepartamentos())));

	}
	
	
	@Listen("onClick=#btn_atualizar")
	public void edita(){
		
		DepartamentoDAO departamentoDAO = new DepartamentoDAO();
		
		Departamento departamento = new Departamento();
		departamento.setNome(novoNome.getValue());
		departamento.setCod((int)Integer.valueOf(cbx_departamento.getSelectedItem().getLabel()
			.split("-")[1].trim()));
		
		if(departamentoDAO.updateOne(departamento) > 0){
			
			Mensagem.sucessoUpdate();
			atualizar();
			
		}else
			Mensagem.insucessoUpdate();
		
	}

}
