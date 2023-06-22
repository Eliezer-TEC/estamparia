package controller;

import model.dao.PedidoDAO;
import model.vo.Pedido;

public class PedidoController {

	public void inserir(Pedido pedido) {
		if (pedido.getCamisas().size() > 0) {
			PedidoDAO dao = new PedidoDAO();
			dao.inserir(pedido);
		}
	}
}
