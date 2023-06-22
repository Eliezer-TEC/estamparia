package model.bo;

import java.util.ArrayList;

import model.dao.PedidoDAO;
import model.seletor.PessoaSeletor;
import model.vo.Pedido;

public class PedidoBO {
	PedidoDAO dao = new PedidoDAO();
	
	public Pedido inserir(Pedido novoPedido){
		//validar array de camisas tem pelo menos 1 camisa
		//o pedido deve possuir pessoa associada
		return dao.inserir(novoPedido);
	}



}
