package br.com.unibh.escola.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn
@Table(name = "TB_PROFESSOR")
public class Professor extends Pessoa {
	
	@NotNull
	@DecimalMin("500.00")
	@DecimalMax("50000.00")
	@Column(name="SALARIO", nullable=false)
	private BigDecimal salario;
	
	public static double BONUS = 0.1D;

	public Professor() {
	}

	public Professor(Long id, String nome, String cpf, BigDecimal salario) {
		super(id, nome, cpf);
		this.setSalario(salario);
	}

	public Professor(Long id, String nome, String cpf) {
		super(id, nome, cpf);
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
