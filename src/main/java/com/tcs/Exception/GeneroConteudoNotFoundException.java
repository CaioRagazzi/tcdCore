package com.tcs.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Usuario not found")
public class GeneroConteudoNotFoundException extends RuntimeException {

}
