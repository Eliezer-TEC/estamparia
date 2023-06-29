package controller;

import java.util.ArrayList;
import java.util.List;

import model.bo.PessoaBO;
import model.exception.CampoInvalidoException;
import model.exception.CpfJaUtilizadoException;
import model.exception.EmailJaUtilizadoException;
import model.exception.UsuarioPossuiPedidosException;
import model.gerador.GeradorPlanilha;
import model.seletor.PessoaSeletor;
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
		
		
		if (c.getNome() == null || c.getNome().trim().length() < 2) {
			mensagemValidacao += "Nome inválido \n";
		}

		if (c.getCpf() == null) {
			mensagemValidacao += "Informe um cpf\n";
		}

		if (c.getEmail() == null) {
			mensagemValidacao += "Informe um email\n";
		}

		if (c.getSenha() == null) {
			mensagemValidacao += "Informe uma senha\n";
		}

		if (c.getSenha().trim().length() < 5) {
			mensagemValidacao += "A senha deve conter 5 dígitos\n";
		}

		if (!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}

	}

	public Pessoa consultarPorLoginSenha(String email, String senha) throws CampoInvalidoException {
		Pessoa usuarioConsultado = null;
		boolean valido = (email != null && !email.isEmpty()) && (senha != null && !senha.isEmpty());
		if (valido) {
			usuarioConsultado = bo.consultarPorLoginSenha(email, senha);
		} else {

			throw new CampoInvalidoException("Login ou senha inválidos!");

		}

		return usuarioConsultado;
	}

	public boolean consultarEmail(Pessoa pessoa) throws CampoInvalidoException {
		validarCamposObrigatorios(pessoa);

		return bo.verificarEmail(pessoa);

	}

	public String gerarPlanilha(ArrayList<Pessoa> pessoas, String caminhoEscolhido) throws CampoInvalidoException {

		if (pessoas == null || caminhoEscolhido == null || caminhoEscolhido.trim().isEmpty()) {
			throw new CampoInvalidoException("Preencha todos os campos");
		}

		GeradorPlanilha gerador = new GeradorPlanilha();
		return gerador.gerarPlanilhaClientes(pessoas, caminhoEscolhido);
	}

	public boolean excluir(Integer id) throws UsuarioPossuiPedidosException {
		return bo.excluir(id);

	}

	public Pessoa consultarPorId(int id) {
		return bo.consultarPorId(id);
	}

	public List<Pessoa> consultarTodos() {
		// TODO Auto-generated method stub
		return bo.consultarTodos();
	}

	public int contarTotalRegistrosComFiltros(PessoaSeletor seletor) {
		// TODO Auto-generated method stub
		return bo.contarTotalRegistrosComFiltros(seletor);
	}

	public List<Pessoa> consultarComFiltros(PessoaSeletor seletor) {
		// TODO Auto-generated method stub
		return bo.consultarComFiltros(seletor);
	}

	public boolean atualizar(Pessoa pessoa) throws CampoInvalidoException {
		validarCamposObrigatorios(pessoa);
		return bo.atualizar(pessoa);
		
	}

}
