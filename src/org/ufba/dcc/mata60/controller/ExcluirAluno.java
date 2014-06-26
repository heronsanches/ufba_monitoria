package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Aluno;
import org.ufba.dcc.mata60.model.AlunoDAO;
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

public class ExcluirAluno extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	private Combobox cbx_aluno;

	@Wire
	private Listbox listar_aluno;
	
	@Wire
	private Button btn_excluir;

	private void atualizar() {

		Include include = (Include) Selectors
				.iterable(cbx_aluno.getPage(), "#mainInclude").iterator()
				.next();
		include.setSrc(null);
		include.setSrc("/template_aluno.zul");

	}

	private ArrayList<String> obtemAlunos() {

		AlunoDAO alunoDAO = new AlunoDAO();
		ArrayList<String> alunos = new ArrayList<String>();

		for (Aluno aluno : alunoDAO.getAll())
			alunos.add(aluno.getCpf() + "-" + aluno.getNome());

		return alunos;

	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {

		super.doAfterCompose(comp);

		cbx_aluno.setModel(ListModels.toListSubModel(new ListModelList<>(
				obtemAlunos())));

	}

	@Listen("onSelect = #cbx_aluno")
	public void criaLista() {

		AlunoDAO alunoDAO = new AlunoDAO();
		Aluno aluno = alunoDAO.getOneByCPF(cbx_aluno
				.getSelectedItem().getLabel().split("-")[0].trim());

		// remove filhos da listbox
		listar_aluno.getItems().clear();

		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		alunos.add(aluno);

		ListModelList<Aluno> disciplinaModel = new ListModelList<Aluno>(
				alunos);
		// cria model
		listar_aluno.setModel(disciplinaModel);

		// recria listbox
		try {

			Listhead lh = new Listhead();
			lh.appendChild(new Listheader("cpf"));
			lh.appendChild(new Listheader("matricula"));
			lh.appendChild(new Listheader("nome"));
			listar_aluno.appendChild(lh);

		} catch (Exception e) {
			// TODO: handle exception
		}

		listar_aluno.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem listitem, Object data, int arg2)
					throws Exception {

				final Aluno aluno = (Aluno) data;

				new Listcell(aluno.getCpf()).setParent(listitem);
				new Listcell(aluno.getMatricula()).setParent(listitem);
				new Listcell(aluno.getNome()).setParent(listitem);

			}
		});

	}

	@Listen("onClick=#btn_excluir")
	public void exclui() {

		AlunoDAO alunoDAO = new AlunoDAO();

		Aluno aluno = new Aluno();
		aluno.setCpf(cbx_aluno.getSelectedItem().getLabel().split("-")[0]
				.trim());

		if (alunoDAO.deleteOne(aluno) > 0) {

			Mensagem.sucessoDelete();
			atualizar();

		} else
			Mensagem.insucessoDelete();
	}
}
