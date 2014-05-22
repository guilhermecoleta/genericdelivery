package br.com.genericdelivery.view.web.faces.converters;

import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Double.class, value = "CurrencyLocale")
public class CurrencyLocale implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			return Double.parseDouble(value.replaceAll("[,]","."));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return new DecimalFormat("#,##0.00").format((Double) value);  
	}

}
