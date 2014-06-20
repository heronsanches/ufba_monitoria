package org.ufba.dcc.mata60.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

public class TurmaDAO {
	
	public int insert(Turma turma){
		
		int count = 0;

		try {
			
			PreparedStatement preparedStatement = DB.getConnectionDB().prepareStatement("insert into " +
				DB.getDbName()+".turma values (?, ?, ?, ?, ?, ?)");
		
			preparedStatement.setString(1, turma.getNumero());
			preparedStatement.setTime(2, new Time(turma.getHora_inicio().getTime()));
			preparedStatement.setTime(3, new Time(turma.getHora_fim().getTime()));
			preparedStatement.setDate(4, new Date(turma.getData_inicio().getTime()));
			preparedStatement.setString(5, turma.getSemestre());
			preparedStatement.setString(6, turma.getDisciplina_cod());
			
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
	
	/** 
	 * @param semestre: ano+.+semestre
	 */
	public  ArrayList<Turma> getBySemestreDisciplinaCod(String semestre, String disciplina_cod) {

		try {
			
			Statement statement = DB.getConnectionDB().createStatement();
			
			ResultSet resultSet = statement.executeQuery("select * from "+DB.getDbName()+".turma"+
			" where semestre='"+semestre+"' and disciplina_cod='"+disciplina_cod+"'");
			
			Turma turma = null;
			ArrayList<Turma> turmas = new ArrayList<Turma>();

			while (resultSet.next()) {

				turma = new Turma();
				turma.setData_inicio(resultSet.getDate("data_inicio"));
				turma.setDisciplina_cod(resultSet.getString("disciplina_cod"));
				turma.setHora_fim(resultSet.getTime("hora_fim"));
				turma.setHora_inicio(resultSet.getTime("hora_inicio"));
				turma.setNumero(resultSet.getString("numero"));
				turma.setSemestre(resultSet.getString("semestre"));
				
				turmas.add(turma);
			
			}
			
			statement.close();
			return turmas;
			
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
