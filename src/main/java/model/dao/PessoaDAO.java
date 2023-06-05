package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.vo.Pessoa;

public class PessoaDAO {

	

	public Pessoa inserir(Pessoa novoUsuario) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO PESSOA(NOME, CPF, EMAIL, SENHA, FUNCIOARIO) "
				+ " VALUES (?,?,?,?) ";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		try {
			stmt.setString(1, novoUsuario.getNome());
			stmt.setString(2, novoUsuario.getCpf());
			stmt.setString(3, novoUsuario.getEmail());
			stmt.setString(4, novoUsuario.getSenha());
			stmt.setBoolean(5, novoUsuario.isFuncionario());

			stmt.execute();
			
			//Preencher o id gerado no banco no objeto
			ResultSet resultado = stmt.getGeneratedKeys();
			if(resultado.next()) {
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
		String sql = " UPDATE PESSOA SET NOME=?, CPF=?, EMAIL=?, SENHA=?, FUNCIONARIO=? "
				+ " WHERE ID = ?";
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
	
	
	public Pessoa consultarPorTipo(int id) {
		Pessoa clienteBuscado = null;
		Connection conexao = Banco.getConnection();
		String sql = " select * from pessoa "
				   + " where funcioNario = ? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			
			if(resultado.next()) {
				clienteBuscado = montarClienteComResultadoDoBanco(resultado);
			}
			
		}catch (Exception e) {
			System.out.println("Erro ao buscar cliente com id: " + id 
					+ "\n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return clienteBuscado;
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
		String sql = " select count(*) from cliente "
				   + " where cpf = ? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setString(1, cpfBuscado);
			ResultSet resultado = query.executeQuery();
			
			if(resultado.next()) {
				cpfJaUtilizado = resultado.getInt(1) > 0;
			}
		}catch (Exception e) {
			System.out.println("Erro ao verificar uso do CPF " + cpfBuscado 
					+ "\n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return cpfJaUtilizado;
	}
}
