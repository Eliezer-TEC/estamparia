package controller;

import model.bo.PessoaBO;

import model.exception.CampoInvalidoException;
import model.exception.CpfJaUtilizadoException;
import model.vo.Pessoa;

public class ClienteController {
	
	private PessoaBO bo = new PessoaBO();
	
	public Pessoa inserir(Pessoa novoUsuario)
			throws CpfJaUtilizadoException, CampoInvalidoException {

		this.validarCamposObrigatorios(novoUsuario);
		return bo.inserir(novoUsuario);
	}

	private void validarCamposObrigatorios(Pessoa c) throws CampoInvalidoException {
		String mensagemValidacao = "";
		
		if(c.getNome() == null || c.getNome().trim().length() < 2) {
			mensagemValidacao += "Nome inválido \n";
		}
		
		if(c.getCpf() == null ) {
			mensagemValidacao += "Informe um cpf\n";
		}		
		
	
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}
}
