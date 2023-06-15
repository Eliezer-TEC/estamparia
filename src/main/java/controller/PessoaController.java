package controller;

import model.bo.PessoaBO;

import model.exception.CampoInvalidoException;
import model.exception.CpfJaUtilizadoException;
import model.vo.Pessoa;

public class PessoaController {
	
	private PessoaBO bo = new PessoaBO();

	public Pessoa inserir(Pessoa novoUsuario)
	        throws CpfJaUtilizadoException, CampoInvalidoException {
	    validarCamposObrigatorios(novoUsuario);
	    return bo.inserir(novoUsuario);
	}

	private void validarCamposObrigatorios(Pessoa c) throws CampoInvalidoException {
	    String mensagemValidacao = "";

	    if (c.getNome() == null || c.getNome().trim().length() < 2) {
	        mensagemValidacao += "Nome inválido\n";
	    }
	    
	    if (c.getCpf() == null || c.getCpf().isEmpty()) {
	        mensagemValidacao += "Informe um CPF\n";
	    }

	    if (c.getEmail() == null || c.getEmail().trim().length() <= 2) {
	        mensagemValidacao += "Email inválido\n";
	    }

	    if (c.getSenha() == null || c.getSenha().trim().length() <= 2) {
	        mensagemValidacao += "Senha inválida\n";
	    }

	    if (!mensagemValidacao.isEmpty()) {
	        throw new CampoInvalidoException(mensagemValidacao);
	    }
	}
}
