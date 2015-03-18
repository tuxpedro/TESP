package br.com.phsweb;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import com.mysql.jdbc.RowData;

import br.com.phsweb.entidades.Aluno;
import br.com.phsweb.entidades.Professor;
import br.com.phsweb.persistencia.AlunoDAO;
import br.com.phsweb.persistencia.JDBCTestes;
import br.com.phsweb.persistencia.ProfessorDAO;

public class Test {

	@Before
	public void preparaBanco() {
		// prepapara tb_aluno
		AlunoDAO dao = new AlunoDAO();
		Aluno aluno1 = new Aluno(1L, 11316704L, "Pedro Herique da Silva",
				"100.354.836-95", new Date());
		Aluno aluno2 = new Aluno(2L, 11316705L, "Tim dos Bauretes",
				"110.364.996-05", new Date());
		Aluno aluno3 = new Aluno(3L, 11316706L, "Jonh Baurete",
				"200.354.800-36", new Date());
		// insert aluno
		dao.insert(aluno1);
		dao.insert(aluno2);
		dao.insert(aluno3);

		// prepara tb_professor
		ProfessorDAO profdao = new ProfessorDAO();
		Professor prof1 = new Professor(1L, "Carlos Mota baurete",
				"100.308.280-15", new BigDecimal(10_000));
		Professor prof2 = new Professor(2L, "Davis Canabis baurete",
				"368.758.210-76", new BigDecimal(10_000));
		Professor prof3 = new Professor(3L, "João Vinis baurete",
				"590.228.200-95", new BigDecimal(10_000));

		// insert tb_professor
		profdao.insert(prof1);
		profdao.insert(prof2);
		profdao.insert(prof3);

	}

	@After
	public void limpabanco() {
		// limpa o banco
		JDBCTestes.limpaBanco("tb_aluno");
		JDBCTestes.limpaBanco("tb_professor");
	}

	@org.junit.Test
	public void testeSelect() {
		// teste método find() da classe AlunoDAO
		AlunoDAO dao = new AlunoDAO();

		Aluno a = dao.find(1L);
		Assert.assertEquals(a.getCPF(), "100.354.836-95");

		Aluno a1 = dao.find(2L);
		Assert.assertEquals(a1.getCPF(), "110.364.996-05");

		Aluno a3 = dao.findByCpf("200.354.800-36");
		Assert.assertEquals(a3.getCPF(), "200.354.800-36");

		// teste método find() da classe ProfessorDAO
		ProfessorDAO profDao = new ProfessorDAO();

		Professor prof = profDao.find(1L);
		Assert.assertEquals(prof.getCPF(), "100.308.280-15");

	}

	@org.junit.Test
	public void TesteInsert() {
		// teste do método insert() da classe AlunoDAO
		AlunoDAO dao = new AlunoDAO();

		Aluno aluno = new Aluno(4L, 11316707L, "Maria Baurete",
				"199.304.036-15", new Date());
		dao.insert(aluno);
		dao.findByCpf("199.304.036-15");
		Assert.assertEquals(aluno.getCPF(), "199.304.036-15");

		// teste do método insert() da classe ProfessorDAO
		ProfessorDAO proDao = new ProfessorDAO();

		Professor prof = new Professor(4L, "Wilsson Baurete", "223.669.895-65",
				new BigDecimal(9_000));
		proDao.insert(prof);
		Professor p2 = proDao.find(4L);
		Assert.assertEquals(p2.getNome(), "Wilsson Baurete");

	}

	@org.junit.Test
	public void TesteUpdate() {
		// Teste do método update() da classe AlunoDAO
		AlunoDAO dao = new AlunoDAO();
		Aluno aluno = dao.findByCpf("100.354.836-95");
		aluno.setNome("Pedro H. Silva");
		dao.update(aluno);
		aluno = dao.findByCpf("100.354.836-95");
		Assert.assertEquals(aluno.getNome(), "Pedro H. Silva");

		// Teste do método update() da classe ProfessorDAO
		ProfessorDAO profDao = new ProfessorDAO();
		Professor prof = profDao.findByCpf("590.228.200-95");
		prof.setNome("João Vinis baurete Calabresa");
		profDao.update(prof);
		prof = profDao.findByCpf("590.228.200-95");
		Assert.assertEquals(prof.getNome(), "João Vinis baurete Calabresa");

	}

	@org.junit.Test
	public void testeDelete() {
		// Teste do método delete() da classe AlunoDAO
		AlunoDAO dao = new AlunoDAO();
		Aluno aluno = dao.findByCpf("100.354.836-95");
		dao.delete(aluno);
		aluno = dao.findByCpf("100.354.836-95");
		Assert.assertNull(aluno);

		// Teste do método delete() da classe ProfessorDAO
		ProfessorDAO profDao = new ProfessorDAO();
		Professor prof = profDao.findByCpf("590.228.200-95");
		profDao.delete(prof);
		prof = profDao.findByCpf("590.228.200-95");
		Assert.assertNull(prof);
	}

	@org.junit.Test
	public void testeFindAll() {
		// Teste do método findAll() da classe AlunoDAO
		AlunoDAO dao = new AlunoDAO();
		List<Aluno> alunos = dao.findAll();
		Assert.assertEquals(3, alunos.size());

		// Teste do método findAll() da classe ProfessorDAO
		ProfessorDAO profDao = new ProfessorDAO();
		List<Professor> professores = profDao.findAll();
		Assert.assertEquals(3, professores.size());

	}

}
