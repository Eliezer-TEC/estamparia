package controller;

import java.util.ArrayList;
import java.util.List;

import model.bo.PedidoBO;
import model.dao.PedidoDAO;
import model.dao.PessoaDAO;
import model.exception.CampoInvalidoException;
import model.gerador.GeradorPlanilha;
import model.seletor.PedidoSeletor;
import model.vo.Pedido;
import model.vo.Pessoa;

public class PedidoController {

	PedidoBO bo = new PedidoBO();
	PessoaDAO pessoaDao = new PessoaDAO();

	public void inserir(Pedido pedido) {
		if (pedido.getCamisas().size() > 0) {
			PedidoDAO dao = new PedidoDAO();
			dao.inserir(pedido);
		}
		
	}
	
	public List<Pedido> consultarComFiltros(PedidoSeletor seletor) {
		// TODO Auto-generated method stub
		return bo.consultarComFiltros(seletor);
	}
	
	public Pessoa consultarCliente (int idCliente) {
		return pessoaDao.consultarPorId(idCliente);
	}

	public int contarTotalRegistrosComFiltros(PedidoSeletor seletor) {
		return bo.contarTotalRegistrosComFiltros(seletor);
	}

	public String gerarPlanilha(ArrayList<Pedido> pedidos, String caminhoEscolhido) throws CampoInvalidoException {

		if (pedidos == null || caminhoEscolhido == null || caminhoEscolhido.trim().isEmpty()) {
			throw new CampoInvalidoException("Preencha todos os campos");
		}

		GeradorPlanilha gerador = new GeradorPlanilha();
		return gerador.gerarPlanilhaPedido(pedidos, caminhoEscolhido);
	}

	public boolean excluir(Integer id) {
		return bo.excluir(id);
		
	}

	public ArrayList<Pedido> consultarTodos() {
		return bo.consultarTodos();
	}

	public boolean atualizar(Pedido pedido) {
		return bo.atualizar(pedido);
	}

	
}
