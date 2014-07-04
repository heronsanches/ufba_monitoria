package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ufba.dcc.mata60.model.Projeto;
import org.ufba.dcc.mata60.model.ProjetoDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
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
import org.zkoss.zul.Window;

public class ListarProjeto extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Listbox listar_projeto;
	
	@Wire
	private Button btn_atualiza;
	
	
   @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        criaLista();
        
    }
   
   
   @Listen("onClick=#btn_atualiza")
   public void criaLista(){
	   
	   ProjetoDAO projetoDAO = new ProjetoDAO();
       ArrayList<Projeto> projetos = projetoDAO.getAll();
       
       //remove filhos da listbox
       listar_projeto.getItems().clear();
       
       ListModelList<Projeto> projetoModel = new ListModelList<Projeto>(projetos);
       //cria model
       listar_projeto.setModel(projetoModel);
       
       //recria listbox
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
				
				final Projeto proj = (Projeto)data;
				
				new Listcell(Integer.toString(proj.getCod())).setParent(listitem);
				new Listcell(proj.getDescricao()).setParent(listitem);
				new Listcell(proj.getTurmaDisciplinaCod() + "-" + proj.getTurmaNumero()).setParent(listitem);
				new Listcell(proj.getProfessorCpf()).setParent(listitem);
				if(proj.getDataAprovacao() != null)
					new Listcell(proj.getDataAprovacao().toString()).setParent(listitem);
				new Listcell(proj.getAtaAprovacao()).setParent(listitem);
			}
		});
	   
   }
   
   @Listen("onSelect=#listar_projeto")
   public void mostrarAta(){
	   
	   Map<String, String> args = new HashMap<String, String>();
	   ProjetoDAO projetoDAO = new ProjetoDAO();
	   
       args.put("nomePDF", projetoDAO.getOneByCod(Integer.valueOf(listar_projeto.getSelectedItem()
    		   .getLabel().trim())).getAtaAprovacao());
	  
	   //cria uma janela e a usa como um modal e envia parâmetros para o
       //controller do outro arquivo.zul
        Window window = (Window)Executions.createComponents(
                "/modal_ata.zul", null, args);
        
        window.doModal();
   }

    
}