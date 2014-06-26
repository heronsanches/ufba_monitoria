package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Aluno;
import org.ufba.dcc.mata60.model.AlunoDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class ListarAluno extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	private Listbox listar_aluno;

	@Wire
	private Button btn_atualiza;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		criaLista();

	}

	@Listen("onClick=#btn_atualiza")
	public void criaLista() {

		AlunoDAO alunoDAO = new AlunoDAO();
		ArrayList<Aluno> alunos = alunoDAO.getAll();

		// remove filhos da listbox
		listar_aluno.getItems().clear();

		ListModelList<Aluno> disciplinaModel = new ListModelList<Aluno>(alunos);
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

}
