package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.seletor.PessoaSeletor;
import model.vo.Pessoa;

public class PessoaDAO {

	public Pessoa inserir(Pessoa novoUsuario) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO PESSOA(NOME, CPF, EMAIL, SENHA, FUNCIONARIO) " + " VALUES (?,?,?,?,?) ";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		try {
			stmt.setString(1, novoUsuario.getNome());
			stmt.setString(2, novoUsuario.getCpf());
			stmt.setString(3, novoUsuario.getEmail());
			stmt.setString(4, novoUsuario.getSenha());
			stmt.setBoolean(5, novoUsuario.isFuncionario());

			stmt.execute();

			// Preencher o id gerado no banco no objeto
			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				novoUsuario.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo Usuário.");
			System.out.println("Erro: " + e.getMessage());
		}

		return novoUsuario;
	}

	public boolean atualizar(Pessoa pessoa) {
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE PESSOA SET NOME=?, CPF=?, EMAIL=?, SENHA=?, FUNCIONARIO=? " + " WHERE ID = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		int registrosAlterados = 0;
		try {
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getCpf());
			stmt.setString(3, pessoa.getEmail());
			stmt.setString(4, pessoa.getSenha());
			stmt.setBoolean(5, pessoa.isFuncionario());
			registrosAlterados = stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo Usuário.");
			System.out.println("Erro: " + e.getMessage());
		}

		return registrosAlterados > 0;
	}

	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM PESSOA WHERE ID= " + id;
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

	public List<Pessoa> consultarComFiltros(PessoaSeletor seletor) {
		List<Pessoa> pessoa = new ArrayList<Pessoa>();
		Connection conexao = Banco.getConnection();
		String sql = " select * from pessoa ";

		if (seletor.temFiltro()) {
			sql = preencherFiltros(sql, seletor);
		}

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();

			while (resultado.next()) {
				Pessoa pessoaBuscado = montarClienteComResultadoDoBanco(resultado);
				pessoa.add(pessoaBuscado);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar todos os clientes. \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return pessoa;
	}

	private String preencherFiltros(String sql, PessoaSeletor seletor) {

		boolean primeiro = true;
		if (seletor.getNome() != null && !seletor.getNome().trim().isEmpty()) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}

			sql += " nome LIKE '%" + seletor.getNome() + "%'";
			primeiro = false;
		}

		if (seletor.getCpf() != null && !seletor.getCpf().trim().isEmpty()) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}
			sql += " cpf LIKE '%" + seletor.getCpf() + "%'";
			primeiro = false;
		}

		if (seletor.getCpf() != null && !seletor.getCpf().trim().isEmpty()) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}
			sql += " cpf LIKE '%" + seletor.getCpf() + "%'";
			primeiro = false;
		}

		if (seletor.getEmail() != null && !seletor.getEmail().trim().isEmpty()) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}
			sql += " email LIKE '%" + seletor.getEmail() + "%'";
			primeiro = false;
		}

		if (seletor.getSenha() != null && !seletor.getSenha().trim().isEmpty()) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}
			sql += " senha LIKE '%" + seletor.getSenha() + "%'";
			primeiro = false;
		}

		if (seletor.isFuncionario() != null) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}
			sql += " funcionario LIKE '%" + seletor.isFuncionario() + "%'";
			primeiro = false;
		}
		return sql;
	}

	private Pessoa montarClienteComResultadoDoBanco(ResultSet resultado) throws SQLException {
		Pessoa pessoaBuscada = new Pessoa();
		pessoaBuscada.setId(resultado.getInt("id"));
		pessoaBuscada.setNome(resultado.getString("nome"));
		pessoaBuscada.setCpf(resultado.getString("cpf"));
		pessoaBuscada.setEmail(resultado.getString("email"));
		pessoaBuscada.setSenha(resultado.getString("senha"));
		pessoaBuscada.setFuncionario(resultado.getBoolean("funcionario"));

		return pessoaBuscada;
	}

	public boolean cpfJaUtilizado(String cpfBuscado) {
		boolean cpfJaUtilizado = false;
		Connection conexao = Banco.getConnection();
		String sql = " select count(*) from pessoa " + " where cpf = ? ";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setString(1, cpfBuscado);
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				cpfJaUtilizado = resultado.getInt(1) > 0;
			}
		} catch (Exception e) {
			System.out.println("Erro ao verificar uso do CPF " + cpfBuscado + "\n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return cpfJaUtilizado;
	}

	public boolean emailJaUtilizado(String emailBuscado) {
		boolean emailJaUtilizado = false;
		Connection conexao = Banco.getConnection();
		String sql = " select count(*) from pessoa " + " where email = ? ";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setString(1, emailBuscado);
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				emailJaUtilizado = resultado.getInt(1) > 0;
			}
		} catch (Exception e) {
			System.out.println("Erro ao verificar uso do CPF " + emailJaUtilizado + "\n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return emailJaUtilizado;
	}
}
