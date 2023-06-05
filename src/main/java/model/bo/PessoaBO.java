package model.bo;

import model.dao.PessoaDAO;
import model.exception.CpfJaUtilizadoException;
import model.vo.Pessoa;

public class PessoaBO {
	private PessoaDAO dao = new PessoaDAO();

	public Pessoa inserir(Pessoa novoUsuario) throws CpfJaUtilizadoException {
		if (dao.cpfJaUtilizado(novoUsuario.getCpf())) {
			throw new CpfJaUtilizadoException("CPF informado já foi utilizado");
		}

//Caso CPF não utilizado -> salvar e devolver o cliente
		return dao.inserir(novoUsuario);
	}

}
