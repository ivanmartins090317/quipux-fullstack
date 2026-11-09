package com.quipux.prova.infra.nationalize;

import java.util.Locale;

public final class CountryNameResolver {

	private static final Locale PORTUGUES = Locale.forLanguageTag("pt-BR");

	private CountryNameResolver() {
	}

	public static String nomeEmPortugues(String iso2) {
		if (iso2 == null || iso2.length() != 2) {
			return iso2;
		}
		var codigo = iso2.toUpperCase(Locale.ROOT);
		var nome = new Locale("", codigo).getDisplayCountry(PORTUGUES);
		if (nome == null || nome.isBlank() || nome.equalsIgnoreCase(codigo)) {
			return codigo;
		}
		return nome;
	}
}
