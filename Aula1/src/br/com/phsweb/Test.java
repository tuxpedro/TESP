package br.com.phsweb;

import java.math.BigDecimal;
import java.util.Date;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import br.com.phsweb.entidades.Aluno;
import br.com.phsweb.entidades.Professor;
import br.com.phsweb.persistencia.AlunoDAO;
import br.com.phsweb.persistencia.JDBCTestes;
import br.com.phsweb.persistencia.ProfessorDAO;

public class Test {

	@Before
	public void preparaBanco() {
		AlunoDAO dao = new AlunoDAO();

		Aluno aluno1 = new Aluno(1L, 11316704L, "Pedro Herique da Silva",
				"100.354.836-95", new Date());
		Aluno aluno2 = new Aluno(2L, 11316705L, "Tim dos Bauretes",
				"110.364.996-05", new Date());
		Aluno aluno3 = new Aluno(3L, 11316706L, "Jonh Baurete",
				"200.354.800-36", new Date());

		dao.insert(aluno1);
		dao.insert(aluno2);
		dao.insert(aluno3);

		ProfessorDAO profdao = new ProfessorDAO();
		Professor prof1 = new Professor(1L, "Stiven baurete", "268.398.210-05",
				new BigDecimal(10_000));
		Professor prof2 = new Professor(2L, "Stiven baurete", "268.398.210-05",
				new BigDecimal(10_000));
		Professor prof3 = new Professor(3L, "Stiven baurete", "268.398.210-05",
				new BigDecimal(10_000));
		profdao.insert(prof1);
		profdao.insert(prof2);
		profdao.insert(prof3);

	}

	@org.junit.Test
	public void testeSelect() {
		AlunoDAO dao = new AlunoDAO();
		Aluno a = dao.find(1L);
		Assert.assertEquals(a.getCPF(), "100.354.836-95");

		Aluno a1 = dao.find(2L);
		Assert.assertEquals(a1.getCPF(), "110.364.996-05");

		Aluno a3 = dao.findByCpf("200.354.800-36");
		Assert.assertEquals(a3.getCPF(), "200.354.800-36");

		ProfessorDAO profDao = new ProfessorDAO();

		Professor prof = profDao.find(1L);
		Assert.assertEquals(prof.getCPF(), "268.398.210-05");

	}

	@org.junit.Test
	public void TesteInsert() {
		AlunoDAO dao = new AlunoDAO();
		Aluno aluno = new Aluno(4L, 11316707L, "Maria Baurete",
				"199.304.036-15", new Date());
		dao.insert(aluno);
		dao.findByCpf("199.304.036-15");
		Assert.assertEquals(aluno.getCPF(), "199.304.036-15");

		ProfessorDAO proDao = new ProfessorDAO();
		Professor prof = new Professor(4L, "Wilsson Baurete", "223.669.895-65",
				new BigDecimal(9_000));
		proDao.insert(prof);
		Professor p2 = proDao.find(4L);
		Assert.assertEquals(p2.getNome(), "Wilsson Baurete");

	}

	@org.junit.Test
	public void TesteUpdate() {
		AlunoDAO dao = new AlunoDAO();
		Aluno aluno = dao.findByCpf("100.354.836-95");
		aluno.setNome("Pedro H. Silva");
		dao.update(aluno);
		aluno = dao.findByCpf("100.354.836-95");
		Assert.assertEquals(aluno.getCPF(), "100.354.836-95");

	}

	@org.junit.Test
	public void testeDelete() {
		AlunoDAO dao = new AlunoDAO();
		Aluno aluno = dao.find(21L);
		dao.delete(aluno);
		aluno = dao.find(21L);
		Assert.assertNull(aluno);
	}

	@After
	public void limpabanco() {
		JDBCTestes.limpaBanco("tb_aluno");
		JDBCTestes.limpaBanco("tb_professor");
	}

}
