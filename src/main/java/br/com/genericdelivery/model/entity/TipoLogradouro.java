package br.com.genericdelivery.model.entity;

public enum TipoLogradouro {
	AVENIDA("Avenida", "av."), RUA("Rua", "rua");

	private String code;
	private String abreviacao;

	private TipoLogradouro(String code, String abreviacao) {
		this.code = code;
		this.abreviacao = abreviacao;
	}

	public static TipoLogradouro getTipoLogradouro(String code) {
		for (TipoLogradouro tipo : TipoLogradouro.values()) {
			if (tipo.getCode().equals(code))
				return tipo;
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAbreviacao() {
		return abreviacao;
	}

	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}

}
