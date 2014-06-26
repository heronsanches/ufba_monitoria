package org.ufba.dcc.mata60.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlunoDAO {

	public ArrayList<Aluno> getAll() {

		try {

			Statement statement = DB.getConnectionDB().createStatement();

			ResultSet resultSet = statement.executeQuery("select * from "
					+ DB.getDbName() + ".aluno");

			Aluno aluno = null;
			ArrayList<Aluno> alunos = new ArrayList<Aluno>();

			while (resultSet.next()) {
				// It is possible to get the columns via name
				// also possible to get the columns via the column number
				// which starts at 1
				// e.g. resultSet.getSTring(2);
				aluno = new Aluno();
				aluno.setCpf(resultSet.getString("cpf"));
				aluno.setNome(resultSet.getString("nome"));
				aluno.setMatricula(resultSet.getString("matricula"));
				
				alunos.add(aluno);
			}

			statement.close();
			return alunos;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public Aluno getOneByCPF(String cpf) {

		try {

			PreparedStatement preparedStatement;
			preparedStatement = DB.getConnectionDB().prepareStatement(
					"select * from " + DB.getDbName() + ".aluno "
							+ "where cpf=?");

			preparedStatement.setString(1, cpf);

			ResultSet resultSet = preparedStatement.executeQuery();
			
			Aluno aluno = null;
			
			if (resultSet.next()) {
				// It is possible to get the columns via name
				// also possible to get the columns via the column number
				// which starts at 1
				// e.g. resultSet.getSTring(2);
				aluno = new Aluno();
				aluno.setCpf(resultSet.getString("cpf"));
				aluno.setNome(resultSet.getString("nome"));
				aluno.setMatricula(resultSet.getString("matricula"));				
			}

			preparedStatement.close();
			return aluno;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public int insert(Aluno aluno) {

		int count = 0;
		PreparedStatement preparedStatement;

		try {

			preparedStatement = DB.getConnectionDB().prepareStatement(
					"insert into " + DB.getDbName()
							+ ".aluno values (?, ?, ?)");

			preparedStatement.setString(1, aluno.getCpf());
			preparedStatement.setString(2, aluno.getMatricula());
			preparedStatement.setString(3, aluno.getNome());
			
			count = preparedStatement.executeUpdate();

			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public int updateOne(Aluno aluno) {

		int count = 0;

		try {

			PreparedStatement preparedStatement;
			preparedStatement = DB.getConnectionDB().prepareStatement(
					"update " + DB.getDbName() + ".aluno set nome=?, matricula=?, cpf=?"
							+ " where cpf=?");

			preparedStatement.setString(1, aluno.getNome());
			preparedStatement.setString(2, aluno.getMatricula());
			preparedStatement.setString(3, aluno.getCpf());
			preparedStatement.setString(4, aluno.getCpf());

			count = preparedStatement.executeUpdate();

			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public int deleteOne(Aluno aluno) {

		int count = 0;

		try {

			PreparedStatement preparedStatement;
			preparedStatement = DB.getConnectionDB().prepareStatement(
					"delete from " + DB.getDbName() + ".aluno "
							+ "where cpf=?");

			preparedStatement.setString(1, aluno.getCpf());

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
