package br.com.phsweb.escola.negocio;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.phsweb.escola.entidades.Professor;

@Stateless
@LocalBean
public class ServicoProfessor implements DAO<Professor, Long> {

	@Inject
	EntityManager em;

	@Inject
	private Logger log;

	@Override
	public Professor insert(Professor t) throws Exception {
		log.info("Persistindo " + t);
		em.persist(t);
		return t;
	}

	@Override
	public Professor update(Professor t) throws Exception {
		log.info("Atualizando " + t);
		return em.merge(t);
	}

	@Override
	public void Delete(Professor t) throws Exception {
		log.info("Deletando " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Professor find(Long k) throws Exception {
		log.info("Encontrando professor " + k);
		return em.find(Professor.class, k);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Professor> findAll() throws Exception {
		log.info("Encontrando todos os alunos");
		return em.createQuery("from Professor").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Professor> findByNAme(String nome) throws Exception {
		log.info("Encontra Professor pelo nome " + nome);
		return em.createNamedQuery("Professor.findByName")
				.setParameter("nome", nome + "%").getResultList();
	}

}
