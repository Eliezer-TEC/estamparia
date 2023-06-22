package controller;

import java.util.ArrayList;

import model.bo.PedidoBO;
import model.dao.PedidoDAO;
import model.gerador.GeradorPlanilha;
import model.seletor.PessoaSeletor;
import model.vo.Pedido;

public class PedidoController {

	PedidoBO bo = new PedidoBO();

	public void inserir(Pedido pedido) {
		if (pedido.getCamisas().size() > 0) {
			PedidoDAO dao = new PedidoDAO();
			dao.inserir(pedido);
		}
	}

	
}
