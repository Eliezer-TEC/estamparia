package model.vo;

import java.util.ArrayList;

public class Pedido {
	
	private Integer id;
	private Pessoa pessoa;
	private ArrayList<Camisa> camisas;
	private SituacaoPedido situacaoPedido;
	
	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pedido(Integer id, Pessoa pessoa, ArrayList<Camisa> camisas, SituacaoPedido situacaoPedido) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.camisas = camisas;
		this.situacaoPedido = situacaoPedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public ArrayList<Camisa> getCamisas() {
		return camisas;
	}

	public void setCamisas(ArrayList<Camisa> camisas) {
		this.camisas = camisas;
	}

	public SituacaoPedido getSituacaoPedido() {
		return situacaoPedido;
	}

	public void setSituacaoPedido(SituacaoPedido situacaoPedido) {
		this.situacaoPedido = situacaoPedido;
	}
	
}
