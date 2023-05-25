package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.Cliente;

public class ClienteDAO {

	

	public Cliente inserir(Cliente novoCliente) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO CLIENTE(NOME, CPF, TELEFONE, EMAIL) "
				+ " VALUES (?,?,?,?) ";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		try {
			stmt.setString(1, novoCliente.getNome());
			stmt.setString(2, novoCliente.getCpf());
			stmt.setString(3, novoCliente.getTelefone());
			stmt.setString(4, novoCliente.getEmail());
			//stmt.setDate(5, java.sql.Date.valueOf(novoCliente.getDataNascimento()));
			stmt.execute();
			
			//Preencher o id gerado no banco no objeto
			ResultSet resultado = stmt.getGeneratedKeys();
			if(resultado.next()) {
				novoCliente.setId(resultado.getInt(1));
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo cliente.");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return novoCliente;
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
