package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ufba.dcc.mata60.model.Departamento;
import org.ufba.dcc.mata60.model.DepartamentoDAO;
import org.ufba.dcc.mata60.model.Professor;
import org.ufba.dcc.mata60.model.ProfessorDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

public class CadastrarProfessor extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire
	Textbox nome;

	@Wire
	Textbox matricula;
	
	@Wire
	Textbox cpf;
	
	@Wire
    private Listbox listbox_departamentoNomes;
	
	@Wire
	private Radiogroup rd_group_tipoProfessores;
    
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
	public void cadastrarProfessor(){
		
		ProfessorDAO professorDAO = new ProfessorDAO();
		Professor professor = new Professor();
		
		professor.setCpf(cpf.getValue());
		professor.setDepartamento_cod(departamentos.get(listbox_departamentoNomes.getSelectedItem().getValue()));
		professor.setMatricula(matricula.getValue());
		professor.setNome(nome.getValue());
		professor.setTipo(rd_group_tipoProfessores.getSelectedItem().getLabel());
		
		if(professorDAO.insert(professor) > 0){
			
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
