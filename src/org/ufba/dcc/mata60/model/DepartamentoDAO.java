package org.ufba.dcc.mata60.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DepartamentoDAO {
	
	public  ArrayList<Departamento> getAll() {

		try {
			
			Statement statement = DB.getConnectionDB().createStatement();
			
			ResultSet resultSet = statement.executeQuery("select * from "+DB.getDbName()+".departamento");
			
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


	public  int insert(Departamento departamento){

		int count = 0;
		PreparedStatement preparedStatement;
			
			try {
				
				preparedStatement = DB.getConnectionDB().prepareStatement("insert into " +
					DB.getDbName()+".departamento values (default, ?)");
				
				preparedStatement.setString(1, departamento.getNome());

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

	
	public  int updateOne(Departamento departamento){
		
		int count = 0;
		
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = DB.getConnectionDB().prepareStatement("update "+DB.getDbName()+".departamento set nome=?"
					+ " where cod=?");
			
			preparedStatement.setString(1, departamento.getNome());
			preparedStatement.setInt(2, departamento.getCod());

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
	
	
public  int deleteOne(Departamento departamento){
		
		int count = 0;
		
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = DB.getConnectionDB().prepareStatement("delete from "+DB.getDbName()+".departamento "
					+ "where cod=?");
			
			preparedStatement.setInt(1, departamento.getCod());

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
