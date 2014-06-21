package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Disciplina;
import org.ufba.dcc.mata60.model.DisciplinaDAO;
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

public class ListarDisciplina extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Listbox listar_disciplina;
	
	@Wire
	private Button btn_atualiza;
	
	
   @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        criaLista();
        
    }
   
   
   @Listen("onClick=#btn_atualiza")
   public void criaLista(){
	   
	   DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
       ArrayList<Disciplina> disciplinas = disciplinaDAO.getAll();
       
       //remove filhos da listbox
       listar_disciplina.getItems().clear();
       
       ListModelList<Disciplina> disciplinaModel = new ListModelList<Disciplina>(disciplinas);
       //cria model
       listar_disciplina.setModel(disciplinaModel);
       
       //recria listbox
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
				
				final Disciplina disc = (Disciplina)data;
				
				new Listcell(disc.getCod()).setParent(listitem);
				new Listcell(disc.getNome()).setParent(listitem);
				new Listcell(String.valueOf(disc.getDepartamento_cod())).setParent(listitem);
				
			}
		});
	   
   }

    
}
