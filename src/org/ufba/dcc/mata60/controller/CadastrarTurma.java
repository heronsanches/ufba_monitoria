package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Disciplina;
import org.ufba.dcc.mata60.model.DisciplinaDAO;
import org.ufba.dcc.mata60.model.Turma;
import org.ufba.dcc.mata60.model.TurmaDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Include;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.ListModels;
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
	 private Combobox cbx_disciplina;
	 
	 @Wire
	 private Intbox ano;
	 
	 @Wire
	 private Intbox semestre;
	  

	    private ArrayList<String> obtemDisciplinas(){
	    	
	    	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	    	ArrayList<String> disciplinas = new ArrayList<String>();
	           	
	    	for(Disciplina dis: disciplinaDAO.getAll())
	    		disciplinas.add(dis.getNome()+"-"+dis.getCod());
	    	
	    	return disciplinas;
	        
	    }
	    
	    
		private void atualizar(){
			
			Include include = (Include)Selectors.iterable(cbx_disciplina.getPage(), "#mainInclude").iterator().next();
		    include.setSrc(null);
		    include.setSrc("/template_turma.zul");
			
		}
		

	    @Override
	    public void doAfterCompose(Component comp) throws Exception{
	          
	    	super.doAfterCompose(comp);
			cbx_disciplina.setModel(ListModels.toListSubModel(new ListModelList<>(obtemDisciplinas())));

	    }
	    
	    
	    @Listen("onClick = #btn_cadastrar")
	    public void cadastrarTurma(){
	    	
	    	TurmaDAO turmaDAO = new TurmaDAO();
	    	Turma turma = new Turma();
	    	
	    	turma.setData_inicio(data_inicio.getValue());
	    	turma.setDisciplina_cod(cbx_disciplina.getSelectedItem().getLabel().split("-")[1].trim());
	    	turma.setHora_fim(hora_fim.getValue());
	    	turma.setHora_inicio(hora_inicio.getValue());
	    	turma.setNumero(numero.getValue().toString());
	    	turma.setSemestre(ano.getValue().toString() +"."+ semestre.getValue().toString());
	    	
	    	if(turmaDAO.insert(turma) > 0){
	    		
	    		Mensagem.sucesso();
	    		atualizar();
	    		
	    	}else
	    		Mensagem.insucesso();
	    	
	    }
	 
}
