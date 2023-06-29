package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.PedidoDAO;
import model.dao.PessoaDAO;
import model.exception.CampoInvalidoException;
import model.exception.CpfJaUtilizadoException;
import model.exception.EmailJaUtilizadoException;
import model.exception.UsuarioPossuiPedidosException;
import model.seletor.PessoaSeletor;
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


	public Pessoa consultarPorLoginSenha(String email, String senha) throws CampoInvalidoException {
		PessoaDAO dao = new PessoaDAO();
		Pessoa usuarioConsultado = dao.consultarPorLoginSenha(email, senha);  
		
		return usuarioConsultado;
	}


	public boolean verificarEmail(Pessoa pessoa) {

		return dao.emailJaUtilizado(pessoa.getEmail());
		// TODO Auto-generated method stub
		
	}


	public boolean excluir(Integer id) throws UsuarioPossuiPedidosException{
		PedidoDAO pedidoDao = new PedidoDAO();
		if(pedidoDao.cansultarPedidosDoUsuario(id).size() > 0) {
			throw new UsuarioPossuiPedidosException("Usuário não pode ser excluido, pois possui pedidos.");
		}else {
			return dao.excluir(id);
		}
		
	}


	public List<Pessoa> consultarTodos() {
		// TODO Auto-generated method stub
		return dao.consultarTodos();
	}


	public Pessoa consultarPorId(int id) {
		// TODO Auto-generated method stub
		return dao.consultarPorId(id);	}


	public int contarTotalRegistrosComFiltros(PessoaSeletor seletor) {
		return dao.contarTotalRegistrosComFiltros(seletor);
	}


	public List<Pessoa> consultarComFiltros(PessoaSeletor seletor) {
		return dao.consultarComFiltros(seletor);
	}


	public boolean atualizar(Pessoa pessoa) {
		
		return dao.atualizar(pessoa);
	}

}
