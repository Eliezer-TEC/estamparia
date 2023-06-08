package model.vo;

import java.sql.Blob;

public class Camisa {
	
	private Integer id;
	private String tamanho;
	private String cor;
	private Blob estampa;
	private Integer idPedido;
	
	public Camisa() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Camisa(Integer id, String tamanho, String cor, Blob estampa, Integer idPedido) {
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
	public Blob getEstampa() {
		return estampa;
	}
	public void setEstampa(Blob estampa) {
		this.estampa = estampa;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	
	
	
}