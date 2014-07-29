package org.ufba.dcc.mata60.controller;

import org.ufba.dcc.mata60.model.Aluno;
import org.ufba.dcc.mata60.model.AlunoDAO;
import org.ufba.dcc.mata60.model.AlunoInscricao;
import org.ufba.dcc.mata60.model.AlunoInscricaoDAO;
import org.ufba.dcc.mata60.model.Edital;
import org.ufba.dcc.mata60.model.EditalDAO;
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
	private Textbox cpf_aluno;
	
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
		String cpf = cpf_aluno.getText();
		AlunoDAO alunoDAO = new AlunoDAO();
		Aluno aluno = alunoDAO.getOneByCPF(cpf);
		
		if(aluno == null) {
			Mensagem.insucessoInscricaoEdital();
		} else {
			AlunoInscricao inscricao = new AlunoInscricao();
			inscricao.setAlunoCPF(cpf);
			inscricao.setEditalCod(Integer.parseInt(cod_edital.getText()));
			
			AlunoInscricaoDAO inscricaoDAO= new AlunoInscricaoDAO();
			
			if(inscricaoDAO.insert(inscricao) == 1) {
				Mensagem.sucessoInscricao();
				modalInscricao.detach();
			} else {
				Mensagem.alunoInscritoEdital();
			}
		}
	}
	
	@Listen("onClick = #btn_cancelar")
	public void desistirInscricao(){
		
		modalInscricao.detach();
		
	}
	
	
}
