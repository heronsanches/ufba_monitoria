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
		pageMap.put("fn1",new SidebarPage("ufba", "CADASTRO", "/imagens/icons/add.png", "http://localhost:8080/ProjetoMonitoria/index_register.zul"));
		pageMap.put("fn2",new SidebarPage("ufba_dcc", "LISTAGEM", "/imagens/icons/users.png", "http://localhost:8080/ProjetoMonitoria/editais.zul"));
		pageMap.put("fn3",new SidebarPage("ufba_disciplinas", "Detalhe Edital", "/imagens/icons/rss.png", "http://localhost:8080/ProjetoMonitoria/edital_detalhe.zul"));
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
