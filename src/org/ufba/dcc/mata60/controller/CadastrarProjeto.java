package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ufba.dcc.mata60.model.Departamento;
import org.ufba.dcc.mata60.model.DepartamentoDAO;
import org.ufba.dcc.mata60.model.Professor;
import org.ufba.dcc.mata60.model.ProfessorDAO;
import org.ufba.dcc.mata60.model.Projeto;
import org.ufba.dcc.mata60.model.ProjetoDAO;
import org.ufba.dcc.mata60.model.Turma;
import org.ufba.dcc.mata60.model.TurmaDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

public class CadastrarProjeto extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	Textbox codigo;
	
	@Wire
	Textbox descricao;

	@Wire
	private Listbox listbox_professor;
	
	@Wire
	private Listbox listbox_turma;

	private Map<String, String> professores = new HashMap<String, String>();
	private Map<String, Turma> turmas = new HashMap<String, Turma>();
	
	private void atualizar() {
		Include include = (Include) Selectors
				.iterable(codigo.getPage(), "#mainInclude").iterator().next();
		include.setSrc(null);
		include.setSrc("/template_projeto.zul");
	}

	private void obtemProfessores() {

		ProfessorDAO professorDAO = new ProfessorDAO();

		for (Professor prof : professorDAO.getAll())
			professores.put(prof.getNome(), prof.getCpf());

	}

	private void obtemTurmas() {

		TurmaDAO turmaDAO = new TurmaDAO();

		ArrayList<String> listaTurma = new ArrayList<String>();
		
		for (Turma turma : turmaDAO.getAll()) {
			turmas.put(turma.getNumero(), turma);
			listaTurma.add(turma.getDisciplina_cod() + "-" + turma.getNumero());
		}
		
		ListModelList<String> modelTurma = new ListModelList<String>(listaTurma);

		listbox_turma.setModel(modelTurma);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {

		super.doAfterCompose(comp);
		professores.clear();
		obtemProfessores();
		obtemTurmas();

		ArrayList<String> listaProfessores = new ArrayList<String>();

		for (String profName : professores.keySet())
			listaProfessores.add(profName);

		ListModelList<String> modelProfessores = new ListModelList<String>(
				listaProfessores);

		listbox_professor.setModel(modelProfessores);

	}

	@Listen("onClick = #btn_cadastrar")
	public void cadastrarProjeto() {

		ProjetoDAO projetoDAO = new ProjetoDAO();
		Projeto projeto = new Projeto();

		projeto.setCod(Integer.parseInt(codigo.getValue()));
		projeto.setDescricao(descricao.getValue());
		projeto.setProfessorCpf(professores.get(listbox_professor.getSelectedItem().getValue()));
		projeto.setTurmaValues(
				turmas.get(
						listbox_turma.getSelectedItem().getValue().toString().split("-")[1].trim()
						)
					);

		if (projetoDAO.insert(projeto) > 0) {

			atualizar();
			Mensagem.sucesso();

		} else {
			Mensagem.insucesso();
		}
	}

}
