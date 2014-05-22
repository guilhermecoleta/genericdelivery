package br.com.genericdelivery.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class EncryptionUtil {

	private static final byte[] valorChave = new byte[] { 'U', 'm', 'a', 'C',
			'h', 'a', 'v', 'e', 'Q', 'u', 'a', 'l', 'q', 'u', 'e', 'r' };

	public static String criptografa(String mensagem) {
		Key chave;
		try {
			chave = criaChave();
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.ENCRYPT_MODE, chave);
			byte[] bytesCriptografados = c.doFinal(mensagem.getBytes());
			byte[] bytesBase64 = Base64.encodeBase64(bytesCriptografados);
			return new String(bytesBase64);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String descriptografa(String mensagemCriptografada) {
		Key chave = criaChave();
		Cipher c;
		try {
			c = Cipher.getInstance("AES");
			c.init(Cipher.DECRYPT_MODE, chave);
			byte[] decordedValue = Base64.decodeBase64(mensagemCriptografada);
			byte[] decValue = c.doFinal(decordedValue);
			String decryptedValue = new String(decValue);
			return decryptedValue;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			return mensagemCriptografada;
		}
	}

	private static Key criaChave() {
		Key key = new SecretKeySpec(valorChave, "AES");
		return key;
	}

}
