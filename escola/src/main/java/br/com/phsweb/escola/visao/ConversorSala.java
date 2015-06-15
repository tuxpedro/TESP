package br.com.phsweb.escola.visao;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

import br.com.phsweb.escola.entidades.Sala;
import br.com.phsweb.escola.negocio.ServicoSala;

@FacesConverter("ConversorSala")
public class ConversorSala implements Converter {
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component,
			String value) {
		try {
			ServicoSala ss = (ServicoSala) new InitialContext()
					.lookup("java:global/escola-phs/ServicoSala");
			return ss.find(new Long(value));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component,
			Object value) {
		if (value != null) {
			Sala s = (Sala) value;
			if (s.getId() != null)
				return s.getId().toString();
		}
		return null;
	}
}