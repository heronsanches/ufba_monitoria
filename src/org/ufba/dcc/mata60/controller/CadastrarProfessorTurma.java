package org.ufba.dcc.mata60.controller;

import java.sql.Time;
import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Disciplina;
import org.ufba.dcc.mata60.model.DisciplinaDAO;
import org.ufba.dcc.mata60.model.Professor;
import org.ufba.dcc.mata60.model.ProfessorDAO;
import org.ufba.dcc.mata60.model.Turma;
import org.ufba.dcc.mata60.model.TurmaDAO;
import org.ufba.dcc.mata60.model.TurmaLecionadaProfessorDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.ListModels;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Radiogroup;

public class CadastrarProfessorTurma extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;

	@Wire
	private Combobox cbx_professor;
	
	@Wire
	private Combobox cbx_disciplina;
	
	@Wire
	private Listbox listbox_turma;
	
	@Wire
	private Radiogroup rd_group_semestre;
	
	@Wire
	private Intbox ano;
	
	@Wire
	private Button btn_cadastrar;
	
	
	 private ArrayList<String> obtemProfessores(){
	    	
		 ProfessorDAO professorDAO = new ProfessorDAO();
		 ArrayList<String> professores = new ArrayList<String>();
		 
		 for(Professor p: professorDAO.getAll())
			 professores.add(p.getNome()+" - "+p.getCpf());
		 
		 return professores;
	    	
	  }
	 
	 private ArrayList<String> obtemDisciplinas(){
	    	
		 DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		 ArrayList<String> disciplinas = new ArrayList<String>();
		 
		 for(Disciplina d: disciplinaDAO.getAll())
			 disciplinas.add(d.getNome()+" - "+d.getCod());
		 
		 return disciplinas;
	    	
	  }
	 

	 @Override
	public void doAfterCompose(Component comp) throws Exception {

		super.doAfterCompose(comp);
    	
		cbx_professor.setModel(ListModels.toListSubModel(new ListModelList<>(obtemProfessores())));
		cbx_disciplina.setModel(ListModels.toListSubModel(new ListModelList<>(obtemDisciplinas())));

	}
	
	@Listen("onSelect = #cbx_disciplina")
	public void listaTurmas(){

		TurmaDAO turmaDAO = new TurmaDAO();
		
		String semestre = ano.getValue().toString()+"."+rd_group_semestre.getSelectedItem().getLabel();
		String disciplina_cod = cbx_disciplina.getSelectedItem().getLabel().split("-")[1].trim();
		
		ArrayList<Turma> turmas = turmaDAO.getBySemestreDisciplinaCod(semestre, disciplina_cod);
				
		ArrayList<String> turmasInformacoes = new ArrayList<String>();
		
		for(Turma t: turmas){
			
			turmasInformacoes.add(t.getNumero()+" --- inicio: "+t.getData_inicio()+" --- horario: "+
					((Time)t.getHora_inicio()).toString()+" / "+((Time)t.getHora_fim()).toString());
			
		}
		
		ListModelList<String> modelTurmas = new  ListModelList<String>(turmasInformacoes);
	     
    	listbox_turma.setModel(modelTurmas);
		
		
	}
	
	@Listen("onClick = #btn_cadastrar")
	public void cadastrar(){
		
		TurmaLecionadaProfessorDAO turmaLecionadaProfessorDAO = new TurmaLecionadaProfessorDAO();
		
		String turma_semestre = ano.getValue().toString()+"."+rd_group_semestre.getSelectedItem().getLabel();
		String turma_disciplina_cod = cbx_disciplina.getSelectedItem().getLabel().split("-")[1].trim();
		String professor_cpf = cbx_professor.getSelectedItem().getLabel().split("-")[1].trim();
		String turma_numero = listbox_turma.getSelectedItem().getLabel().split("-")[0].trim();
				
		if(turmaLecionadaProfessorDAO.insert(turma_numero, professor_cpf, 
				turma_disciplina_cod, turma_semestre) > 0){
			
			Mensagem.sucesso();
			//TODO clear(); 
			
		}else
			Mensagem.insucesso();
		
	}
 

}
