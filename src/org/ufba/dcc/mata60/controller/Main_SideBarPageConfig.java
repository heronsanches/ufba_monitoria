package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.ufba.dcc.mata60.services.SidebarPage;
import org.ufba.dcc.mata60.services.SidebarPageConfig;


public class Main_SideBarPageConfig implements SidebarPageConfig{
	
	HashMap<String,SidebarPage> pageMap = new LinkedHashMap<String,SidebarPage>();
	
	public Main_SideBarPageConfig(){		
		pageMap.put("fn1",new SidebarPage("ufba", "UFBA", "/imagens/icons/add.png", "http://localhost:8080/ProjetoMonitoria/teste.zul"));
		pageMap.put("fn2",new SidebarPage("ufba_dcc", "Listagem", "/imagens/icons/users.png", "http://www.dcc.ufba.br"));
		pageMap.put("fn3",new SidebarPage("ufba_disciplinas", "UFBA DCC Disciplinas", "/imagens/icons/rss.png", "http://disciplinas.dcc.ufba.br"));
	}
	
	@Override
	public List<SidebarPage> getPages(){
		return new ArrayList<SidebarPage>(pageMap.values());
	}
	
	@Override
	public SidebarPage getPage(String name){
		return pageMap.get(name);
	}
	
}
