package org.ufba.dcc.mata60.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;


public class SideBar extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire
	Menu menu_cadastro;
	
	@Wire
	Menu menu_projeto;
	
	@Wire
	Menu menu_edital;
	
	@Wire
	Menuitem menuitem_sair;
	
	@Listen("onClick = #menu_cadastro > menupopup > menu > menupopup > menuitem")
	public void change_menu_cadastro(Event e){

	    String menuItemId = e.getTarget().getId();
	    String locationUri = "";

	    switch (menuItemId) {
	    
	    	case "mi_departamento_novo":  locationUri = "/cadastrar_departamento.zul";
	    		break;
	    	
	    	case "mi_departamento_listar":  locationUri = "/listar_departamento.zul";
    			break;
	    		
	    	case "mi_professor_novo":  locationUri = "/cadastrar_professor.zul";
    			break;
    		
	    	case "mi_professor_listar":  locationUri = "/listar_professor.zul";
				break;
    			
	    	case "mi_disciplina_novo":  locationUri = "/cadastrar_disciplina.zul";
				break;
				
	    	case "mi_disciplina_listar":  locationUri = "/listar_disciplina.zul";
				break;
				
	    	case "mi_turma_novo":  locationUri = "/cadastrar_turma.zul";
				break;
			
	    	case "mi_turma_listar":  locationUri = "/listar_turma.zul";
				break;
			
	    	case "mi_turma_cadastrar_professor":  locationUri = "/cadastrar_professor_turma.zul";
				break;
	  
	    }

	    Include include = (Include)Selectors.iterable(menu_cadastro.getPage(), "#mainInclude").iterator().next();
	    include.setSrc(locationUri);
	}
	

	@Listen("onClick = #menu_projeto > menupopup > menuitem")
	public void change_menu_projeto(Event e){

	    String menuItemId = e.getTarget().getId();
	    String locationUri = "";

	    switch (menuItemId) {
	    
	    	case "mi_projeto_novo":  locationUri = "/cadastrar_projeto.zul";
	    		break;
	    		
	    	case "mi_projeto_listar":  locationUri = "/listar_projeto.zul";
    			break;
	    
	    }

	    Include include = (Include)Selectors.iterable(menu_projeto.getPage(), "#mainInclude").iterator().next();
	    include.setSrc(locationUri);
	}
	

	@Listen("onClick = #menu_edital > menupopup > menuitem")
	public void change_menu_edital(Event e){

	    String menuItemId = e.getTarget().getId();
	    String locationUri = "";

	    switch (menuItemId) {
	    
	    	case "mi_edital_novo":  locationUri = "/cadastrar_edital.zul";
	    		break;
	    		
	    	case "mi_edital_listar":  locationUri = "/listar_edital.zul";
    			break;
	    
	    }

	    Include include = (Include)Selectors.iterable(menu_edital.getPage(), "#mainInclude").iterator().next();
	    include.setSrc(locationUri);
	}
	
	//TODO fazer @Listen do menuitem_sair

	
}
