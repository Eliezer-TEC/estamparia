package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.Camisa;
import model.vo.Pedido;

public class PedidoDAO {
	public Pedido inserir(Pedido novoPedido) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO PEDIDO(STATUS_PEDIDO, ID_PESSOA) " + " VALUES (?,?) ";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		try {
			stmt.setInt(1, novoPedido.getSituacaoPedido().getValor());
			stmt.setInt(2, novoPedido.getPessoa().getId());
			
			stmt.execute();

			// Preencher o id gerado no banco no objeto
			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				novoPedido.setId(resultado.getInt(1));
			}

			CamisaDAO camisaDao = new CamisaDAO();
			for(Camisa c: novoPedido.getCamisas()) {
				c.setIdPedido(novoPedido.getId());
				camisaDao.inserir(c);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo pedido.");
			System.out.println("Erro: " + e.getMessage());
		}

		return novoPedido;
	}
	
		
}
