package com.tcs.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Classificacao not found")
public class ClassificacaoNotFoundException extends RuntimeException {

}
