package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Turma;
import org.ufba.dcc.mata60.model.TurmaDAO;
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

public class ListarTurma extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	
	@Wire
	private Listbox listar_turma;
	
	@Wire
	private Button btn_atualiza;
	
	
   @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        criaLista();
        
    }
   
   
   @Listen("onClick=#btn_atualiza")
   public void criaLista(){
	   
	   TurmaDAO turmaDAO = new TurmaDAO();
	   ArrayList<Turma> turmas = turmaDAO.getAll();
       
       //remove filhos da listbox
       listar_turma.getItems().clear();
       
       ListModelList<Turma> turmaModel = new ListModelList<Turma>(turmas);
       //cria model
       listar_turma.setModel(turmaModel);
       
       //recria listbox
       try {
    	   
    	   Listhead lh = new Listhead();
           lh.appendChild(new Listheader("número"));
           lh.appendChild(new Listheader("hora_inicio"));
           lh.appendChild(new Listheader("hora_fim"));
           lh.appendChild(new Listheader("data_inicio"));
           lh.appendChild(new Listheader("semestre"));
           lh.appendChild(new Listheader("disciplina_cod"));
           
           listar_turma.appendChild(lh);
           
		} catch (Exception e) {
			// TODO: handle exception
		}
	       
       listar_turma.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem listitem, Object data, int arg2)
					throws Exception {
				
				final Turma turma = (Turma)data;
				
				new Listcell(turma.getNumero()).setParent(listitem);
				new Listcell(turma.getHora_inicio().toString()).setParent(listitem);
				new Listcell(turma.getHora_fim().toString()).setParent(listitem);
				new Listcell(turma.getData_inicio().toString()).setParent(listitem);
				new Listcell(turma.getSemestre()).setParent(listitem);
				new Listcell(turma.getDisciplina_cod()).setParent(listitem);
			
			}
		});
	   
   }

	

}
