package br.com.phsweb.aula1;

import java.math.BigDecimal;

public class Professor extends Pessoa {
	private BigDecimal salario;

	public Professor(String nome, String cpf, BigDecimal salario) {
		super(nome, cpf);
		this.setSalario(salario);
	}

	public Professor(String nome, String cpf) {
		super(nome, cpf);
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return super.toString() + "Professor [salario=" + salario + "] \n";
	}

}
