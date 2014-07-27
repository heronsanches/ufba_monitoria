package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Edital;
import org.ufba.dcc.mata60.model.EditalDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.ListModels;
import org.zkoss.zul.Textbox;

public class ExcluirEdital extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	private Textbox data_inicio;
	
	@Wire
	private Textbox data_fim;
	
	@Wire
	private Intbox cod_edital;
	
	@Wire
	private Intbox cod_projeto;
	
	@Wire
	private Intbox n_vagas;
	
	@Wire
	private Textbox informacoes_adicionais;
	
	@Wire
	private Textbox documentos_necessarios;	
	
	@Wire
	private Combobox cbx_edital;
	
	@Wire
	private Grid grid_excluir;
	
	
	private void atualizar() {
		Include include = (Include) Selectors
				.iterable(grid_excluir.getPage(), "#mainInclude").iterator().next();
		include.setSrc(null);
		include.setSrc("/template_edital.zul");
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
    	
		cbx_edital.setModel(ListModels.toListSubModel(new ListModelList<>(obtemEditais())));
    
    }
    
    @Listen("onSelect = #cbx_edital")
    public void mostraGridEditar(){
    	
    	Edital edital = new EditalDAO().getOneByCod((int)Integer.parseInt(cbx_edital.getSelectedItem().getLabel()
        		.split("-")[0].trim()));
    	
    	data_inicio.setValue(edital.getData_inicio().toString());
    	data_fim.setValue(edital.getData_fim().toString());
    	documentos_necessarios.setValue(edital.getDocumentos_necessarios());
    	informacoes_adicionais.setValue(edital.getInformacoes_adicionais());
    	n_vagas.setValue(edital.getN_vagas());
    	cod_edital.setValue(edital.getCod());
    	cod_projeto.setValue(edital.getProjeto_cod());
  
    	grid_excluir.setVisible(true);
    
    }
    
    

	@Listen("onClick = #btn_excluir")
	public void excluirEdital() {
		
		if (new EditalDAO().deleteOne((int)cod_edital.getValue()) > 0) {

			atualizar();
			Mensagem.sucessoDelete();

		} else {
			Mensagem.insucessoDelete();
		}
	}
	
}