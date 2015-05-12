package br.com.phsweb.escola.visao;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.phsweb.escola.entidades.Professor;
import br.com.phsweb.escola.negocio.ServicoProfessor;

@ManagedBean(name = "professorMb")
@ViewScoped
public class ControleProfessor {

	@Inject
	ServicoProfessor sp;

	@Inject
	private Logger log;

	private Professor professor;
	private Long id;
	private String nomeArg; // pesquisa por nome
	private List<Professor> professores;

	public String getNomeArg() {
		return nomeArg;
	}

	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Professor getProfessor() {
		return professor;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	@PostConstruct
	public void inicializa() {
		log.info("Executa o MB de professor");
		try {
			professores = sp.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gravar() {

		FacesMessage facesMsg;
		try {
			if (professor.getId() == null) {
				professor = sp.insert(professor);
			} else {
				professor = sp.update(professor);
			}
		} catch (Exception e) {
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: "
					+ e.getMessage(), "");

			FacesContext.getCurrentInstance().addMessage("messagePanel",
					facesMsg);
			return;
		}
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Professor Gravado com sucesso!", "");

		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}

	public void pesquisar() {
		try {
			professores = sp.findByNAme(nomeArg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void novo() {
		professor = new Professor();
	}

	public void cancelar() {
		professor = null;
	}

	public void editar() {
		try {
			professor = sp.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir() {
		try {
			sp.Delete(sp.find(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
