package br.com.phsweb.escola.negocio;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.phsweb.escola.entidades.Sala;

@Stateless
@LocalBean
public class ServicoSala implements DAO<Sala, Long> {

	@Inject
	EntityManager em;

	@Inject
	private Logger log;

	@Override
	public Sala insert(Sala t) throws Exception {
		log.info("Gravando " + t);
		em.persist(t);
		return t;
	}

	@Override
	public Sala update(Sala t) throws Exception {
		log.info("Atualizando " + t);
		return em.merge(t);
	}

	@Override
	public void Delete(Sala t) throws Exception {
		log.info("Deletando " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Sala find(Long k) throws Exception {
		log.info("Encontrando aluno " + k);
		return em.find(Sala.class, k);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sala> findAll() throws Exception {
		log.info("Encontrando todos os alunos");
		return em.createQuery("from Sala").getResultList();
	}

	@Override
	public List<Sala> findByNAme(String nome) throws Exception {
		// Não havera pesquisa por nome
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Sala> findByCapacidade(int capacidade) {
		log.info("Pesquisando por capacidade da sala " + capacidade);
		return em.createNamedQuery("Sala.findByCapacidade")
				.setParameter("capacidade", capacidade).getResultList();
	}

}
