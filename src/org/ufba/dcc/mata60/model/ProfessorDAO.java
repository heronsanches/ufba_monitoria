package org.ufba.dcc.mata60.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ProfessorDAO {
	
	
	public void insert(Professor professor){

		try {
			
			PreparedStatement preparedStatement = DB.getConnectionDB().prepareStatement("insert into " +
				DB.getDbName()+".professor values (?, ?, ?, ?, ?)");
		
			preparedStatement.setString(1, professor.getCpf());
			preparedStatement.setString(2, professor.getMatricula());
			preparedStatement.setString(3, professor.getNome());
			preparedStatement.setInt(4, professor.getDepartamento_cod());
			preparedStatement.setString(5, professor.getTipo());
			
			preparedStatement.executeUpdate(); // update
			preparedStatement.close();
		
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	public  ArrayList<Professor> getAll() {

		try {
			
			Statement statement = DB.getConnectionDB().createStatement();
			
			ResultSet resultSet = statement.executeQuery("select * from "+DB.getDbName()+".professor");
			
			Professor professor = null;
			ArrayList<Professor> professores = new ArrayList<Professor>();

			while (resultSet.next()) {
				// It is possible to get the columns via name
				// also possible to get the columns via the column number
				// which starts at 1
				// e.g. resultSet.getSTring(2);
				professor = new Professor();
				professor.setCpf(resultSet.getString("cpf"));
				professor.setDepartamento_cod(resultSet.getInt("departamento_cod"));
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

}
