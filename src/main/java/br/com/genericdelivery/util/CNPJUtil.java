package br.com.genericdelivery.util;


public class CNPJUtil {
	private static final int[] weightCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	
	private static int calculateDigit(String string, int[] weight) {
		int sum = 0;
		for (int indice = string.length() - 1, digit; indice >= 0; indice--) {
			digit = Integer.parseInt(string.substring(indice, indice + 1));
			sum += digit * weight[weight.length - string.length() + indice];
		}
		sum = 11 - sum % 11;
		return sum > 9 ? 0 : sum;
	}
	
	public static boolean isValid(String cnpj) {
		if ((cnpj == null) || (cnpj.length() != 14) && (cnpj.length() != 18))
			return false;
		else {
			cnpj = removeMask(cnpj);			
			Integer digit1 = calculateDigit(cnpj.substring(0, 12), weightCNPJ);
			Integer digit2 = calculateDigit(cnpj.substring(0, 12) + digit1, weightCNPJ);
			return cnpj.equals(cnpj.substring(0, 12) + digit1.toString() + digit2.toString());
		}
	}
	
	public static String removeMask(String cnpj){
		return cnpj.replaceAll("[./-]", "");
	}
}
