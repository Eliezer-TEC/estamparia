package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.seletor.PedidoSeletor;
import model.seletor.PessoaSeletor;
import model.vo.Camisa;
import model.vo.Pedido;
import model.vo.Pessoa;
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

		if (seletor.temFiltro()) {
			sql = preencherFiltros(sql, seletor);
		}

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();

			while (resultado.next()) {
				Pedido p = new Pedido();
				p.setId(resultado.getInt("id"));
				p.setIdPessoa(resultado.getInt("idPessoa"));
				p.setSituacaoPedido(SituacaoPedido.getSituacaoEntregaVOPorValor(resultado.getInt("SituacaoPedido")));
				p.setCamisas(null);
				pedido.add(p);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar todos os clientes. \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return pedido;
	}

	private String preencherFiltros(String sql, PedidoSeletor seletor) {

		if (seletor.getId() != null){
			
		}
		
		if (seletor.getIdPessoa() != null) {
			
		}
		
		if(!seletor.getCamisas().isEmpty()) {
			
		}
		
		
		return sql;
	}

	private Pedido montarPedidoComResultadoDoBanco(ResultSet resultado) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
