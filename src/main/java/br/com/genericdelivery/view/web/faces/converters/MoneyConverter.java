package br.com.genericdelivery.view.web.faces.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Double.class, value = "MoneyConverter")
public class MoneyConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		value = value.replace(",", ".");
		final double number = Double.parseDouble(value);
		return number;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		final String valor = value.toString();
		return valor;
	}

}
