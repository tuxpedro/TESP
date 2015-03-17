package br.com.phsweb;

import java.util.List;

import org.junit.Assert;

import br.com.phsweb.entidades.Aluno;
import br.com.phsweb.persistencia.AlunoDAO;

public class Test {

	@org.junit.Test
	public void selectAluno() {
		AlunoDAO dao = new AlunoDAO();
		Aluno a = dao.find(1L);
		Assert.assertEquals(a.getCPF(), "591.153.496-75");

		Aluno a1 = dao.find(2L);
		Assert.assertEquals(a1.getCPF(), "186.253.437-32");

	}

	@org.junit.Test
	public void selectListaAlunos() {
		AlunoDAO dao = new AlunoDAO();
		List<Aluno> alunos = dao.findAll();
		for (Aluno aluno : alunos) {
			System.out.println(aluno.getId() + "\t" + aluno.getNome());
		}
	}

}
