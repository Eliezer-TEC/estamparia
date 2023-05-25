package model.vo;

public class Funcionario {
	private Integer id;
	private String nome;
	private String cargo;
	private String cpf;
	private String telefone;

	public Funcionario(Integer id, String nome, String cargo, String cpf, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.cargo = cargo;
		this.cpf = cpf;
		this.telefone = telefone;
	}

	public Funcionario() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", cargo=" + cargo + ", cpf=" + cpf + ", telefone="
				+ telefone + "]";
	}

}
