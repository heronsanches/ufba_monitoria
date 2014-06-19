package org.ufba.dcc.mata60.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TurmaLecionadaProfessorDAO {
	
	public void insert(String professor_cpf, String turma_numero, String turma_disciplina_cod,
			String turma_semestre){
		// PreparedStatements can use variables and are more efficient
		try {
			
			PreparedStatement preparedStatement = DB.getConnectionDB().prepareStatement("insert into " +
				DB.getDbName()+".turma_lecionada_professor values (?, ?, ?, ?)");
		
			// Parameters start with 1
			preparedStatement.setString(1, professor_cpf);
			preparedStatement.setString(2, turma_numero);
			preparedStatement.setString(3, turma_disciplina_cod);
			preparedStatement.setString(4, turma_semestre);

			
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
