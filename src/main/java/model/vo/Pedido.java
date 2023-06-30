package model.vo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {

	private Integer id;
	private Integer idPessoa;
	private ArrayList<Camisa> camisas;
	private SituacaoPedido situacaoPedido;
	private LocalDate data;


	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pedido(Integer id, Integer idPessoa, ArrayList<Camisa> camisas, SituacaoPedido situacaoPedido,
			LocalDate data) {
		super();
		this.id = id;
		this.idPessoa = idPessoa;
		this.camisas = camisas;
		this.situacaoPedido = situacaoPedido;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
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
