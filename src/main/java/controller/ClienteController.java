package controller;

import model.bo.ClienteBO;
import model.exception.CampoInvalidoException;
import model.exception.CpfJaUtilizadoException;
import model.vo.Cliente;

public class ClienteController {
	
	private ClienteBO bo = new ClienteBO();
	
	public Cliente inserir(Cliente novoCliente)
			throws CpfJaUtilizadoException, CampoInvalidoException {

		this.validarCamposObrigatorios(novoCliente);
		return bo.inserir(novoCliente);
	}

	private void validarCamposObrigatorios(Cliente c) throws CampoInvalidoException {
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
