package br.com.genericdelivery.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	public static String getProperty(Properties properties, String index) {
		return properties.getProperty(index);
	}
	
	public static String getProperty(String src, String index) throws IOException {
		return PropertiesUtil.load(src).getProperty(index);
	}

	public static Properties load(InputStream inputStream) throws IOException {
		Properties propertie = new Properties();
		propertie.load(inputStream);
		return propertie;
	}

	public static Properties load(String src) throws IOException {
		InputStream inp = PropertiesUtil.class.getResourceAsStream(src);
		Properties propertie = PropertiesUtil.load(inp);
		inp.close();
		return propertie;
	}
}
