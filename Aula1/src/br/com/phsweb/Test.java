package br.com.phsweb;

import org.junit.Assert;

import br.com.phsweb.entidades.Aluno;
import br.com.phsweb.persistencia.AlunoDAO;

public class Test {

	@org.junit.Test
	public void selectAluno() {
		AlunoDAO dao = new AlunoDAO();
		Aluno a = dao.find(1L);
		Assert.assertEquals(a.getCPF(), "591.153.496-75");

	}

}
