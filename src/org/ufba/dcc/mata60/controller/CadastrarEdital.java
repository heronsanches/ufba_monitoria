package org.ufba.dcc.mata60.controller;

import java.util.ArrayList;

import org.ufba.dcc.mata60.model.Edital;
import org.ufba.dcc.mata60.model.EditalDAO;
import org.ufba.dcc.mata60.model.Professor;
import org.ufba.dcc.mata60.model.ProfessorDAO;
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
import org.zkoss.zul.Messagebox;
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

		int projectId = Integer.parseInt(cbx_projeto.getSelectedItem().getLabel().split("-")[0].trim());
		int inVagas = (int)n_vagas.getValue();
		
		if(!validateNumVagas(projectId, inVagas))
			return;
			
		EditalDAO editalDAO = new EditalDAO();
		Edital edital = new Edital();

		edital.setData_fim(data_fim.getValue());
		edital.setData_inicio(data_inicio.getValue());
		edital.setDocumentos_necessarios(documentos_necessarios.getValue());
		edital.setInformacoes_adicionais(informacoes_adicionais.getValue());
		edital.setN_vagas(inVagas);
		edital.setProjeto_cod(projectId);

		if (editalDAO.insert(edital) > 0) {

			atualizar();
			Mensagem.sucesso();

		} else {
			Mensagem.insucesso();
		}
	}
	
	private boolean validateNumVagas(int projectId, int numVagas) {
		ProjetoDAO projetoDAO = new ProjetoDAO();
		Projeto projeto = projetoDAO.getOneByCod(projectId);
		
		ProfessorDAO professorDAO = new ProfessorDAO();
		Professor p = professorDAO.getOneByCPF(projeto.getProfessorCpf());
		
		int maxVagas = 0;
		
		if (p.getTipo().equals("DE")) {
			maxVagas = 3;
		} else if (p.getTipo().equals("20H")) {
			maxVagas = 1;
		} else if (p.getTipo().equals("40H")) {
			maxVagas = 2;
		}
		
		if (numVagas > maxVagas) {
			Messagebox msg = new Messagebox();
			msg.show("Não foi posível abrir este edital, pois o Número de vagas máxima para este professor é " + maxVagas, "", Messagebox.OK,
					Messagebox.EXCLAMATION);
			return false;
		}
		
		return true;
		
	}

}
