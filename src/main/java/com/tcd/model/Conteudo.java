package com.tcd.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Conteudo {
	
	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(nullable = false, length = 50)
	@NotBlank
	private String titulo;
	
	@Column(nullable = false, length = 50)
	@NotBlank
	private String descricao;
	
	@Column(nullable = false)
	@NotBlank
	@Email
	private Date dataLancamento;
	
	@Column(nullable = false)
	@NotBlank
	private int idTipoConteudo;
	
	@Column(nullable = false)
	@NotBlank
	private int quantidadeVisualizacao;
	
	public Conteudo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public int getIdTipoConteudo() {
		return idTipoConteudo;
	}

	public void setIdTipoConteudo(int idTipoConteudo) {
		this.idTipoConteudo = idTipoConteudo;
	}

	public int getQuantidadeVisualizacao() {
		return quantidadeVisualizacao;
	}

	public void setQuantidadeVisualizacao(int quantidadeVisualizacao) {
		this.quantidadeVisualizacao = quantidadeVisualizacao;
	}
}
