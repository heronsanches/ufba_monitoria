package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Edital;
import org.ufba.dcc.mata60.model.EditalDAO;
import org.ufba.dcc.mata60.model.Projeto;
import org.ufba.dcc.mata60.model.ProjetoDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.ListModels;
import org.zkoss.zul.Textbox;

public class EditarEdital extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	private Datebox data_inicio;
	
	@Wire
	private Datebox data_fim;
	
	@Wire
	private Intbox n_vagas;
	
	@Wire
	private Combobox cbx_edital;
	
	@Wire
	private Combobox cbx_projeto;
	
	@Wire
	private Textbox documentos_necessarios;
	
	@Wire
	private Textbox informacoes_adicionais;
	
	@Wire
	private Grid grid_editar;

	@Wire
	private Grid grid_cod_projeto;
	
	private void atualizar() {
		Include include = (Include) Selectors
				.iterable(n_vagas.getPage(), "#mainInclude").iterator().next();
		include.setSrc(null);
		include.setSrc("/template_edital.zul");
	}

	private ArrayList<String> obtemProjetos(){
		
		ArrayList<String> projetos = new ArrayList<String>();
				
		for(Projeto projeto: new ProjetoDAO().getAll())
			projetos.add(projeto.getCod()+"-"+projeto.getDescricao());
		
		return projetos;
		
	}
	
	private ArrayList<String> obtemEditais(){
		
		ArrayList<String> editais = new ArrayList<String>();
				
		for(Edital edital: new EditalDAO().getAll())
			editais.add(edital.getCod()+"-"+edital.getProjeto_cod());
		
		return editais;
		
	}
	
    @Override
    public void doAfterCompose(Component comp) throws Exception{
          
    	super.doAfterCompose(comp);
    	
		cbx_projeto.setModel(ListModels.toListSubModel(new ListModelList<>(obtemProjetos())));
		cbx_edital.setModel(ListModels.toListSubModel(new ListModelList<>(obtemEditais())));
    
    }
    
    @Listen("onSelect = #cbx_edital")
    public void mostraGridEditar(){
    	
    	Edital edital = new EditalDAO().getOneByCod((int)Integer.parseInt(cbx_edital.getSelectedItem().getLabel()
        		.split("-")[0].trim()));
    	
    	data_inicio.setValue(edital.getData_inicio());
    	data_fim.setValue(edital.getData_fim());
    	documentos_necessarios.setValue(edital.getDocumentos_necessarios());
    	informacoes_adicionais.setValue(edital.getInformacoes_adicionais());
    	n_vagas.setValue(edital.getN_vagas());
  
    	grid_editar.setVisible(true);
    	grid_cod_projeto.setVisible(true);
    
    }
    
    

	@Listen("onClick = #btn_atualizar")
	public void atualizarEdital() {

		EditalDAO editalDAO = new EditalDAO();
		Edital edital = new Edital();

		edital.setData_fim(data_fim.getValue());
		edital.setData_inicio(data_inicio.getValue());
		edital.setDocumentos_necessarios(documentos_necessarios.getValue());
		edital.setInformacoes_adicionais(informacoes_adicionais.getValue());
		edital.setN_vagas((int)n_vagas.getValue());
		edital.setProjeto_cod(Integer.parseInt(cbx_projeto.getSelectedItem().getLabel().split("-")[0].trim()));
		edital.setCod(Integer.parseInt(cbx_edital.getSelectedItem().getLabel().split("-")[0].trim()));
		
		if (editalDAO.updateOne(edital) > 0) {

			atualizar();
			Mensagem.sucessoUpdate();

		} else {
			Mensagem.insucessoUpdate();
		}
	}
}
