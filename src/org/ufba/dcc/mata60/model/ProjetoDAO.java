package org.ufba.dcc.mata60.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProjetoDAO {

	public int insert(Projeto projeto) {

		int count = 0;

		try {

			PreparedStatement preparedStatement = DB.getConnectionDB()
					.prepareStatement(
							"insert into " + DB.getDbName()
									+ ".projeto(cod, descricao, turma_numero, professor_cpf,"
									+ " turma_disciplina_cod, turma_semestre, atividades_gerais)"
									+ " values (?, ?, ?, ?, ?, ?, ?)");
 
			preparedStatement.setInt(1, projeto.getCod());
			preparedStatement.setString(2, projeto.getDescricao());
			preparedStatement.setString(3, projeto.getTurmaNumero());
			preparedStatement.setString(4, projeto.getProfessorCpf());
			preparedStatement.setString(5, projeto.getTurmaDisciplinaCod());
			preparedStatement.setString(6, projeto.getTurmaSemestre());
			preparedStatement.setString(7, projeto.getAtividades_gerais());

			count = preparedStatement.executeUpdate(); // update
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;

	}

	public ArrayList<Projeto> getAll() {

		try {

			Statement statement = DB.getConnectionDB().createStatement();

			ResultSet resultSet = statement.executeQuery("select * from "
					+ DB.getDbName() + ".projeto");

			Projeto projeto = null;
			ArrayList<Projeto> projetos = new ArrayList<Projeto>();

			while (resultSet.next()) {
				projeto = new Projeto();
				
				projeto.setCod(resultSet.getInt("cod"));
				projeto.setDescricao(resultSet.getString("descricao"));
				projeto.setProfessorCpf(resultSet.getString("professor_cpf"));
				projeto.setDataAprovacao(resultSet.getDate("data_approvacao"));
				projeto.setAtaAprovacao(resultSet.getString("ata_aprovacao"));
				projeto.setTurmaNumero(resultSet.getString("turma_numero"));
				projeto.setTurmaDisciplinaCod(resultSet.getString("turma_disciplina_cod"));
				projeto.setTurmaSemestre(resultSet.getString("turma_semestre"));
				projeto.setAtividades_gerais(resultSet.getString("atividades_gerais"));
				
				projetos.add(projeto);

			}

			statement.close();
			return projetos;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Projeto getOneByCod(int cod) {

		try {


			PreparedStatement preparedStatement = DB.getConnectionDB().prepareStatement("select * from "
					+ DB.getDbName() + ".projeto " + "where cod=?");

			preparedStatement.setInt(1, cod);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			Projeto projeto = null;
			if (resultSet.next()) {

				projeto = new Projeto();
				
				projeto.setCod(resultSet.getInt("cod"));
				projeto.setDescricao(resultSet.getString("descricao"));
				projeto.setProfessorCpf(resultSet.getString("professor_cpf"));
				projeto.setDataAprovacao(resultSet.getDate("data_approvacao"));
				projeto.setAtaAprovacao(resultSet.getString("ata_aprovacao"));
				projeto.setTurmaNumero(resultSet.getString("turma_numero"));
				projeto.setTurmaDisciplinaCod(resultSet.getString("turma_disciplina_cod"));
				projeto.setTurmaSemestre(resultSet.getString("turma_semestre"));
				projeto.setAtividades_gerais(resultSet.getString("atividades_gerais"));

				
			}

			preparedStatement.close();
			return projeto;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public int updateOne(Projeto projeto) {

		int count = 0;

		try {

			PreparedStatement preparedStatement;
			preparedStatement = DB
					.getConnectionDB()
					.prepareStatement(
							"update "
									+ DB.getDbName()
									+ ".projeto"
									+ " set cod=?, descricao=?, professor_cpf=?, data_approvacao=?,"
									+ " ata_aprovacao=?, turma_numero=?, turma_disciplina_cod=?,"
									+ " turma_semestre=?,  atividades_gerais=? where cod=?");

			preparedStatement.setInt(1, projeto.getCod());
			preparedStatement.setString(2, projeto.getDescricao());
			preparedStatement.setString(3, projeto.getProfessorCpf());
			
			if(projeto.getDataAprovacao() != null) {
				preparedStatement.setDate(4, new java.sql.Date(projeto.getDataAprovacao().getTime()));
			} else {
				preparedStatement.setNull(4, java.sql.Types.DATE);;
			}
			
			if(projeto.getDataAprovacao() != null) {
				preparedStatement.setString(5, projeto.getAtaAprovacao());
			} else {
				preparedStatement.setNull(5, java.sql.Types.VARCHAR);;
			}
			
			preparedStatement.setString(6, projeto.getTurmaNumero());
			preparedStatement.setString(7, projeto.getTurmaDisciplinaCod());
			preparedStatement.setString(8, projeto.getTurmaSemestre());
			preparedStatement.setString(9, projeto.getAtividades_gerais());
			preparedStatement.setInt(10, projeto.getCod());
			
			count = preparedStatement.executeUpdate();

			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;

	}

	public int deleteOne(int cod) {

		int count = 0;

		try {

			PreparedStatement preparedStatement;
			preparedStatement = DB.getConnectionDB().prepareStatement(
					"delete from " + DB.getDbName() + ".projeto "
							+ "where cod=?");

			preparedStatement.setInt(1, cod);

			count = preparedStatement.executeUpdate();

			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;

	}

}
