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
import org.zkoss.zul.Include;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.ListModels;
import org.zkoss.zul.Textbox;

public class CadastrarEdital extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	Datebox data_inicio;
	
	@Wire
	Datebox data_fim;
	
	@Wire
	private Intbox n_vagas;
	
	@Wire
	private Textbox documentos_necessarios;
	
	@Wire
	private Textbox informacoes_adicionais;
	
	@Wire
	private Combobox cbx_projeto;
	
	
	private void atualizar() {
		Include include = (Include) Selectors
				.iterable(n_vagas.getPage(), "#mainInclude").iterator().next();
		include.setSrc(null);
		include.setSrc("/template_edital.zul");
	}

	private ArrayList<String> obtemProjetos(){
		
		ProjetoDAO projetoDAO = new ProjetoDAO();
		ArrayList<String> projetos = new ArrayList<String>();
				
		for(Projeto projeto: projetoDAO.getAll())
			projetos.add(projeto.getCod()+"-"+projeto.getDescricao());
		
		return projetos;
		
	}
	
    @Override
    public void doAfterCompose(Component comp) throws Exception{
          
    	super.doAfterCompose(comp);
    	
		cbx_projeto.setModel(ListModels.toListSubModel(new ListModelList<>(obtemProjetos())));
    
    }

	@Listen("onClick = #btn_cadastrar")
	public void cadastrarEdital() {

		EditalDAO editalDAO = new EditalDAO();
		Edital edital = new Edital();

		edital.setData_fim(data_fim.getValue());
		edital.setData_inicio(data_inicio.getValue());
		edital.setDocumentos_necessarios(documentos_necessarios.getValue());
		edital.setInformacoes_adicionais(informacoes_adicionais.getValue());
		edital.setN_vagas((int)n_vagas.getValue());
		edital.setProjeto_cod(Integer.parseInt(cbx_projeto.getSelectedItem().getLabel().split("-")[0].trim()));

		if (editalDAO.insert(edital) > 0) {

			atualizar();
			Mensagem.sucesso();

		} else {
			Mensagem.insucesso();
		}
	}

}
