package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ufba.dcc.mata60.model.Departamento;
import org.ufba.dcc.mata60.model.DepartamentoDAO;
import org.ufba.dcc.mata60.model.Disciplina;
import org.ufba.dcc.mata60.model.DisciplinaDAO;
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
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.ListModels;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;

public class EditarProjeto extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	private Combobox cbx_projeto;

	@Wire
	private Button btn_atualizar;

	@Wire
	Textbox codigo;
	
	@Wire
	Textbox descricao;
	
	@Wire
	Textbox ata_aprovacao;
	
	@Wire
	 private Datebox data_aprovacao;

	@Wire
	private Listbox listbox_professor;
	
	@Wire
	private Listbox listbox_turma;

	@Wire
	private Listbox listar_projeto;

	private Map<String, String> professores = new HashMap<String, String>();
	private Map<String, Turma> turmas = new HashMap<String, Turma>();
	
	private ListModelList<String> modelProfessor;
	private ListModelList<String> modelTurma;
	
	private void atualizar() {

		Include include = (Include) Selectors
				.iterable(cbx_projeto.getPage(), "#mainInclude")
				.iterator().next();
		include.setSrc(null);
		include.setSrc("/template_projeto.zul");

	}

	private void obtemProfessores() {

		ProfessorDAO professorDAO = new ProfessorDAO();

		ArrayList<String> listaProfessores = new ArrayList<String>();
		
		for (Professor prof : professorDAO.getAll()) {
			professores.put(prof.getNome(), prof.getCpf());
			listaProfessores.add(prof.getNome());
		}
		
		modelProfessor = new ListModelList<String>(listaProfessores);

		listbox_professor.setModel(modelProfessor);
	}

	private void obtemTurmas() {

		TurmaDAO turmaDAO = new TurmaDAO();

		ArrayList<String> listaTurma = new ArrayList<String>();
		
		for (Turma turma : turmaDAO.getAll()) {
			turmas.put(turma.getNumero(), turma);
			listaTurma.add(turma.getDisciplina_cod() + "-" + turma.getNumero());
		}
		
		modelTurma = new ListModelList<String>(listaTurma);

		listbox_turma.setModel(modelTurma);
	}

	private void obtemProjetos() {

		ProjetoDAO projetoDAO = new ProjetoDAO();

		ArrayList<String> lista = new ArrayList<String>();
		
		for (Projeto projeto : projetoDAO.getAll()) {
			lista.add(Integer.toString(projeto.getCod()));
		}
		
		ListModelList<String> modelProjetos = new ListModelList<String>(lista);

		cbx_projeto.setModel(modelProjetos);
	}
	

	@Override
	public void doAfterCompose(Component comp) throws Exception {

		super.doAfterCompose(comp);
		
		turmas.clear();
		professores.clear();
		obtemProfessores();
		obtemTurmas();
		obtemProjetos();

	}

	@Listen("onSelect = #cbx_projeto")
	public void criaLista() {

		ProjetoDAO projetoDAO = new ProjetoDAO();
		Projeto projeto = projetoDAO.getOneByCod(Integer.parseInt(cbx_projeto
				.getSelectedItem().getLabel()));

		// remove filhos da listbox
		listar_projeto.getItems().clear();

		ArrayList<Projeto> projetos = new ArrayList<Projeto>();
		projetos.add(projeto);

		ListModelList<Projeto> disciplinaModel = new ListModelList<Projeto>(
				projetos);
		// cria model
		listar_projeto.setModel(disciplinaModel);

		// recria listbox
		try {
	    	   Listhead lh = new Listhead();
	           lh.appendChild(new Listheader("cod"));
	           lh.appendChild(new Listheader("descricao"));
	           lh.appendChild(new Listheader("turma"));
	           lh.appendChild(new Listheader("professor cpf"));
	           lh.appendChild(new Listheader("data aprovacao"));
	           lh.appendChild(new Listheader("ata aprovacao"));
	           listar_projeto.appendChild(lh);

		} catch (Exception e) {
		}

		listar_projeto.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem listitem, Object data, int arg2)
					throws Exception {

				final Projeto proj = (Projeto)data;
				
				new Listcell(Integer.toString(proj.getCod())).setParent(listitem);
				new Listcell(proj.getDescricao()).setParent(listitem);
				new Listcell(proj.getTurmaDisciplinaCod() + "-" + proj.getTurmaNumero()).setParent(listitem);
				new Listcell(proj.getProfessorCpf()).setParent(listitem);
				if(proj.getDataAprovacao() != null)
					new Listcell(proj.getDataAprovacao().toString()).setParent(listitem);
				new Listcell(proj.getAtaAprovacao()).setParent(listitem);

			}
		});

		updateValues(projeto);
	}

	private void updateValues(Projeto projeto) {
		codigo.setValue(Integer.toString(projeto.getCod()));
		descricao.setValue(projeto.getDescricao());
		listbox_turma.setSelectedIndex(modelTurma.indexOf(projeto.getTurmaDisciplinaCod() + "-" + projeto.getTurmaNumero()));
	}

	@Listen("onClick=#btn_atualizar")
	public void edita() {

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
		projeto.setDataAprovacao(data_aprovacao.getValue());
		
		if(!ata_aprovacao.getValue().equals(""))
			projeto.setAtaAprovacao(ata_aprovacao.getValue());

		if (projetoDAO.updateOne(projeto) > 0) {

			atualizar();
			Mensagem.sucesso();

		} else
			Mensagem.insucesso();

	}

}
