package br.com.genericdelivery.view.web.faces.util;

import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class EnderecoByCEPUtil {

	private static final String WEB_SERVICE = "http://cep.republicavirtual.com.br/web_cep.php?cep=";
	private static final String FORMATO = "&formato=xml";

	public static Element encontraCEP(String cep) {
		try {

			URL url = new URL(WEB_SERVICE + cep + FORMATO);

			Document document = getDocument(url);

			return document.getRootElement();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		encontraCEP("1444584545646");
	}

	private static Document getDocument(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);

		return document;
	}

}
