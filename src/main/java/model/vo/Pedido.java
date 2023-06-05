package model.vo;

public class Pedido {
	private Integer id;
	private Integer IdPessoa;
	private Integer idCamisa;
	private SituacaoPedido situacaoPedido;

	public Pedido(Integer id, Integer idPessoa, Integer idCamisa, SituacaoPedido situacaoPedido) {
		super();
		this.id = id;
		IdPessoa = idPessoa;
		this.idCamisa = idCamisa;
		this.situacaoPedido = situacaoPedido;
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

	public Integer getIdPessoa() {
		return IdPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		IdPessoa = idPessoa;
	}

	public Integer getIdCamisa() {
		return idCamisa;
	}

	public void setIdCamisa(Integer idCamisa) {
		this.idCamisa = idCamisa;
	}

	public SituacaoPedido getSituacaoPedido() {
		return situacaoPedido;
	}

	public void setSituacaoPedido(SituacaoPedido situacaoPedido) {
		this.situacaoPedido = situacaoPedido;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", IdPessoa=" + IdPessoa + ", idCamisa=" + idCamisa + ", situacaoPedido="
				+ situacaoPedido + "]";
	}

}
