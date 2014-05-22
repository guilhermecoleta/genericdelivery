package br.com.genericdelivery.util;

public enum Locale {
	PT("pt_BR"), UN("un_US");

	private String value;

	private Locale(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
