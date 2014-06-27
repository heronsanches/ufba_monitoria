package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Disciplina;
import org.ufba.dcc.mata60.model.DisciplinaDAO;
import org.ufba.dcc.mata60.model.Projeto;
import org.ufba.dcc.mata60.model.ProjetoDAO;
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

public class ExcluirProjeto extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	private Button btn_excluir;

	@Wire
	private Listbox listar_projeto;

	@Wire
	private Combobox cbx_projeto;

	private void atualizar() {

		Include include = (Include) Selectors
				.iterable(cbx_projeto.getPage(), "#mainInclude").iterator()
				.next();
		include.setSrc(null);
		include.setSrc("/template_projeto.zul");

	}

	private ArrayList<String> obtemProjetos() {

		ProjetoDAO projetoDAO = new ProjetoDAO();
		ArrayList<String> projetos = new ArrayList<String>();

		for (Projeto projeto : projetoDAO.getAll())
			projetos.add(Integer.toString(projeto.getCod()));

		return projetos;

	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {

		super.doAfterCompose(comp);

		cbx_projeto.setModel(ListModels.toListSubModel(new ListModelList<>(
				obtemProjetos())));

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
			// TODO: handle exception
		}

		listar_projeto.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem listitem, Object data, int arg2)
					throws Exception {

				final Projeto proj = (Projeto) data;

				new Listcell(Integer.toString(proj.getCod()))
						.setParent(listitem);
				new Listcell(proj.getDescricao()).setParent(listitem);
				new Listcell(proj.getTurmaDisciplinaCod() + "-"
						+ proj.getTurmaNumero()).setParent(listitem);
				new Listcell(proj.getProfessorCpf()).setParent(listitem);
				if (proj.getDataAprovacao() != null)
					new Listcell(proj.getDataAprovacao().toString())
							.setParent(listitem);
				new Listcell(proj.getAtaAprovacao()).setParent(listitem);

			}
		});

	}

	@Listen("onClick=#btn_excluir")
	public void exclir() {

		ProjetoDAO projetoDAO = new ProjetoDAO();

		if (projetoDAO.deleteOne(Integer.parseInt(cbx_projeto.getSelectedItem().getLabel())) > 0) {

			Mensagem.sucessoDelete();
			atualizar();

		} else {
			Mensagem.insucessoDelete();
		}
	}

}
