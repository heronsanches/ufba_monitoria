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

	
	public  ArrayList<Turma> getAll() {

		try {
			
			Statement statement = DB.getConnectionDB().createStatement();
			
			ResultSet resultSet = statement.executeQuery("select * from "+DB.getDbName()+".turma");
			
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
	
	/**
	 * @param semestre = ano+"."+semestre(1 ou 2)
	 */
	public  Turma getOne(String numero, String semestre, String disciplina_cod) {

		try {
			
			Statement statement = DB.getConnectionDB().createStatement();
			
			ResultSet resultSet = statement.executeQuery("select * from "+DB.getDbName()+".turma"+
			" where semestre='"+semestre+"' and disciplina_cod='"+disciplina_cod+"'"
					+ " and numero='"+numero+"'");
			
			Turma turma = null;

			while (resultSet.next()) {

				turma = new Turma();
				turma.setData_inicio(resultSet.getDate("data_inicio"));
				turma.setDisciplina_cod(resultSet.getString("disciplina_cod"));
				turma.setHora_fim(resultSet.getTime("hora_fim"));
				turma.setHora_inicio(resultSet.getTime("hora_inicio"));
				turma.setNumero(resultSet.getString("numero"));
				turma.setSemestre(resultSet.getString("semestre"));
							
			}
			
			statement.close();
			return turma;
			
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
	
	
	public  int updateOne(Turma turmaNova, String numeroAntigo, String semestreAntigo, String disciplina_cod_antigo){
		
		int count = 0;
		
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = DB.getConnectionDB().prepareStatement("update "+DB.getDbName()+".turma"
					+ " set numero=?, hora_inicio=?, hora_fim=?, data_inicio=?,"
					+ " semestre=?, disciplina_cod=? where numero=? and semestre=? and"
					+ " disciplina_cod=?");
			
			preparedStatement.setString(1, turmaNova.getNumero());
			preparedStatement.setTime(2, new Time(turmaNova.getHora_inicio().getTime()));
			preparedStatement.setTime(3, new Time(turmaNova.getHora_fim().getTime()));
			preparedStatement.setDate(4, new Date(turmaNova.getData_inicio().getTime()));
			preparedStatement.setString(5, turmaNova.getSemestre());
			preparedStatement.setString(6, turmaNova.getDisciplina_cod());
			
			preparedStatement.setString(7, numeroAntigo);
			preparedStatement.setString(8, semestreAntigo);
			preparedStatement.setString(9, disciplina_cod_antigo);

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
	
	public  int deleteOne(String numero, String semestre, String disciplina_cod){
		
		int count = 0;
		
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = DB.getConnectionDB().prepareStatement("delete from "+DB.getDbName()+".turma"
					+ " where semestre=? and disciplina_cod=? and numero=?");
			
			preparedStatement.setString(1, semestre);
			preparedStatement.setString(2, disciplina_cod);
			preparedStatement.setString(3, numero);
			
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
