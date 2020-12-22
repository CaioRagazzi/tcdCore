package com.tcd.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@Column(nullable = false, length = 50)
	@NotBlank
	private String nome;
	
	@Column(nullable = false, length = 50)
	@NotBlank
	private String login;
	
	@Column(nullable = false, length = 50)
	@NotBlank
	@Email
	private String email;
	
	@Column(nullable = false, length = 50)
	@NotBlank
	private String senha;
	
	public Usuario() {
		super();
	}
	
	public Usuario(
			@JsonProperty("id") UUID id, 
			@JsonProperty("nome") String nome, 
			@JsonProperty("login") String login, 
			@JsonProperty("email") String email, 
			@JsonProperty("senha") String senha) {
		super();
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.id = id;
		this.nome = nome;
	}
	
	public String getLogin() {
		return login;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public UUID getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

}
