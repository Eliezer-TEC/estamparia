package model.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import model.vo.Camisa;
import model.vo.Pessoa;

public class CamisaDAO {
	public Camisa inserir(Camisa novaCamisa) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO CAMISA(ID_PEDIDO, TAMANHO, COR, ESTAMPA, NOMEARQUIVO ) " + " VALUES (?,?,?,?,?) ";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		try {
			stmt.setInt(1, novaCamisa.getIdPedido() == null ? 0 : novaCamisa.getIdPedido());
			stmt.setString(2, novaCamisa.getTamanho());
			stmt.setString(3, novaCamisa.getCor());
			stmt.setBytes(4, novaCamisa.getEstampa());
			stmt.setString(5, novaCamisa.getNomeArquivo());
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
	
	private Camisa montarCamisaComResultadoDoBanco(ResultSet resultado) throws SQLException {
		Camisa camisaBuscada = new Camisa();
		camisaBuscada.setId(resultado.getInt("id"));
		camisaBuscada.setTamanho(resultado.getString("tamanho"));
		camisaBuscada.setCor(resultado.getString("cor"));
		camisaBuscada.setEstampa(resultado.getBytes("estampa"));
		camisaBuscada.setNomeArquivo(resultado.getString("nomearquivo"));
		camisaBuscada.setIdPedido(resultado.getInt("id_pedido"));

		return camisaBuscada;
	}
	
	public Camisa consultarPorId(int id) {
		Camisa camisaConsultada = null;
		Connection conexao = Banco.getConnection();
		String sql =  " SELECT * FROM CAMISA "
				    + " WHERE ID = ?";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			
			if(resultado.next()) {
				camisaConsultada = montarCamisaComResultadoDoBanco(resultado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar camisa com id: + " + id 
								+ "\n Causa: " + e.getMessage());	
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return camisaConsultada;
	}

	public ArrayList<Camisa> cansultarCamisasDoPedido(int idPedido) {
		ArrayList<Camisa> camisas = new ArrayList();
		Connection conexao = Banco.getConnection();
		String sql =  " SELECT * FROM CAMISA "
				    + " WHERE ID_PEDIDO = ?";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		try {
			query.setInt(1, idPedido);
			ResultSet resultado = query.executeQuery();

			while (resultado.next()) {
				Camisa camisaBuscada = montarCamisaComResultadoDoBanco(resultado);
				camisas.add(camisaBuscada);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar camisas do pedido " + idPedido +". \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
	    return camisas;
	}
	

	public void inserirCamisa(Integer id, ArrayList<Camisa> camisas) {
		for(Camisa camisaDoUsuarioCamisa : camisas) {
			camisaDoUsuarioCamisa.setIdPedido(id);
			this.inserir(camisaDoUsuarioCamisa);
	
		}
		
	}

}
