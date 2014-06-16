package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

public class CadastrarTurma extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	 @Wire
	 Listbox listbox_disciplinaNomes;
	    
	    
	    //TODO pegar essa lista do banco de dados
	    private ArrayList<String> getDisciplinas(){
	    	
	        ArrayList<String> disciplinas = new ArrayList<String>();
	        disciplinas.add("Banco de dados");
	        disciplinas.add("Paradigmas");
	        disciplinas.add("Rede de computadores");
	        disciplinas.add("Engenharia de software II");
	        disciplinas.add("Metodologia");
	        
	        return disciplinas;
	        
	    }

	    @Override
	    public void doAfterCompose(Component comp) throws Exception{
	          
	    	super.doAfterCompose(comp);
	         
	          ListModelList<String> disciplinas = new ListModelList<String>(getDisciplinas());
	         
	          listbox_disciplinaNomes.setModel(disciplinas);
	    
	    }

}
