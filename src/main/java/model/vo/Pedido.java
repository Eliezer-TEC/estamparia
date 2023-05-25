package model.vo;

public class Pedido {
	private Integer id;
	private Integer idCliente;
	private Integer idProduto;

	public Pedido(Integer id, Integer idCliente, Integer idProduto) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.idProduto = idProduto;
	}

	public Pedido() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", idCliente=" + idCliente + ", idProduto=" + idProduto + "]";
	}

}
