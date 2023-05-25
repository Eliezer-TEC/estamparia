package model.bo;

import model.dao.ClienteDAO;
import model.exception.CpfJaUtilizadoException;
import model.vo.Cliente;

public class ClienteBO {
	private ClienteDAO dao = new ClienteDAO();

	public Cliente inserir(Cliente novoCliente) throws CpfJaUtilizadoException {
		if (dao.cpfJaUtilizado(novoCliente.getCpf())) {
			throw new CpfJaUtilizadoException("CPF informado já foi utilizado");
		}

		

//Caso CPF não utilizado -> salvar e devolver o cliente
		return dao.inserir(novoCliente);
	}



}
