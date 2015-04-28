package br.com.unibh.escola.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author 11316704
 *
 */

@Entity
@PrimaryKeyJoinColumn
@Table(name = "TB_ALUNO", uniqueConstraints = @UniqueConstraint(columnNames = "matricula"))
@NamedQueries({ @NamedQuery(name = "Aluno.findByName", query = "select a from Aluno a where a.nome like :nome") })
public class Aluno extends Pessoa {

	@Column(name = "MATRICULA", unique = true, nullable = false)
	@NotBlank
	private Long matricula;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_ANIVERSARIO", nullable = false, columnDefinition = "DATE")
	private Date dataAniversario;

	public Aluno() {
	}

	public Aluno(Long id, Long matricula, String nome, String cpf,
			Date dataAniversario) {
		super(id, nome, cpf);
		this.matricula = matricula;
		this.dataAniversario = dataAniversario;
	}

	public Aluno(Long id, Long matricula, String nome, String cpf) {
		super(id, nome, cpf);
		this.matricula = matricula;
	}

	public Aluno(Long id, Long matricula, String nome) {
		super(id, nome, null);
		this.matricula = matricula;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public Date getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(Date dataAniversario) {
		this.dataAniversario = dataAniversario;
	}

	@Override
	public String toString() {

		return super.toString() + "Aluno [matricula=" + matricula
				+ ", dataAniversario=" + dataAniversario + "] \n";
	}

	public static boolean verificaMatricula(String matricula) {
		if (matricula == null) {
			return false;
		} else if (matricula.trim().equals("")) {
			return false;
		} else if (matricula.length() != 8) {
			return false;
		}
		return true;
	}

}
