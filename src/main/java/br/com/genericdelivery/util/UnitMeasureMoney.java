package br.com.genericdelivery.util;

public enum UnitMeasureMoney {
	PERCENT("%"),
	REAL("Real");
	
	private String unit;
	
	private UnitMeasureMoney(String unit) {
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
	}
}
