package org.ufba.dcc.mata60.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;
import org.zkoss.zul.Listbox;


public class SideBar extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire
	private Listbox listbox_sidebar;

	
	@Listen("onClick = #listbox_sidebar > listitem > listcell")
	public void change_menu_cadastro(Event e){

	    String menuItemId = e.getTarget().getId();
	    String locationUri = "";

	    switch (menuItemId) {
	    
	    	case "listcell_departamento":  locationUri = "/template_departamento.zul";
	    		break;
	    	
	    	case "listcell_professor":  locationUri = "/template_professor.zul";
    			break;
	    		
	    	case "listcell_aluno": locationUri = "/template_aluno.zul";
	    		break;
	    	case "listcell_disciplina":  locationUri = "/template_disciplina.zul";
    			break;
    		
	    	case "listcell_turma":  locationUri = "/template_turma.zul";
				break;
    			
	    	case "listcell_projeto":  locationUri = "/template_projeto.zul";
				break;
				
	    	case "listcell_edital":  locationUri = "/template_edital.zul";
				break;
				
	    	case "listcell_sair":  locationUri = ""; //TODO
				break;
			
	    }

	    Include include = (Include)Selectors.iterable(listbox_sidebar.getPage(), "#mainInclude").iterator().next();
	    include.setSrc(locationUri);
	}
	
	
}
