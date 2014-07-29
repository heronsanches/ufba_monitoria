package org.ufba.dcc.mata60.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoInscricaoDAO {

	public int insert(AlunoInscricao inscricao) {
		int count = 0;
		PreparedStatement preparedStatement;

		try {

			preparedStatement = DB.getConnectionDB().prepareStatement(
					"insert into " + DB.getDbName()
							+ ".aluno_faz_inscricao(edital_cod, aluno_cpf, data_inscricao) values (?, ?, now())");

			preparedStatement.setInt(1, inscricao.getEditalCod());
			preparedStatement.setString(2, inscricao.getAlunoCPF());

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
