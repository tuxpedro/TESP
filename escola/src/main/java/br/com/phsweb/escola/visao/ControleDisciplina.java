package br.com.phsweb.escola.visao;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.phsweb.escola.entidades.Disciplina;
import br.com.phsweb.escola.negocio.ServicoDisciplina;

@ManagedBean(name = "disciplinaMb")
@ViewScoped
public class ControleDisciplina {
	@Inject
	private Logger log;

	@Inject
	private ServicoDisciplina sd;

	private Disciplina disciplina;
	private Long id;
	private String nomeDisciplina;
	private String nomeCurso;
	private List<Disciplina> disciplinas;

	public ServicoDisciplina getSd() {
		return sd;
	}

	public void setSd(ServicoDisciplina sd) {
		this.sd = sd;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@PostConstruct
	public void listaDisciplina() {
		log.info("excutando o MB de disciplina");
		try {
			disciplinas = sd.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gravar() {
		FacesMessage facesMsg;
		try {
			if (disciplina.getId() == null) {
				disciplina = sd.insert(disciplina);
			} else {
				disciplina = sd.update(disciplina);
			}
		} catch (Exception e) {
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: "
					+ e.getMessage(), "");

			FacesContext.getCurrentInstance().addMessage("messagePanel",
					facesMsg);
			return;
		}
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Disciplina gravada com sucesso!", "");

		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}

	public void pesquisa() {
		try {
			disciplinas = sd.findByNameECurso(nomeDisciplina, nomeCurso);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void novo() {
		disciplina = new Disciplina();
	}

	public void editar() {
		try {
			disciplina = sd.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		disciplina = null;
	}

	public void excluir() {
		try {
			sd.Delete(sd.find(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		disciplina = null;
	}

	public void cancelar() {
		disciplina = null;
	}

}
