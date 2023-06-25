package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.seletor.PedidoSeletor;
import model.vo.Camisa;
import model.vo.Pedido;
import model.vo.SituacaoPedido;

public class PedidoDAO {
	public Pedido inserir(Pedido novoPedido) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO PEDIDO(STATUS_PEDIDO, ID_PESSOA) " + " VALUES (?,?) ";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		try {
			stmt.setInt(1, novoPedido.getIdPessoa());
			stmt.setInt(2, novoPedido.getSituacaoPedido().getValor());

			stmt.execute();

			// Preencher o id gerado no banco no objeto
			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				novoPedido.setId(resultado.getInt(1));
			}

			CamisaDAO camisaDao = new CamisaDAO();
			if (!novoPedido.getCamisas().isEmpty()) {
				camisaDao.inserirCamisa(novoPedido.getId(), novoPedido.getCamisas());
			}
			
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo pedido.");
			System.out.println("Erro: " + e.getMessage());
		}

		return novoPedido;
	}

	public List<Pedido> consultarComFiltros(PedidoSeletor seletor) {
		List<Pedido> pedido = new ArrayList<Pedido>();
		Connection conexao = Banco.getConnection();
		String sql = " select * from pedido ";
		CamisaDAO camisaDao = new CamisaDAO();

		if (seletor.temFiltro()) {
			sql = preencherFiltros(sql, seletor);
		}

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();

			while (resultado.next()) {
				Pedido p = new Pedido();
				p.setId(resultado.getInt("id"));
				p.setIdPessoa(resultado.getInt("id_Pessoa"));
				p.setSituacaoPedido(SituacaoPedido.getSituacaoEntregaVOPorValor(resultado.getInt("status_pedido")));
				p.setCamisas(camisaDao.cansultarCamisasDoPedido(resultado.getInt("id")));
				pedido.add(p);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar todos os pedidos. \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return pedido;
	}

	private String preencherFiltros(String sql, PedidoSeletor seletor) {

		boolean primeiro = true;
		if (seletor.getId() != null){
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}

			sql += " id LIKE '%" + seletor.getId() + "%'";
			primeiro = false;
		}
		
		if (seletor.getSituacaoPedido() != null) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}

			sql += " STATUS_PEDIDO LIKE '%" + seletor.getIdPessoa() + "%'";
			primeiro = false;
		}
		
		if (seletor.getIdPessoa() != null) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}

			sql += " id_pessoa like '%" + seletor.getIdPessoa() + "%'";
			primeiro = false;
		}
		
		
		
		return sql;
	}
	
	public int contarTotalRegistrosComFiltros(PedidoSeletor seletor) {
		int total = 0;
		Connection conexao = Banco.getConnection();
		String sql = " select count(*) from pedido ";
		
		if(seletor.temFiltro()) {
			sql = preencherFiltros(sql, seletor);
		}
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();
			
			if(resultado.next()) {
				total = resultado.getInt(1);
			}
		}catch (Exception e) {
			System.out.println("Erro contar o total de pedidos" 
					+ "\n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return total;
	}
	
	

	private Pedido montarPedidoComResultadoDoBanco(ResultSet resultado) throws SQLException {
	    Pedido pedidoBuscado = new Pedido();
	    CamisaDAO camisaDao = new CamisaDAO();
	    int statusPedido = resultado.getInt("status_pedido");
	    SituacaoPedido situacaoPedido = SituacaoPedido.getSituacaoEntregaVOPorValor(statusPedido);
	    pedidoBuscado.setId(resultado.getInt("id"));
	    pedidoBuscado.setSituacaoPedido(situacaoPedido);
	    pedidoBuscado.setIdPessoa(resultado.getInt("ID_PESSOA"));
	    pedidoBuscado.setCamisas(camisaDao.cansultarCamisasDoPedido(resultado.getInt("id")));
	    
	    return pedidoBuscado;
	}

	public boolean excluir(Integer id) {
		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM PEDIDO WHERE ID= " + id;
		Statement stmt = Banco.getStatement(conn);

		int quantidadeLinhasAfetadas = 0;
		try {
			quantidadeLinhasAfetadas = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir usuário.");
			System.out.println("Erro: " + e.getMessage());
		}

		boolean excluiu = quantidadeLinhasAfetadas > 0;

		return excluiu;
	}

	public ArrayList<Pedido> consultarTodos() {
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		Connection conexao = Banco.getConnection();
		String sql = " select * from pedido ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();
			
			while(resultado.next()) {
				Pedido pedidoBuscado = montarPedidoComResultadoDoBanco(resultado);
				pedidos.add(pedidoBuscado);
			}
			
		}catch (Exception e) {
			System.out.println("Erro ao buscar todos os pedidos. \n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return pedidos;

	}

	public boolean atualizar(Pedido pedido) {
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE PEDIDO SET STATUS_PEDIDO=? " + " WHERE ID = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		int registrosAlterados = 0;
		
		int situacaoPedidoValue = pedido.getSituacaoPedido().getValor();
		
		try {
			stmt.setInt(1, situacaoPedidoValue);
			stmt.setInt(6, pedido.getId());
			registrosAlterados = stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo Usuário.");
			System.out.println("Erro: " + e.getMessage());
		}

		return registrosAlterados > 0;
	}

}
