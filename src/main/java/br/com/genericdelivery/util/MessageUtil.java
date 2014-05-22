package br.com.genericdelivery.util;

import java.io.IOException;

import br.com.genericdelivery.view.Messages;

public class MessageUtil {

	private final static String messageFileName = "messages";
	private final static String messageSRC = "/";

	public static String getMessage(Messages message) {
		return getMessage(Locale.PT, message);
	}

	public static String getMessage(Locale locale, Messages message) {
		try {
			String messageFile = messageSRC + messageFileName + "_" + locale.getValue() + ".properties";
			return PropertiesUtil.getProperty(messageFile, message.getTag());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
