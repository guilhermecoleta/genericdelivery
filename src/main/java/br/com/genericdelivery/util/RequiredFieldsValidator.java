package br.com.genericdelivery.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.persistence.Id;

import br.com.genericdelivery.util.annotations.Required;
import br.com.genericdelivery.util.annotations.RequiredObject;

public abstract class RequiredFieldsValidator {

	public final static boolean validate(Object obj) {
		Class<? extends Object> objClass = obj.getClass();

		Method[] methods = objClass.getMethods();

		for (Method method : methods) {
			try {
				Object retObj;
				if (method.isAnnotationPresent(RequiredObject.class)) {
					retObj = method.invoke(obj);

					Field[] fields = retObj.getClass().getDeclaredFields();

					for (Field field : fields) {
						if (field.isAnnotationPresent(Id.class)) {
							return field != null;
						}
					}
				} else if (method.isAnnotationPresent(Required.class)) {
					retObj = method.invoke(obj);
					if (retObj == null) {
						return false;
					} else if (retObj instanceof String) {
						if (((String) retObj).trim().isEmpty())
							return false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return true;
	}
}
