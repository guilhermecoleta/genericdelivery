package br.com.genericdelivery.util;

import java.util.ArrayList;
import java.util.List;

public class CPFUtil {
	private static final int[] weightCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static List<String> cpfNotValid;
	
	public static List<String> getCpfNotValid() {
		if(cpfNotValid == null){
			cpfNotValid = new ArrayList<String>();
			cpfNotValid.add("00000000000");
			cpfNotValid.add("11111111111");
			cpfNotValid.add("22222222222");
			cpfNotValid.add("33333333333");
			cpfNotValid.add("44444444444");
			cpfNotValid.add("55555555555");
			cpfNotValid.add("66666666666");
			cpfNotValid.add("77777777777");
			cpfNotValid.add("88888888888");
			cpfNotValid.add("99999999999");
		}
		return cpfNotValid;
	}
	
	private static int calculateDigit(String string, int[] weight) {
		int sum = 0;
		for (int indice = string.length() - 1, digit; indice >= 0; indice--) {
			digit = Integer.parseInt(string.substring(indice, indice + 1));
			sum += digit * weight[weight.length - string.length() + indice];
		}
		sum = 11 - sum % 11;
		return sum > 9 ? 0 : sum;
	}
	
	public static boolean isValid(String cpf) {
		if ((cpf == null) || (cpf.length() != 11 && cpf.length() != 14)) {
			return false; 
		}
		else {
			cpf = removeMask(cpf);
			
			if (getCpfNotValid().contains(cpf))
				return false;
			
			Integer digit1 = calculateDigit(cpf.substring(0, 9), weightCPF);
			Integer digit2 = calculateDigit(cpf.substring(0, 9) + digit1, weightCPF);
			return cpf.equals(cpf.substring(0, 9) + digit1.toString() + digit2.toString());
		}
	}
	
	public static String removeMask(String cpf){
		return cpf.replaceAll("[.-]", "");
	}
}
