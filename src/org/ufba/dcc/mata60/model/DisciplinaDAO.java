package org.ufba.dcc.mata60.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DisciplinaDAO {

	public int insert(Disciplina disciplina) {

		int count = 0;

		try {

			PreparedStatement preparedStatement = DB.getConnectionDB()
					.prepareStatement(
							"insert into " + DB.getDbName()
									+ ".disciplina values (?, ?, ?)");

			preparedStatement.setString(1, disciplina.getCod());
			preparedStatement.setString(2, disciplina.getNome());
			preparedStatement.setInt(3, disciplina.getDepartamento_cod());

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

	public ArrayList<Disciplina> getAll() {

		try {

			Statement statement = DB.getConnectionDB().createStatement();

			ResultSet resultSet = statement.executeQuery("select * from "
					+ DB.getDbName() + ".disciplina");

			Disciplina disciplina = null;
			ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();

			while (resultSet.next()) {

				disciplina = new Disciplina();
				disciplina.setCod(resultSet.getString("cod"));
				disciplina.setDepartamento_cod(resultSet
						.getInt("departamento_cod"));
				disciplina.setNome(resultSet.getString("nome"));
				disciplinas.add(disciplina);

			}

			statement.close();
			return disciplinas;

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

	public Disciplina getOne(String cod) {

		try {

			Statement statement = DB.getConnectionDB().createStatement();

			ResultSet resultSet = statement.executeQuery("select * from "
					+ DB.getDbName() + ".disciplina " + "where cod='" + cod
					+ "'");

			Disciplina disc = null;
			while (resultSet.next()) {

				disc = new Disciplina();
				disc.setCod(resultSet.getString("cod"));
				disc.setDepartamento_cod(resultSet.getInt("departamento_cod"));
				disc.setNome(resultSet.getString("nome"));

			}

			statement.close();
			return disc;

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

	public int updateOne(Disciplina disciplina) {

		int count = 0;

		try {

			PreparedStatement preparedStatement;
			preparedStatement = DB
					.getConnectionDB()
					.prepareStatement(
							"update "
									+ DB.getDbName()
									+ ".disciplina"
									+ " set cod=?, nome=?, departamento_cod=? where cod=?");

			preparedStatement.setString(1, disciplina.getCod());
			preparedStatement.setString(2, disciplina.getNome());
			preparedStatement.setInt(3, disciplina.getDepartamento_cod());
			preparedStatement.setString(4, disciplina.getCod());

			count = preparedStatement.executeUpdate();

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

	public int deleteOne(String cod) {

		int count = 0;

		try {

			PreparedStatement preparedStatement;
			preparedStatement = DB.getConnectionDB().prepareStatement(
					"delete from " + DB.getDbName() + ".disciplina "
							+ "where cod=?");

			preparedStatement.setString(1, cod);

			count = preparedStatement.executeUpdate();

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

}
