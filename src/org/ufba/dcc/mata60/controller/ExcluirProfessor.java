package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

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
import org.zkoss.zul.Textbox;

public class ExcluirProfessor extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	private Button btn_excluir;

	@Wire
	private Listbox listar_professor;

	@Wire
	private Combobox cbx_professor;

	private void atualizar() {

		Include include = (Include) Selectors
				.iterable(cbx_professor.getPage(), "#mainInclude").iterator()
				.next();
		include.setSrc(null);
		include.setSrc("/template_professor.zul");

	}

	private ArrayList<String> obtemProfessores() {

		ProfessorDAO professorDAO = new ProfessorDAO();
		ArrayList<String> professores = new ArrayList<String>();

		for (Professor prof : professorDAO.getAll())
			professores.add(prof.getMatricula() + "-" + prof.getNome());

		return professores;

	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {

		super.doAfterCompose(comp);

		cbx_professor.setModel(ListModels.toListSubModel(new ListModelList<>(
				obtemProfessores())));

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

		ListModelList<Professor> professorModel = new ListModelList<Professor>(
				professores);
		// cria model
		listar_professor.setModel(professorModel);

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

	@Listen("onClick=#btn_excluir")
	public void exclui() {

		ProfessorDAO professorDAO = new ProfessorDAO();

		if (professorDAO.deleteOne(cbx_professor.getSelectedItem().getLabel()
				.split("-")[0].trim()) > 0) {

			Mensagem.sucessoDelete();
			atualizar();

		} else
			Mensagem.insucessoDelete();

	}

}
