package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.ListModels;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

public class CadastrarProjeto extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	private Textbox codigo;
	
	@Wire
	private Textbox descricao;

	@Wire
	private Combobox cbx_professor;
	
	@Wire
	private Combobox cbx_turma;
	
	@Wire
	private Textbox atividades_gerais;
	
	private void atualizar() {
		Include include = (Include) Selectors
				.iterable(codigo.getPage(), "#mainInclude").iterator().next();
		include.setSrc(null);
		include.setSrc("/template_projeto.zul");
	}

	private ArrayList<String> obtemProfessores() {

		ArrayList<String> professores = new ArrayList<String>();
		
		for (Professor prof : new ProfessorDAO().getAll())
			professores.add(prof.getNome()+"-"+prof.getCpf());
		
		return professores;

	}

	private ArrayList<String> obtemTurmas() {

		ArrayList<String> turmas = new ArrayList<String>();
		
		for (Turma turma : new TurmaDAO().getAll())
			turmas.add(turma.getDisciplina_cod()+"-"+turma.getSemestre()+"-"+turma.getNumero());
		
		return turmas;

	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {

		super.doAfterCompose(comp);
		cbx_professor.setModel(ListModels.toListSubModel(new ListModelList<>(obtemProfessores())));
		cbx_turma.setModel(ListModels.toListSubModel(new ListModelList<>(obtemTurmas())));

	}

	@Listen("onClick = #btn_cadastrar")
	public void cadastrarProjeto() {

		ProjetoDAO projetoDAO = new ProjetoDAO();
		Projeto projeto = new Projeto();

		projeto.setCod(Integer.parseInt(codigo.getValue()));
		projeto.setDescricao(descricao.getValue());
		projeto.setProfessorCpf(cbx_professor.getSelectedItem().getLabel().split("-")[1].trim());
		
		String turma[] = cbx_turma.getSelectedItem().getLabel().split("-");
		projeto.setTurmaDisciplinaCod(turma[0].trim());
		projeto.setTurmaSemestre(turma[1].trim());
		projeto.setTurmaNumero(turma[2].trim());
		
		projeto.setAtividades_gerais(atividades_gerais.getValue());

		if (projetoDAO.insert(projeto) > 0) {

			atualizar();
			Mensagem.sucesso();

		} else {
			Mensagem.insucesso();
		}
	}

}
