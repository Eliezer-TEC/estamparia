package model.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import model.vo.Camisa;

public class CamisaDAO {
	public Camisa inserir(Camisa novaCamisa) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO CAMISA(ID_PEDIDO, TAMANHO, COR, ESTAMPA ) " + " VALUES (?,?,?,?) ";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		try {
			stmt.setInt(1, novaCamisa.getIdPedido() == null ? 0 : novaCamisa.getIdPedido());
			stmt.setString(2, novaCamisa.getTamanho());
			stmt.setString(3, novaCamisa.getCor());
			stmt.setBytes(4, novaCamisa.getEstampa());
			stmt.execute();

			// Preencher o id gerado no banco no objeto
			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				novaCamisa.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir nova Camisa.");
			System.out.println("Erro: " + e.getMessage());
		}

		return novaCamisa;
	}

	public boolean estampaJaUtilizada(Blob estampa) {
		boolean estampaJaUtilizada = false;
		Connection conexao = Banco.getConnection();
		String sql = " select count(*) from camisa " + " where estampa = ? ";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setBlob(1, estampa);
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				estampaJaUtilizada = resultado.getInt(1) > 0;
			}
		} catch (Exception e) {
			System.out.println("Erro ao verificar uso da Estampa " + estampa + "\n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return estampaJaUtilizada;
	}

	

	public void inserirCamisa(Integer id, ArrayList<Camisa> camisas) {
		for(Camisa camisaDoUsuarioCamisa : camisas) {
			camisaDoUsuarioCamisa.setIdPedido(id);
			this.inserir(camisaDoUsuarioCamisa);
	
		}
		
	}

}
