package model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {
	
	private final UUID id;
	@NotBlank
	private final String nome;
	@NotBlank
	private final String login;
	@NotBlank
	private final String email;
	@NotBlank
	private final String senha;
	
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
