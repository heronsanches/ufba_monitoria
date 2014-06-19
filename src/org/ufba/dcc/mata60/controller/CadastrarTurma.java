package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ufba.dcc.mata60.model.Disciplina;
import org.ufba.dcc.mata60.model.DisciplinaDAO;
import org.ufba.dcc.mata60.model.Turma;
import org.ufba.dcc.mata60.model.TurmaDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Timebox;

public class CadastrarTurma extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	 @Wire
	 private Intbox numero;
	 
	 @Wire
	 private Timebox hora_inicio;
	 
	 @Wire
	 private Timebox hora_fim;
	 
	 @Wire
	 private Datebox data_inicio;
	 
	 @Wire
	 private Listbox listbox_disciplinaNomes;
	 
	 @Wire
	 private Intbox ano;
	 
	 @Wire
	 private Intbox semestre;
	 
	 //<nome, cod> de Disciplina
	 private Map<String, String> disciplinas = new HashMap<String, String>();
	    

	    private void obtemDisciplinas(){
	    	
	    	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	           	
	    	for(Disciplina dis: disciplinaDAO.getAll())
	    		disciplinas.put(dis.getNome(), dis.getCod());
	        
	    }

	    @Override
	    public void doAfterCompose(Component comp) throws Exception{
	          
	    	super.doAfterCompose(comp);
	    	disciplinas.clear();
	    	obtemDisciplinas();
	     
	    	ArrayList<String> listaDisciplinas = new ArrayList<String>();
	    	
	    	for(String nomeDisciplina: disciplinas.keySet())
	    		listaDisciplinas.add(nomeDisciplina);
	    		
	    	ListModelList<String> modelDisciplinas = new  ListModelList<String>(listaDisciplinas);
	     
	    	listbox_disciplinaNomes.setModel(modelDisciplinas);
	    
	    }
	    
	    @Listen("onClick = #btn_cadastrar")
	    public void cadastrarTurma(){
	    	
	    	TurmaDAO turmaDAO = new TurmaDAO();
	    	Turma turma = new Turma();
	    	
	    	turma.setData_inicio(data_inicio.getValue());
	    	turma.setDisciplina_cod(disciplinas.get(listbox_disciplinaNomes.getSelectedItem().getValue()));
	    	turma.setHora_fim(hora_fim.getValue());
	    	turma.setHora_inicio(hora_inicio.getValue());
	    	turma.setNumero(numero.getValue().toString());
	    	turma.setSemestre(ano.getValue().toString() +"."+ semestre.getValue().toString());
	    	
	    	turmaDAO.insert(turma);
	    	
	    }
	 
}
