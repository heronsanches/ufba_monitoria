package org.ufba.dcc.mata60.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DisciplinaDAO {

	
	public void insert(Disciplina disciplina){
		// PreparedStatements can use variables and are more efficient
		try {
			
			PreparedStatement preparedStatement = DB.getConnectionDB().prepareStatement("insert into " +
				DB.getDbName()+".disciplina values (?, ?, ?)");
		
			// Parameters start with 1
			preparedStatement.setString(1, disciplina.getCod());
			preparedStatement.setString(2, disciplina.getNome());
			preparedStatement.setInt(3, disciplina.getDepartamento_cod());
			
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
	
	
	public  ArrayList<Disciplina> getAll() {
		// Statements allow to issue SQL queries to the database
		try {
			
			Statement statement = DB.getConnectionDB().createStatement();
			
			// Result set get the result of the SQL query
			ResultSet resultSet = statement.executeQuery("select * from "+DB.getDbName()+".disciplina");
			
			Disciplina disciplina = null;
			ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();

			while (resultSet.next()) {
				// It is possible to get the columns via name
				// also possible to get the columns via the column number
				// which starts at 1
				// e.g. resultSet.getSTring(2);
				disciplina = new Disciplina();
				disciplina.setCod(resultSet.getString("cod"));
				disciplina.setDepartamento_cod(resultSet.getInt("departamento_cod"));
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
	
	
}
