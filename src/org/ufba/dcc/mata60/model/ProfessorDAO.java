package org.ufba.dcc.mata60.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProfessorDAO {

	public int insert(Professor professor) {

		int count = 0;

		try {

			PreparedStatement preparedStatement = DB.getConnectionDB()
					.prepareStatement(
							"insert into " + DB.getDbName()
									+ ".professor values (?, ?, ?, ?, ?)");

			preparedStatement.setString(1, professor.getCpf());
			preparedStatement.setString(2, professor.getMatricula());
			preparedStatement.setString(3, professor.getNome());
			preparedStatement.setInt(4, professor.getDepartamento_cod());
			preparedStatement.setString(5, professor.getTipo());

			count = preparedStatement.executeUpdate(); // update
			preparedStatement.close();

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return count;

	}

	public ArrayList<Professor> getAll() {

		try {

			Statement statement = DB.getConnectionDB().createStatement();

			ResultSet resultSet = statement.executeQuery("select * from "
					+ DB.getDbName() + ".professor");

			Professor professor = null;
			ArrayList<Professor> professores = new ArrayList<Professor>();

			while (resultSet.next()) {
				// It is possible to get the columns via name
				// also possible to get the columns via the column number
				// which starts at 1
				// e.g. resultSet.getSTring(2);
				professor = new Professor();
				professor.setCpf(resultSet.getString("cpf"));
				professor.setDepartamento_cod(resultSet
						.getInt("departamento_cod"));
				professor.setMatricula(resultSet.getString("matricula"));
				professor.setNome(resultSet.getString("nome"));
				professor.setTipo(resultSet.getString("tipo"));

				professores.add(professor);

			}

			statement.close();
			return professores;

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}

	}

	public Professor getOneByMatricula(String matricula) {

		try {

			Statement statement = DB.getConnectionDB().createStatement();

			ResultSet resultSet = statement.executeQuery("select * from "
					+ DB.getDbName() + ".professor " + "where matricula='"
					+ matricula + "'");

			Professor professor = null;
			if (resultSet.next()) {

				professor = new Professor();
				professor.setCpf(resultSet.getString("cpf"));
				professor.setDepartamento_cod(resultSet
						.getInt("departamento_cod"));
				professor.setMatricula(resultSet.getString("matricula"));
				professor.setNome(resultSet.getString("nome"));
				professor.setTipo(resultSet.getString("tipo"));

			}

			statement.close();
			return professor;

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}

	}
	
	public Professor getOneByCPF(String cpf) {

		try {

			Statement statement = DB.getConnectionDB().createStatement();

			ResultSet resultSet = statement.executeQuery("select * from "
					+ DB.getDbName() + ".professor " + "where cpf='"
					+ cpf + "'");

			Professor professor = null;
			if (resultSet.next()) {

				professor = new Professor();
				professor.setCpf(resultSet.getString("cpf"));
				professor.setDepartamento_cod(resultSet
						.getInt("departamento_cod"));
				professor.setMatricula(resultSet.getString("matricula"));
				professor.setNome(resultSet.getString("nome"));
				professor.setTipo(resultSet.getString("tipo"));

			}

			statement.close();
			return professor;

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}

	}

	public int updateOne(Professor professor) {

		int count = 0;

		try {

			PreparedStatement preparedStatement;
			preparedStatement = DB
					.getConnectionDB()
					.prepareStatement(
							"update "
									+ DB.getDbName()
									+ ".professor"
									+ " set matricula=?, nome=?, departamento_cod=?, cpf=?, tipo=? where matricula=?");

			preparedStatement.setString(1, professor.getMatricula());
			preparedStatement.setString(2, professor.getNome());
			preparedStatement.setInt(3, professor.getDepartamento_cod());
			preparedStatement.setString(4, professor.getCpf());
			preparedStatement.setString(5, professor.getTipo());
			preparedStatement.setString(6, professor.getMatricula());

			count = preparedStatement.executeUpdate();

			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;

	}

	public int deleteOne(String matricula) {

		int count = 0;

		try {

			PreparedStatement preparedStatement;
			preparedStatement = DB.getConnectionDB().prepareStatement(
					"delete from " + DB.getDbName() + ".professor "
							+ "where matricula=?");

			preparedStatement.setString(1, matricula);

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
