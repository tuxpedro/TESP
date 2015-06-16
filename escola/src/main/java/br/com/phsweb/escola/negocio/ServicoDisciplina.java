package br.com.phsweb.escola.negocio;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.phsweb.escola.entidades.Disciplina;

@Stateless
@LocalBean
public class ServicoDisciplina implements DAO<Disciplina, Long> {

	@Inject
	EntityManager em;

	@Inject
	private Logger log;

	@Override
	public Disciplina insert(Disciplina t) throws Exception {
		log.info("Gravando " + t);
		em.persist(t);
		return t;
	}

	@Override
	public Disciplina update(Disciplina t) throws Exception {
		log.info("Atualizando " + t);
		return em.merge(t);
	}

	@Override
	public void Delete(Disciplina t) throws Exception {
		log.info("Deletando " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Disciplina find(Long k) throws Exception {
		log.info("Encontrando a disciplina " + k);
		return em.find(Disciplina.class, k);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Disciplina> findAll() throws Exception {
		log.info("Encontrando todas as disciplinas ");
		return em.createQuery("from Disciplina").getResultList();
	}

	@Override
	public List<Disciplina> findByNAme(String nome) throws Exception {
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Disciplina> findByNameECurso(String nome, String curso) {
		log.info("Pesquisando Disciplina por " + nome);
		return em.createNamedQuery("Disciplina.findByNameECurso")
				.setParameter("nome", nome+"%").setParameter("curso", curso+"%")
				.getResultList();
	}
}
