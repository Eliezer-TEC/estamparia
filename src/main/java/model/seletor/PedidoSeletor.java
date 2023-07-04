package model.seletor;

import java.time.LocalDate;
import java.util.ArrayList;

import model.vo.Camisa;
import model.vo.SituacaoPedido;

public class PedidoSeletor extends BaseSeletor {

	private Integer id;
	private Integer idPessoa;
	private ArrayList<Camisa> camisas;
	private SituacaoPedido situacaoPedido;
	private LocalDate dataInicial;
	private LocalDate dataFinal;

	@Override
	public boolean temFiltro() {
		// TODO Auto-generated method stub
		return this.id != null || this.idPessoa != null
				|| this.camisas != null || this.situacaoPedido != null
				|| this.dataInicial != null || this.dataFinal != null;
	}

	public PedidoSeletor(Integer id, Integer idPessoa, ArrayList<Camisa> camisas, SituacaoPedido situacaoPedido,
			LocalDate dataInicial, LocalDate dataFinal) {
		super();
		this.id = id;
		this.idPessoa = idPessoa;
		this.camisas = camisas;
		this.situacaoPedido = situacaoPedido;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

	public PedidoSeletor() {
		super();
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

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}
	

	


	

}
