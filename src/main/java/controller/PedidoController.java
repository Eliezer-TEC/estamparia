package controller;

import java.util.ArrayList;
import java.util.List;

import model.bo.PedidoBO;
import model.dao.PedidoDAO;
import model.gerador.GeradorPlanilha;
import model.seletor.PedidoSeletor;
import model.vo.Pedido;
import model.vo.Pessoa;

public class PedidoController {

	PedidoBO bo = new PedidoBO();

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

	
}
