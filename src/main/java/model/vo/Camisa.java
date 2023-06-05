package model.vo;

import java.sql.Blob;

public class Camisa {
	private Integer id;
	private String tamanho;
	private String cor;
	private Blob estampa;

	public Camisa() {
		super();
	}

	public Camisa(Integer id, String tamanho, String cor, Blob estampa) {
		super();
		this.id = id;
		this.tamanho = tamanho;
		this.cor = cor;
		this.estampa = estampa;
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

	@Override
	public String toString() {
		return "Camisa [id=" + id + ", tamanho=" + tamanho + ", cor=" + cor + ", estampa=" + estampa + "]";
	}

}
