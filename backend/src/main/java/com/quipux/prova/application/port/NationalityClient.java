package com.quipux.prova.application.port;

import com.quipux.prova.domain.model.CountryGuess;

public interface NationalityClient {

	CountryGuess lookup(String firstName);
}
