package model.seletor;

public class PessoaSeletor extends BaseSeletor {

	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private Boolean funcionario;

	public boolean temFiltro() {
		return (this.nome != null && this.nome.trim().length() > 0)
			|| (this.cpf != null && this.cpf.trim().length() > 0)
			|| (this.email != null && this.email.trim().length() > 0)
			|| (this.senha != null && this.senha.trim().length() > 0)
			|| (this.funcionario != null);
	}
	
	public PessoaSeletor() {
		super();
	}

	public PessoaSeletor(String nome, String cpf, String email, String senha, Boolean funcionario) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.funcionario = funcionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean isFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Boolean funcionario) {
		this.funcionario = funcionario;
	}
}
