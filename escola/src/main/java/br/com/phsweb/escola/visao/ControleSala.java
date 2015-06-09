package br.com.phsweb.escola.visao;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.phsweb.escola.entidades.Sala;
import br.com.phsweb.escola.negocio.ServicoSala;

@ManagedBean(name = "salaMb")
@ViewScoped
public class ControleSala {

	@Inject
	private Logger log;

	@Inject
	private ServicoSala sl;

	private Sala sala;
	private int capaciadeArg;
	private Long id;
	private List<Sala> salas;

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public int getCapaciadeArg() {
		return capaciadeArg;
	}

	public void setCapaciadeArg(int capaciadeArg) {
		this.capaciadeArg = capaciadeArg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	@PostConstruct
	public void inicializaLista() {
		log.info("Executa o MB de aluno");
		try {
			salas = sl.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gravar() {
		FacesMessage facesMsg;
		try {
			if (sala.getId() == null) {
				sala = sl.insert(sala);
			} else {
				sala = sl.update(sala);
			}
		} catch (Exception e) {
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: "
					+ e.getMessage(), "");

			FacesContext.getCurrentInstance().addMessage("messagePanel",
					facesMsg);
			return;
		}
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Sala Gravada com sucesso!", "");

		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}

	public void pesquisar() {
		try {
			salas = sl.findByCapacidade(capaciadeArg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void novo() {
		sala = new Sala();
	}

	public void cancelar() {
		sala = null;
	}

	public void editar() {
		try {
			sala = sl.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		sala = null;
	}

	public void excluir() {
		try {
			sl.Delete(sl.find(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		sala = null;
	}
}
