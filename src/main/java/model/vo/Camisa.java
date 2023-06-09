package model.vo;

import java.sql.Blob;

public class Camisa {

	private Integer id;
	private String tamanho;
	private String cor;
	private byte[] estampa; //TODO testar com byte[]
	private Integer idPedido;
	private String nomeArquivo;

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Camisa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Camisa(Integer id, String tamanho, String cor, byte[] estampa, Integer idPedido) {
		super();
		this.id = id;
		this.tamanho = tamanho;
		this.cor = cor;
		this.estampa = estampa;
		this.idPedido = idPedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public byte[] getEstampa() {
		return estampa;
	}

	public void setEstampa(byte[] bs) {
		this.estampa = bs;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	

}