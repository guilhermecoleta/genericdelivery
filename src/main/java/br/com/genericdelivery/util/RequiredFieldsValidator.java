package br.com.genericdelivery.util;

import java.lang.reflect.Field;

import br.com.genericdelivery.service.exceptions.CamposObrigatoriosNaoPrenchidos;
import br.com.genericdelivery.util.annotations.Required;

public abstract class RequiredFieldsValidator {

	public static void validate(Object target) throws IllegalAccessException, CamposObrigatoriosNaoPrenchidos{
		Class<?> clazz = target.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields){
			validateField(field, target);
		}
	}

	private static void validateField(Field field, Object target) throws CamposObrigatoriosNaoPrenchidos, IllegalAccessException{
		field.setAccessible(true);
		Object value = field.get(target);
		if (field.isAnnotationPresent(Required.class)){
			if (value == null){
				throw new CamposObrigatoriosNaoPrenchidos();
			}
		}
	}

}
