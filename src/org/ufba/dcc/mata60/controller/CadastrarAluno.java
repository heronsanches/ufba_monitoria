package org.ufba.dcc.mata60.controller;

import java.sql.SQLException;

import org.ufba.dcc.mata60.model.Aluno;
import org.ufba.dcc.mata60.model.AlunoDAO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;
import org.zkoss.zul.Textbox;

public class CadastrarAluno extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	private Textbox cpf;

	@Wire
	private Textbox nome;

	@Wire
	private Textbox matricula;

	private void atualizar() {
		Include include = (Include) Selectors
				.iterable(nome.getPage(), "#mainInclude").iterator().next();
		include.setSrc(null);
		include.setSrc("/template_aluno.zul");
	}

	@Listen("onClick = #btn_cadastrar")
	public void cadastrar() throws SQLException, Exception {

		AlunoDAO alunoDAO = new AlunoDAO();
		Aluno aluno = new Aluno();

		aluno.setCpf(cpf.getValue());
		aluno.setNome(nome.getValue());
		aluno.setMatricula(matricula.getValue());

		if (alunoDAO.insert(aluno) > 0) {

			Mensagem.sucesso();
			atualizar();

		} else {
			Mensagem.insucesso();
		}
	}

	@Listen("onClick = #btn_apagarCampos")
	public void apagaCampos() {
	}

}
