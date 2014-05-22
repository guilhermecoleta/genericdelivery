package br.com.genericdelivery.model.entity;

public enum Tela {
	LOGIN("/login"), MAIN("/pages/main"), USUARIO_LISTAGEM("/pages/usuario_listagem"), USUARIO_CADASTRO("/pages/usuario_cadastro"), PRODUTO_CADASTRO(
			"/pages/produto_cadastro"), CARDAPIO_CADASTRO("/pages/cardapio_cadastro"), PEDIDO_CADASTRO("/pages/pedido_cadastro");

	private Tela(String url) {
		this.url = url;
	}

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static Tela getTela(String valor) {
		for (Tela tela : Tela.values()) {
			if (tela.url.equals(valor))
				return tela;
		}
		return null;
	}

}
