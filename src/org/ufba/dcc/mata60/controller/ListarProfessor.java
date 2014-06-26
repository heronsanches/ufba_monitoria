package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Professor;
import org.ufba.dcc.mata60.model.ProfessorDAO;
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

public class ListarProfessor extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Listbox lista_professor;
	
	@Wire
	private Button btn_atualiza;
	
	
   @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        criaLista();
        
    }
   
   
   @Listen("onClick=#btn_atualiza")
   public void criaLista(){
	   
	   ProfessorDAO professorDAO = new ProfessorDAO();
       ArrayList<Professor> professores = professorDAO.getAll();
       
       //remove filhos da listbox
       lista_professor.getItems().clear();
       
       ListModelList<Professor> professsorModel = new ListModelList<Professor>(professores);
       //cria model
       lista_professor.setModel(professsorModel);
       
       //recria listbox
       try {
		
    	   Listhead lh = new Listhead();
           lh.appendChild(new Listheader("cpf"));
           lh.appendChild(new Listheader("matricula"));
           lh.appendChild(new Listheader("nome"));
           lh.appendChild(new Listheader("departamento_cod"));
           lh.appendChild(new Listheader("tipo"));
           lista_professor.appendChild(lh);
           
		} catch (Exception e) {
			// TODO: handle exception
		}
	       
       lista_professor.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem listitem, Object data, int arg2)
					throws Exception {
				
				final Professor prof = (Professor)data;
				
				new Listcell(prof.getCpf()).setParent(listitem);
				new Listcell(prof.getMatricula()).setParent(listitem);
				new Listcell(prof.getNome()).setParent(listitem);
				new Listcell(Integer.toString(prof.getDepartamento_cod())).setParent(listitem);
				new Listcell(prof.getTipo()).setParent(listitem);
			}
		});
	   
   }

    
}