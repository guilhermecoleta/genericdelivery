package br.com.genericdelivery.util;

/**
 * @author unionGroup
 * 
 */
public enum Time {
	HOUR("Hora"),
	DAY("Dia"),
	MONTH("Mês"),
	YEAR("Ano");
	
	private String time;
	
	private Time(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}
}
