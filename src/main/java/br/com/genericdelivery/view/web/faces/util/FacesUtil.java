package br.com.genericdelivery.view.web.faces.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.genericdelivery.model.entity.Tela;
import br.com.genericdelivery.util.MessageUtil;
import br.com.genericdelivery.view.Messages;

public class FacesUtil {

	public static void showMessage(FacesMessage facesMessage) {
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public static void showMessageInfo(String message) {
		FacesUtil.showMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação:", message));
	}

	public static void showMessageWarn(String message) {
		FacesUtil.showMessage(new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta:", message));
	}

	public static void showMessageError(String message) {
		FacesUtil.showMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", message));
	}

	public static void showMessageInfo(Messages message) {
		showMessageInfo(MessageUtil.getMessage(message));
	}

	public static void showMessageWarn(Messages message) {
		showMessageWarn(MessageUtil.getMessage(message));
	}

	public static void showMessageError(Messages message) {
		showMessageError(MessageUtil.getMessage(message));
	}

	public static String facesRedirectUrl(String destinationURL) {
		return destinationURL == null ? "" : destinationURL + "?faces-redirect=true";
	}

	public static String facesRedirectUrl(Tela tela) {
		return facesRedirectUrl(tela.getUrl());
	}

	public static Map<String, Object> getSessionMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}
	
	public static String getRequestUrl() {
		Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return request == null ? "" : ((HttpServletRequest) request).getRequestURI();
	}
}
