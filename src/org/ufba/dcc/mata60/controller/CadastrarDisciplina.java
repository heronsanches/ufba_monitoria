package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ufba.dcc.mata60.model.Departamento;
import org.ufba.dcc.mata60.model.DepartamentoDAO;
import org.ufba.dcc.mata60.model.Disciplina;
import org.ufba.dcc.mata60.model.DisciplinaDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class CadastrarDisciplina extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
    @Wire
    private Listbox listbox_departamentoNomes;
    
    @Wire
    private Textbox nome;
    
    @Wire
    private Textbox codigo_disciplina;
    
  //<nome, cod> de departamento
    private Map<String, Integer> departamentos = new HashMap<String, Integer>();
    

    private void obtemDepartamentos(){
    	
    	DepartamentoDAO departamentoDAO = new DepartamentoDAO();
           	
    	for(Departamento dep: 	departamentoDAO.getAll())
    		departamentos.put(dep.getNome(), dep.getCod());
        
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception{
          
    	super.doAfterCompose(comp);
    	departamentos.clear();
    	obtemDepartamentos();
     
    	ArrayList<String> listaDepartamentos = new ArrayList<String>();
    	
    	for(String depNome: departamentos.keySet())
    		listaDepartamentos.add(depNome);
    		
    	ListModelList<String> modelDepartamentos = new  ListModelList<String>(listaDepartamentos);
     
    	listbox_departamentoNomes.setModel(modelDepartamentos);
    
    }
    
    @Listen("onClick = #btn_cadastrar")
    public void cadastrarDisciplina(){
    	
    	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    	
    	Disciplina disciplina = new Disciplina();
    	disciplina.setCod(codigo_disciplina.getValue());
    	disciplina.setDepartamento_cod(departamentos.get(
    		listbox_departamentoNomes.getSelectedItem().getValue()));
    	disciplina.setNome(nome.getValue());
    	
    	if(disciplinaDAO.insert(disciplina) > 0){
    		
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

