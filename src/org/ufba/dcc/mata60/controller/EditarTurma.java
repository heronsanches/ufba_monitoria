package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Disciplina;
import org.ufba.dcc.mata60.model.DisciplinaDAO;
import org.ufba.dcc.mata60.model.Turma;
import org.ufba.dcc.mata60.model.TurmaDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.ListModels;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;

public class EditarTurma extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Button btn_atualizar;
	
	@Wire
	private Combobox cbx_disciplina_pesquisa;
	
	@Wire
	private Listbox listar_turma;
	
	@Wire
	private Grid grid1;
	
	@Wire 
	private Grid grid2;
	
	@Wire
	private Textbox ano_pesquisa;
	
	@Wire
	private Textbox semestre_pesquisa;
	
	@Wire
	private Intbox numeroNovo;
	
	@Wire
	private Intbox anoNovo;
	
	@Wire
	private Intbox semestreNovo;
	
	@Wire
	private Combobox disciplinaNova;
	
	@Wire
	private Timebox horaInicioNova;
	
	@Wire
	private Timebox horaFimNova;
	
	@Wire
	private Datebox dataNova;
	
	
	 private ArrayList<String> obtemDisciplinas(){
	    	
	    	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	    	ArrayList<String> disciplinas = new ArrayList<String>();
	           	
	    	for(Disciplina dis: disciplinaDAO.getAll())
	    		disciplinas.add(dis.getNome()+"-"+dis.getCod());
	    	
	    	return disciplinas;
	        
	 }
	 
	 
	 @Listen("onSelect = #cbx_disciplina_pesquisa, #listar_turma")
	 public void criaLista(Event event){
		 
		 ArrayList<Turma> turmas = new ArrayList<Turma>();
		 TurmaDAO turmaDAO = new TurmaDAO();
		   
		 if(event.getTarget().getId().equals("listar_turma")){
			 
			 turmas.add(turmaDAO.getOne(listar_turma.getSelectedItem().getLabel().trim(),
						ano_pesquisa.getValue()+"."+semestre_pesquisa.getValue(), 
						cbx_disciplina_pesquisa.getSelectedItem().getLabel().split("-")[1].trim()));
		   			   			 
		 }else {
			 
			 turmas.addAll( turmaDAO.getBySemestreDisciplinaCod(
					   ano_pesquisa.getValue()+"."+semestre_pesquisa.getValue(), 
					   cbx_disciplina_pesquisa.getSelectedItem().getLabel().split("-")[1].trim()) );
			 			 			 
		 }
	
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
				public void render(Listitem listitem, Object data, int arg2) throws Exception {
					
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
	 
	 
	 	@Listen("onSelect = #listar_turma")
	 	public void habilitaGrides(){
	 		
	 		grid1.setVisible(true);
	    	disciplinaNova.setModel(ListModels.toListSubModel(new ListModelList<>(obtemDisciplinas())));
	 		grid2.setVisible(true);
		 	bloqueaCamposPesquisa();

	 			 		
	 	}
 	
	 	
	 	private void bloqueaCamposPesquisa(){
	 		
		 	ano_pesquisa.setDisabled(true);
		 	cbx_disciplina_pesquisa.setDisabled(true);
		 	semestre_pesquisa.setDisabled(true);
	 		listar_turma.getItemAtIndex(0).setDisabled(true);
	 		
	 	}
	    
	    
		private void atualizar(){
			
			Include include = (Include)Selectors.iterable(cbx_disciplina_pesquisa.getPage(), "#mainInclude").iterator().next();
		    include.setSrc(null);
		    include.setSrc("/template_turma.zul");
			
		}
		

	    @Override
	    public void doAfterCompose(Component comp) throws Exception{
	          
	    	super.doAfterCompose(comp);
	    	cbx_disciplina_pesquisa.setModel(ListModels.toListSubModel(new ListModelList<>(obtemDisciplinas())));

	    }
	    
	    
	    @Listen("onClick = #btn_atualizar")
	    public void atualizarTurma(){
	    	
	    	TurmaDAO turmaDAO = new TurmaDAO();
	    	Turma turmaNova = new Turma();
	    	System.out.println(dataNova.getValue());
	    	turmaNova.setData_inicio(dataNova.getValue());
	    	turmaNova.setDisciplina_cod(disciplinaNova.getSelectedItem().getLabel().split("-")[1].trim());
	    	turmaNova.setHora_fim(horaFimNova.getValue());
	    	turmaNova.setHora_inicio(horaInicioNova.getValue());
	    	turmaNova.setNumero(numeroNovo.getValue().toString());
	    	turmaNova.setSemestre(anoNovo.getValue().toString() +"."+ semestreNovo.getValue().toString());   	
	    	
	    	if( turmaDAO.updateOne(turmaNova, listar_turma.getItemAtIndex(0).getLabel(),
	    			ano_pesquisa.getValue()+"."+semestre_pesquisa.getValue(), 
	    			cbx_disciplina_pesquisa.getSelectedItem().getLabel().split("-")[1].trim()) > 0 ){
	    		
	    		Mensagem.sucessoUpdate();
	    		atualizar();
	    		
	    	}else
	    		Mensagem.insucessoUpdate();
	    	
	    }

}
