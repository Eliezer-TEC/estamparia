package model.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.dao.CamisaDAO;
import model.dao.PedidoDAO;
import model.seletor.PedidoSeletor;
import model.seletor.PessoaSeletor;
import model.vo.Pedido;
import model.vo.Pessoa;

public class PedidoBO {
	PedidoDAO dao = new PedidoDAO();

	public Pedido inserir(Pedido novoPedido) {
		// validar array de camisas tem pelo menos 1 camisa
		// o pedido deve possuir pessoa associada
		LocalDate currentDate = LocalDate.now();
		novoPedido.setData(currentDate);
		return dao.inserir(novoPedido);
	}

	public List<Pedido> consultarComFiltros(PedidoSeletor seletor) {
		// TODO Auto-generated method stub
		return dao.consultarComFiltros(seletor);
		
	}

	public int contarTotalRegistrosComFiltros(PedidoSeletor seletor) {
		return dao.contarTotalRegistrosComFiltros(seletor);
	}

	public boolean excluir(Integer id) {
		CamisaDAO camisaDao = new CamisaDAO();
		camisaDao.excluirTodasCamisasDoPedido(id);
		return dao.excluir(id);
	}

	public ArrayList<Pedido> consultarTodos() {
		return dao.consultarTodos();
	}

	public boolean atualizar(Pedido pedido) {
		return dao.atualizar(pedido);
	}

}
