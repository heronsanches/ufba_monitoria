package org.ufba.dcc.mata60.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;

import org.zkoss.zul.Include;
import org.zkoss.zul.Menubar;


public class SideBar extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire
	Menubar menubar;
	
	@Listen("onClick = #menubar > menu > menupopup > menuitem")
	public void change_page(Event e){

	    String menuItemId = e.getTarget().getId();
	    String locationUri = "";

	    switch (menuItemId) {
	    
	    	case "mc_departamento":  locationUri = "/cadastrar_departamento.zul";
	    		break;
	    		
	    	case "mc_professor":  locationUri = "/cadastrar_professor.zul";
    			break;
    			
	    	case "mc_disciplina":  locationUri = "/cadastrar_disciplina.zul";
				break;
				
	    	case "mc_turma":  locationUri = "/cadastrar_turma.zul";
				break;
				
			//TODO outros "case" para sidebar.zul
	    
	    }

	    Include include = (Include)Selectors.iterable(menubar.getPage(), "#mainInclude").iterator().next();
	    include.setSrc(locationUri);
	}

	
}
