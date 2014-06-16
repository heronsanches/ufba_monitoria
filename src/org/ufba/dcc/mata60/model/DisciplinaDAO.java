package org.ufba.dcc.mata60.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DisciplinaDAO {
	
	//private  Statement statement = null;
	private  PreparedStatement preparedStatement = null;
	//private  ResultSet resultSet = null;

	
	public void insert(Disciplina disciplina){
		// PreparedStatements can use variables and are more efficient
		try {
			
			preparedStatement = DB.getConnectionDB().prepareStatement("insert into " +
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
	
	
}
