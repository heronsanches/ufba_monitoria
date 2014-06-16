package org.ufba.dcc.mata60.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DepartamentoDAO {
	
	private  Statement statement = null;
	private  PreparedStatement preparedStatement = null;
	private  ResultSet resultSet = null;

	
	public  ArrayList<Departamento> getAll() {
		// Statements allow to issue SQL queries to the database
		try {
			
			statement = DB.getConnectionDB().createStatement();
			
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from "+DB.getDbName()+".departamento");
			
			Departamento departamento = null;
			ArrayList<Departamento> departamentos = new ArrayList<Departamento>();

			while (resultSet.next()) {
				// It is possible to get the columns via name
				// also possible to get the columns via the column number
				// which starts at 1
				// e.g. resultSet.getSTring(2);
				departamento = new Departamento();
				departamento.setCod(resultSet.getInt("cod"));
				departamento.setNome(resultSet.getString("nome"));
				departamentos.add(departamento);
			
			}
			
			statement.close();
			return departamentos;
			
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


	public  void insert(Departamento departamento) throws SQLException, Exception{
		// PreparedStatements can use variables and are more efficient
		preparedStatement = DB.getConnectionDB().prepareStatement("insert into " +
			DB.getDbName()+".departamento values (default, ?)");

		// Parameters start with 1
		preparedStatement.setString(1, departamento.getNome());

		preparedStatement.executeUpdate(); // update
		
		preparedStatement.close();
	}

	/*
	public  void updateOne(Loc loc) throws SQLException, Exception{
		
		preparedStatement = DB.getConnectionDB().prepareStatement("update mydb.loc set quantity=?"
				+ ", idLastCommit=?"+ ", dateLastCommit=? where id=?");

		preparedStatement.setInt(1, loc.getQuantity());
		preparedStatement.setString(2, loc.getIdLastCommit());
		preparedStatement.setLong(2, loc.getDateLastCommit());
		preparedStatement.setInt(4, loc.getId());
		
		preparedStatement.executeUpdate(); // update
		
		preparedStatement.close();

	}*/

}
