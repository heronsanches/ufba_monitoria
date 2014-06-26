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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

public class EditarProfessor extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	private Combobox cbx_professor;

	@Wire
	private Button btn_atualizar;

	@Wire
	Textbox novoNome;

	@Wire
	Textbox novaMatricula;

	@Wire
	Textbox novoCpf;

	@Wire
	private Listbox listbox_departamentoNomes;

	@Wire
	private Listbox listar_professor;

	@Wire
	private Radiogroup rd_group_tipoProfessores;

	private void atualizar() {

		Include include = (Include) Selectors
				.iterable(cbx_professor.getPage(), "#mainInclude").iterator()
				.next();
		include.setSrc(null);
		include.setSrc("/template_professor.zul");

	}

	private ArrayList<String> obtemDepartamentos() {

		DepartamentoDAO departamentoDAO = new DepartamentoDAO();
		ArrayList<String> departamentos = new ArrayList<String>();

		for (Departamento dep : departamentoDAO.getAll())
			departamentos.add(dep.getNome() + "-" + dep.getCod());

		return departamentos;

	}

	private ArrayList<String> obtemProfessores() {

		ProfessorDAO professorDAO = new ProfessorDAO();
		ArrayList<String> disciplinas = new ArrayList<String>();

		for (Professor prof : professorDAO.getAll())
			disciplinas.add(prof.getMatricula() + "-" + prof.getNome());

		return disciplinas;

	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {

		super.doAfterCompose(comp);

		cbx_professor.setModel(ListModels.toListSubModel(new ListModelList<>(
				obtemProfessores())));
		listbox_departamentoNomes.setModel(ListModels
				.toListSubModel(new ListModelList<>(obtemDepartamentos())));

	}

	@Listen("onSelect = #cbx_professor")
	public void criaLista() {

		ProfessorDAO professorDAO = new ProfessorDAO();
		Professor professor = professorDAO.getOneByMatricula(cbx_professor
				.getSelectedItem().getLabel().split("-")[0].trim());

		// remove filhos da listbox
		listar_professor.getItems().clear();

		ArrayList<Professor> professores = new ArrayList<Professor>();
		professores.add(professor);

		ListModelList<Professor> disciplinaModel = new ListModelList<Professor>(
				professores);
		// cria model
		listar_professor.setModel(disciplinaModel);

		// recria listbox
		try {

			Listhead lh = new Listhead();
			lh.appendChild(new Listheader("cpf"));
			lh.appendChild(new Listheader("matricula"));
			lh.appendChild(new Listheader("nome"));
			lh.appendChild(new Listheader("departamento_cod"));
			lh.appendChild(new Listheader("tipo"));
			listar_professor.appendChild(lh);

		} catch (Exception e) {
			// TODO: handle exception
		}

		listar_professor.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem listitem, Object data, int arg2)
					throws Exception {

				final Professor prof = (Professor) data;

				new Listcell(prof.getCpf()).setParent(listitem);
				new Listcell(prof.getMatricula()).setParent(listitem);
				new Listcell(prof.getNome()).setParent(listitem);
				new Listcell(Integer.toString(prof.getDepartamento_cod()))
						.setParent(listitem);
				new Listcell(prof.getTipo()).setParent(listitem);

			}
		});

	}

	@Listen("onClick=#btn_atualizar")
	public void edita() {

		ProfessorDAO professorDAO = new ProfessorDAO();

		Professor professor = new Professor();
		professor.setNome(novoNome.getValue());
		professor.setMatricula(cbx_professor.getSelectedItem().getLabel()
				.split("-")[0].trim());
		professor.setDepartamento_cod(Integer.valueOf(listbox_departamentoNomes
				.getSelectedItem().getLabel().split("-")[1].trim()));
		professor.setCpf(novoCpf.getValue());
		professor
				.setTipo(rd_group_tipoProfessores.getSelectedItem().getLabel());

		if (professorDAO.updateOne(professor) > 0) {
			Mensagem.sucessoUpdate();
			atualizar();

		} else {
			Mensagem.insucessoUpdate();
		}

	}

}
