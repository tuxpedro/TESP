package br.com.phsweb.aula1;

import java.math.BigDecimal;
import java.util.Date;

import br.com.phsweb.entidades.Aluno;
import br.com.phsweb.entidades.Professor;

public class OlaAluno {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Professor p1 = new Professor(new Long(1), "Pedro", "90090090056", new BigDecimal(
				10_000));
		Professor p2 = new Professor(2L, "Carlos", "19990000909", new BigDecimal(
				18_000));
		Professor p3 = new Professor(3L, "Jonas", "12334567809");

		Aluno a1 = new Aluno(1L, 1234546L, "Pedro", "10010010099", new Date());
		Aluno a2 = new Aluno(2L, 12345L, "Jonas", "10110110098", new Date());
		Aluno a3 = new Aluno(3L, 111111L, "Baurete", "4567890989");
		Aluno a4 = new Aluno(4L, 222222L, "Cão", "123456789");

		System.out.println("Professores \n");
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		System.out.println("Bonus: " + Professor.BONUS);

		System.out.println("Alunos \n");
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
		System.out.println(a4);
		System.out.println(Aluno.verificaMatricula("1231"));

	}
}