package controller;

import model.bo.PessoaBO;
import model.exception.CampoInvalidoException;
import model.exception.CpfJaUtilizadoException;
import model.exception.EmailJaUtilizadoException;
import model.vo.Pessoa;

public class PessoaController {
	
	private PessoaBO bo = new PessoaBO();



	
	public Pessoa inserir(Pessoa novoUsuario)
			throws CpfJaUtilizadoException, CampoInvalidoException, EmailJaUtilizadoException {

	    validarCamposObrigatorios(novoUsuario);
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
		
		if(c.getEmail() == null ) {
			mensagemValidacao += "Informe um email\n";
		}
		
		if(c.getSenha() == null ) {
			mensagemValidacao += "Informe uma senha\n";
		}
		
		if(c.getSenha().trim().length() < 5 ) {
			mensagemValidacao += "A senha deve conter 5 dígitos\n";
		}
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}

	}

	public Pessoa consultarPorLoginSenha(String email, String senha)throws CampoInvalidoException {
		Pessoa usuarioConsultado = null;
		boolean valido = (email != null && !email.isEmpty())
				&& (senha != null && !senha.isEmpty());
		if (valido) {
			usuarioConsultado = bo.consultarPorLoginSenha(email, senha);
		} else {

			throw new CampoInvalidoException("Login ou senha inválidos!");

		}

		return usuarioConsultado;
	}
	
}
