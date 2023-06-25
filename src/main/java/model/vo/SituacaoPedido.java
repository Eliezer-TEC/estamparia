package model.vo;

public enum SituacaoPedido {
	PEDIDO_REALIZADO (1),
	PREPARANDO_PEDIDO (2),
	PEDIDO_ENTREGUE (3);
	
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
