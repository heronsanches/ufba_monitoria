package org.ufba.dcc.mata60.controller;

import org.ufba.dcc.mata60.model.Edital;
import org.ufba.dcc.mata60.model.Professor;
import org.ufba.dcc.mata60.model.ProfessorDAO;
import org.ufba.dcc.mata60.model.Projeto;
import org.ufba.dcc.mata60.model.ProjetoDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


public class ModalInscricao extends SelectorComposer<Component>{

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
	private Textbox descricao_projeto;
	
	@Wire
	private Textbox professor_projeto;
	
	@Wire
	private Textbox	turma_projeto;
	
	@Wire
	private Textbox atividades_gerais_projeto;
	
	@Wire
	private Window modalInscricao;

	
	private void mostraEdital(Edital edital){
		
		cod_edital.setValue(edital.getCod());
		data_inicio.setValue(edital.getData_inicio().toString());
		data_fim.setValue(edital.getData_fim().toString());
		n_vagas.setValue(edital.getN_vagas());
		documentos_necessarios.setValue(edital.getDocumentos_necessarios());
		informacoes_adicionais.setValue(edital.getInformacoes_adicionais());
	}
	
	private void mostraProjeto(int projeto_cod){
		
		Projeto projeto = new ProjetoDAO().getOneByCod(projeto_cod);
		cod_projeto.setValue(projeto.getCod());
		descricao_projeto.setValue(projeto.getDescricao());
		
		Professor professor = new ProfessorDAO().getOneByCPF(projeto.getProfessorCpf());
		professor_projeto.setValue(professor.getCpf()+"-"+professor.getNome());
		
		turma_projeto.setValue(projeto.getTurmaDisciplinaCod()+"-"+projeto.getTurmaSemestre()+"-"+
			projeto.getTurmaNumero());
		atividades_gerais_projeto.setValue(projeto.getAtividades_gerais());
		
		
	}
	 
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	    
		Edital edital = (Edital) Executions.getCurrent().getArg().get("edital");
		mostraEdital(edital);
		mostraProjeto(edital.getProjeto_cod());
		
	}
	
	//TODO
	@Listen("onClick = #btn_inscricao")
	public void realizarInscricao(){
		
		
	}
	
	@Listen("onClick = #btn_cancelar")
	public void desistirInscricao(){
		
		modalInscricao.detach();
		
	}
	
	
}
