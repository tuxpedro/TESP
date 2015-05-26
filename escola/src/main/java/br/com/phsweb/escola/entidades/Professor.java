package br.com.phsweb.escola.entidades;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_PROFESSOR")
@PrimaryKeyJoinColumn
@NamedQueries({ @NamedQuery(name = "Professor.findByName", query = "select p from Professor p where p.nome like :nome") })
public class Professor extends Pessoa {

	@Column(name = "SALARIO", nullable = false)
	@NotNull
	@DecimalMin("500.00")
	@DecimalMax("50000.00")
	private BigDecimal salario;

	public static Double BONUS = 0.1D;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sala")
	private List<Disciplina> disciplinas;

	public BigDecimal getSalario() {
		return salario;
	}

	public Professor() {
	}

	public Professor(Long id, String nome, String cpf) {
		super(id, nome, cpf);
	}

	public Professor(String nome, String cpf) {
		super(nome, cpf);
	}

	public Professor(Long id, String nome, String cpf, BigDecimal salario) {
		super(id, nome, cpf);
		this.salario = salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return super.toString() + "Professor [salario=" + salario + "]";
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

}
