package org.ufba.dcc.mata60.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TurmaLecionadaProfessorDAO {
	
	public int insert(String turma_numero, String professor_cpf,  String turma_disciplina_cod,
			String turma_semestre){
		
		int count = 0;

		try {
			
			PreparedStatement preparedStatement = DB.getConnectionDB().prepareStatement("insert into " +
				DB.getDbName()+".turma_lecionada_professor values (?, ?, ?, ?)");
		
			preparedStatement.setString(1, turma_numero);
			preparedStatement.setString(2, professor_cpf);
			preparedStatement.setString(3, turma_disciplina_cod);
			preparedStatement.setString(4, turma_semestre);

			
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
