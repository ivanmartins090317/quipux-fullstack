package com.quipux.prova.domain.model;

public final class Documento {

	private Documento() {
	}

	public static String apenasDigitos(String valor) {
		if (valor == null) {
			return "";
		}
		return valor.replaceAll("\\D", "");
	}

	public static String primeiroNome(String nome) {
		var limpo = nome == null ? "" : nome.trim();
		var espaco = limpo.indexOf(' ');
		return espaco < 0 ? limpo : limpo.substring(0, espaco);
	}
}
