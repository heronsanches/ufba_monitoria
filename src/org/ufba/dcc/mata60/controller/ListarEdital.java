package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Edital;
import org.ufba.dcc.mata60.model.EditalDAO;
import org.ufba.dcc.mata60.model.Projeto;
import org.ufba.dcc.mata60.model.ProjetoDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
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

public class ListarEdital extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	
	@Wire
	private Listbox listar_edital;
	
	@Wire
	private Button btn_atualiza;
	
	@Wire
	private Button btn_closed;
	
	@Wire
	private Button btn_opened;
	
	
   @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        criaLista(new Event("onClick", btn_atualiza));
        
    }
   
   
   @Listen("onClick=#btn_atualiza, #btn_aberto, #btn_fechado")
   public void criaLista(Event event){
	   
	   String itemId = event.getTarget().getId();
	   ArrayList<Edital> editais = new ArrayList<>();

	   if(itemId.equals("btn_atualiza")){
		   
		   editais.addAll(new EditalDAO().getAll());

	   }else if(itemId.equals("btn_aberto")){
		   
		   editais.addAll(new EditalDAO().getOpened());
	   
	   }else{
		   
		   editais.addAll(new EditalDAO().getClosed());
		   
	   }
	   
       //remove filhos da listbox
       listar_edital.getItems().clear();
       
       ListModelList<Edital> editalModel = new ListModelList<Edital>(editais);
       //cria model
       listar_edital.setModel(editalModel);
       
       //recria listbox
       try {
    	   
    	   Listhead lh = new Listhead();
           lh.appendChild(new Listheader("código edital"));
           lh.appendChild(new Listheader("projeto"));
           lh.appendChild(new Listheader("número de vagas"));
           lh.appendChild(new Listheader("data inicio"));
           lh.appendChild(new Listheader("data final"));
           
           listar_edital.appendChild(lh);
           
		} catch (Exception e) {
			// TODO: handle exception
		}
	       
       listar_edital.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem listitem, Object data, int arg2)
					throws Exception {
				
				final Edital edital = (Edital)data;
				final Projeto projeto = new ProjetoDAO().getOneByCod(edital.getProjeto_cod());
				
				new Listcell(String.valueOf(edital.getCod())).setParent(listitem);
				new Listcell(projeto.getCod()+"-"+projeto.getDescricao()).setParent(listitem);
				new Listcell(String.valueOf(edital.getN_vagas())).setParent(listitem);
				new Listcell(edital.getData_inicio().toString()).setParent(listitem);
				new Listcell(edital.getData_fim().toString()).setParent(listitem);
			
			}
		});
	   
   }
	

}

