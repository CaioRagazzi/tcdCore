package com.tcd.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

public class GeneroConteudo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
	private Long id;
	
	@NotBlank
	@ManyToOne
	private Conteudo conteudo;
	
	@NotBlank
	@ManyToOne
	private Genero genero;
}
