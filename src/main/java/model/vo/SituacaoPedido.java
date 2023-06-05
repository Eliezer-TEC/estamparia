package model.vo;

public enum SituacaoPedido {
	PEDIDO_REALIZADO (1),
	PREPARANDO_PEDIDO (2),
	EM_ROTA_DE_ENTREGA (3),
	PEDIDO_ENTREGUE (4);
	
private int valor;
	
	private SituacaoPedido(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}
	public static SituacaoPedido getSituacaoEntregaVOPorValor(int valor) {
		SituacaoPedido situacaoPedido = null;
		for (SituacaoPedido elemento : SituacaoPedido.values()) {
			if(elemento.getValor() == valor) {
				situacaoPedido = elemento;
			}
		}
		return situacaoPedido;
	}
}
