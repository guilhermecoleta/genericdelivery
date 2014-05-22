package br.com.genericdelivery.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EMailUtil {
	public static boolean isValid(String email) {
		if ((email == null) || (email.trim().length() == 0) || email.contains(" ")) {
			return false;
		} else {
			Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
			Matcher macher = pattern.matcher(email);
			return macher.matches();
		}
	}
}
