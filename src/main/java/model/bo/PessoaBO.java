package model.bo;

import model.dao.PessoaDAO;
import model.exception.CpfJaUtilizadoException;
import model.exception.EmailJaUtilizadoException;
import model.vo.Pessoa;

public class PessoaBO {
	private PessoaDAO dao = new PessoaDAO();

	public Pessoa inserir(Pessoa novoUsuario) throws CpfJaUtilizadoException, EmailJaUtilizadoException {
		if (dao.cpfJaUtilizado(novoUsuario.getCpf())) {
			throw new CpfJaUtilizadoException("CPF informado já foi utilizado");
		}
		if (dao.emailJaUtilizado(novoUsuario.getEmail())) {
			throw new EmailJaUtilizadoException("Email informado já foi utilizado");
		}
//Caso CPF não utilizado -> salvar e devolver o cliente
		return dao.inserir(novoUsuario);
	}

	public Pessoa realizarLoginBO(Pessoa usuario) {
	
		
		return dao.RealizarLoginDAO(usuario);
	}

	public Pessoa consultarPorLoginSenha(String login, String senha) {
		PessoaDAO dao = new PessoaDAO();
		Pessoa usuarioConsultado = dao.consultarPorLoginSenha(login, senha);  
		
		return usuarioConsultado;
	}

}
