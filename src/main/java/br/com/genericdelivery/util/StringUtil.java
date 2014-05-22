package br.com.genericdelivery.util;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static String captalize(String str) {
		if (str == null)
			return null;

		if (str.isEmpty())
			return "";

		StringTokenizer st = new StringTokenizer(str, " ", true);

		StringBuilder sb = new StringBuilder();

		while (st.hasMoreTokens()) {
			String word = st.nextToken();
			sb.append(StringUtil.toUpperCase(word, 0, 1));
		}

		return sb.toString();
	}

	public static String toUpperCase(String str, int begin, int end) {
		if (str == null)
			return null;

		if (str.isEmpty())
			return "";

		if (!(str.length() >= begin && str.length() >= end))
			return str;

		if (!(begin >= 0 && end >= begin))
			return str;

		String aux = str.substring(0, begin) + str.substring(begin, end).toUpperCase() + str.substring(end, str.length());

		return aux;
	}

	public static String uncaptalize(String str) {
		if (str == null)
			return null;

		if (str.isEmpty())
			return "";

		StringTokenizer st = new StringTokenizer(str, " ", true);

		StringBuilder sb = new StringBuilder();

		while (st.hasMoreTokens()) {
			String word = st.nextToken();
			sb.append(StringUtil.toLowerCase(word, 0, 1));
		}

		return sb.toString();
	}

	private static Object toLowerCase(String str, int begin, int end) {
		if (str == null)
			return null;

		if (str.isEmpty())
			return "";

		if (!(str.length() >= begin && str.length() >= end))
			return str;

		if (!(begin >= 0 && end >= begin))
			return str;

		String aux = str.substring(0, begin) + str.substring(begin, end).toLowerCase() + str.substring(end, str.length());

		return aux;
	}

	public static String swapCase(String str) {
		if (str == null)
			return null;

		if (str.isEmpty())
			return "";

		StringBuilder sb = new StringBuilder();

		for (char character : str.toCharArray()) {
			sb.append(Character.isLowerCase(character) ? Character.toUpperCase(character) : Character.toLowerCase(character));
		}

		return sb.toString();
	}

	public static String trimAll(String str) {
		if (str == null)
			return null;

		if (str.isEmpty())
			return "";

		String patternStr = "\\s+";
		String replaceStr = " ";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(str.trim());
		return matcher.replaceAll(replaceStr);
	}

	public static String initials(String str) {
		if (str == null)
			return null;

		if (str.isEmpty())
			return "";

		String[] words = str.split(" ");

		StringBuilder sb = new StringBuilder();

		for (String word : words) {
			sb.append(word.substring(0, 1));
		}

		return sb.toString();
	}

	public static String reverse(String str) {
		if (str == null)
			return null;

		if (str.isEmpty())
			return "";

		return new StringBuilder(str).reverse().toString();
	}

	public static boolean isNumber(String str) {
		if (str == null)
			return false;

		if (str.isEmpty())
			return false;

		int decimalCount = 0;

		char[] charArray = str.toCharArray();

		for (int i = 0; i < charArray.length && decimalCount <= 1; i++) {
			char character = charArray[i];
			if (Character.valueOf('.').equals(character))
				decimalCount++;
			else if (!Character.isDigit(character))
				return false;
		}

		return decimalCount <= 1;
	}

	public static boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}
}
