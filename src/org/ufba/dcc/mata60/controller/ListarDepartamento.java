package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Departamento;
import org.ufba.dcc.mata60.model.DepartamentoDAO;
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


public class ListarDepartamento extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Listbox listar_departamento;
	
	@Wire
	private Button btn_atualiza;
	
	
   @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        criaLista();
        
    }
   
   
   @Listen("onClick=#btn_atualiza")
   public void criaLista(){
	   
	   DepartamentoDAO departamentoDAO = new DepartamentoDAO();
       ArrayList<Departamento> departamentos = departamentoDAO.getAll();
       
       if(!departamentos.isEmpty()){
    	 //remove filhos da listbox
           listar_departamento.getItems().clear();
           
           ListModelList<Departamento> departamentoModel = new ListModelList<Departamento>(departamentos);
           //cria model
           listar_departamento.setModel(departamentoModel);
           
           //recria listbox
           try {
    		
        	   Listhead lh = new Listhead();
               lh.appendChild(new Listheader("cod"));
               lh.appendChild(new Listheader("nome"));
               listar_departamento.appendChild(lh);
               
    		} catch (Exception e) {
    			// TODO: handle exception
    		}
    	       
           listar_departamento.setItemRenderer(new ListitemRenderer() {

    			@Override
    			public void render(Listitem listitem, Object data, int arg2)
    					throws Exception {
    				
    				final Departamento dep = (Departamento)data;
    				
    				new Listcell(String.valueOf(dep.getCod())).setParent(listitem);
    				new Listcell(dep.getNome()).setParent(listitem);
    				
    			}
    		});
       }
	   
   }

    
}
