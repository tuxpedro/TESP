package br.com.phsweb.entidades;

public class Pessoa {

	private Long id;
	private String nome;
	private String cpf;

	public Pessoa(Long id, String nome, String cPF) {
		super();
		this.id = id;
		this.nome = nome;
		cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cPF) {
		cpf = cPF;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", CPF=" + cpf + "]";
	}
}