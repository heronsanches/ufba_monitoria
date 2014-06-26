package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Disciplina;
import org.ufba.dcc.mata60.model.DisciplinaDAO;
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

public class ExcluirDisciplina extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Button btn_excluir;
	
	@Wire
	private Listbox listar_disciplina;
	
	@Wire
	private Combobox cbx_disciplina;
	
	
	private void atualizar(){
		
		Include include = (Include)Selectors.iterable(cbx_disciplina.getPage(), "#mainInclude").iterator().next();
	    include.setSrc(null);
	    include.setSrc("/template_disciplina.zul");
		
	}
	
	
	private ArrayList<String> obtemDisciplinas(){
		
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		ArrayList<String> disciplinas = new ArrayList<String>();
				
		for(Disciplina disc: disciplinaDAO.getAll())
			disciplinas.add(disc.getNome()+"-"+disc.getCod());
		
		return disciplinas;
		
	}
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {

		super.doAfterCompose(comp);
   	
		cbx_disciplina.setModel(ListModels.toListSubModel(new ListModelList<>(obtemDisciplinas())));

	}
	
	
	@Listen("onSelect = #cbx_disciplina")
	public void criaLista() {

		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		Disciplina disciplina = disciplinaDAO.getOne(cbx_disciplina
				.getSelectedItem().getLabel().split("-")[1].trim());

		// remove filhos da listbox
		listar_disciplina.getItems().clear();

		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
		disciplinas.add(disciplina);

		ListModelList<Disciplina> disciplinaModel = new ListModelList<Disciplina>(
				disciplinas);
		// cria model
		listar_disciplina.setModel(disciplinaModel);

		// recria listbox
		try {

			Listhead lh = new Listhead();
			lh.appendChild(new Listheader("cod"));
			lh.appendChild(new Listheader("nome"));
			lh.appendChild(new Listheader("departamento_cod"));
			listar_disciplina.appendChild(lh);

		} catch (Exception e) {
			// TODO: handle exception
		}

		listar_disciplina.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem listitem, Object data, int arg2)
					throws Exception {

				final Disciplina disc = (Disciplina) data;

				new Listcell(disc.getCod()).setParent(listitem);
				new Listcell(disc.getNome()).setParent(listitem);
				new Listcell(String.valueOf(disc.getDepartamento_cod()))
						.setParent(listitem);

			}
		});

	}

	
	
	@Listen("onClick=#btn_excluir")
	public void edita(){
		
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
				
		if(disciplinaDAO.deleteOne(cbx_disciplina.getSelectedItem().getLabel().split("-")[1].trim()) > 0){
			
			Mensagem.sucessoDelete();
			atualizar();
			
		}else
			Mensagem.insucessoDelete();
		
	}

}

