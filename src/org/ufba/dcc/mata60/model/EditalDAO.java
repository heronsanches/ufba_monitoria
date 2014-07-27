package org.ufba.dcc.mata60.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EditalDAO {
	
	public int insert(Edital edital) {

		int count = 0;

		try {

			PreparedStatement preparedStatement = DB.getConnectionDB()
					.prepareStatement("insert into " +DB.getDbName()+ ".edital("
							+ "informacoes_adicionais, n_vagas, data_inicio, data_fim,"
							+ " documentos_necessarios, projeto_cod)"
							+ " values (?, ?, ?, ?, ?, ?)");

			preparedStatement.setString(1, edital.getInformacoes_adicionais());
			preparedStatement.setInt(2, edital.getN_vagas());
			preparedStatement.setDate(3, new Date(edital.getData_inicio().getTime()));
			preparedStatement.setDate(4, new Date(edital.getData_fim().getTime()));
			preparedStatement.setString(5, edital.getDocumentos_necessarios());
			preparedStatement.setInt(6, edital.getProjeto_cod());

			count = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;

	}

	public ArrayList<Edital> getAll() {

		try {

			Statement statement = DB.getConnectionDB().createStatement();

			ResultSet resultSet = statement.executeQuery("select * from "
					+ DB.getDbName() + ".edital");

			Edital edital = null;
			ArrayList<Edital> editais = new ArrayList<Edital>();

			while (resultSet.next()) {
				edital = new Edital();
				
				edital.setCod(resultSet.getInt(1));
				edital.setInformacoes_adicionais(resultSet.getString(2));
				edital.setN_vagas(resultSet.getInt(3));
				edital.setData_inicio(resultSet.getDate(4));
				edital.setData_fim(resultSet.getDate(5));
				edital.setDocumentos_necessarios(resultSet.getString(6));
				edital.setProjeto_cod(resultSet.getInt(7));
				
				editais.add(edital);

			}

			statement.close();
			return editais;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public ArrayList<Edital> getOpened() {

		try {

			PreparedStatement preparedStatement = DB.getConnectionDB().prepareStatement("select * from "
					+DB.getDbName()+ ".edital where data_fim >= ? order by cod");
			preparedStatement.setDate(1, new Date(new java.util.Date().getTime()));

			ResultSet resultSet = preparedStatement.executeQuery();

			Edital edital = null;
			ArrayList<Edital> editais = new ArrayList<Edital>();

			while (resultSet.next()) {
				edital = new Edital();
				
				edital.setCod(resultSet.getInt(1));
				edital.setInformacoes_adicionais(resultSet.getString(2));
				edital.setN_vagas(resultSet.getInt(3));
				edital.setData_inicio(resultSet.getDate(4));
				edital.setData_fim(resultSet.getDate(5));
				edital.setDocumentos_necessarios(resultSet.getString(6));
				edital.setProjeto_cod(resultSet.getInt(7));
				
				editais.add(edital);

			}

			preparedStatement.close();
			return editais;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public ArrayList<Edital> getClosed() {

		try {

			PreparedStatement preparedStatement = DB.getConnectionDB().prepareStatement("select * from "
					+DB.getDbName()+ ".edital where data_fim < ? order by cod");
			preparedStatement.setDate(1, new Date(new java.util.Date().getTime()));

			ResultSet resultSet = preparedStatement.executeQuery();

			Edital edital = null;
			ArrayList<Edital> editais = new ArrayList<Edital>();

			while (resultSet.next()) {
				edital = new Edital();
				
				edital.setCod(resultSet.getInt(1));
				edital.setInformacoes_adicionais(resultSet.getString(2));
				edital.setN_vagas(resultSet.getInt(3));
				edital.setData_inicio(resultSet.getDate(4));
				edital.setData_fim(resultSet.getDate(5));
				edital.setDocumentos_necessarios(resultSet.getString(6));
				edital.setProjeto_cod(resultSet.getInt(7));
				
				editais.add(edital);

			}

			preparedStatement.close();
			return editais;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Edital getOneByCod(int cod) {

		try {


			PreparedStatement preparedStatement = DB.getConnectionDB().prepareStatement("select * from "
					+ DB.getDbName() + ".edital " + "where cod=?");

			preparedStatement.setInt(1, cod);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			Edital edital = null;
			if (resultSet.next()) {

				edital = new Edital();
				edital.setCod(resultSet.getInt(1));
				edital.setInformacoes_adicionais(resultSet.getString(2));
				edital.setN_vagas(resultSet.getInt(3));
				edital.setData_inicio(resultSet.getDate(4));
				edital.setData_fim(resultSet.getDate(5));
				edital.setDocumentos_necessarios(resultSet.getString(6));
				edital.setProjeto_cod(resultSet.getInt(7));
				
			}

			preparedStatement.close();
			return edital;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	

	public int updateOne(Edital edital) {

		int count = 0;

		try {

			PreparedStatement preparedStatement;
			preparedStatement = DB
					.getConnectionDB().prepareStatement("update "+ DB.getDbName()+ ".edital"
						+ " set informacoes_adicionais=?, n_vagas=?, data_inicio=?, data_fim=?,"
						+ " documentos_necessarios=?, projeto_cod=? where cod=?");

			preparedStatement.setString(1, edital.getInformacoes_adicionais());
			preparedStatement.setInt(2, edital.getN_vagas());
			preparedStatement.setDate(3, new Date(edital.getData_inicio().getTime()));
			preparedStatement.setDate(4, new Date(edital.getData_fim().getTime()));
			preparedStatement.setString(5, edital.getDocumentos_necessarios());
			preparedStatement.setInt(6, edital.getProjeto_cod());
			preparedStatement.setInt(7, edital.getCod());
			
			count = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;

	}

	public int deleteOne(int cod) {

		int count = 0;

		try {

			PreparedStatement preparedStatement;
			preparedStatement = DB.getConnectionDB().prepareStatement(
					"delete from " + DB.getDbName() + ".edital "
							+ "where cod=?");

			preparedStatement.setInt(1, cod);

			count = preparedStatement.executeUpdate();

			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;

	}


}
