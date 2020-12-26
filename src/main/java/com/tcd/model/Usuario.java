package com.tcd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
	private Long id;

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
			@JsonProperty("id") Long id, 
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

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
