package br.com.genericdelivery.view.web.faces.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Date.class, value = "DateConverter")
public class DateConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return spf.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Date date = (Date) value;
		SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");  
		return spf.format(date);  
	}

}
