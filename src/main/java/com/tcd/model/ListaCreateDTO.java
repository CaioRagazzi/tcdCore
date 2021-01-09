package com.tcd.model;


public class ListaCreateDTO {
	
	private int userId;
	private int tipoLista;
	private int idConteudo;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTipoLista() {
		return tipoLista;
	}
	public void setTipoLista(int tipoLista) {
		this.tipoLista = tipoLista;
	}
	public int getIdConteudo() {
		return idConteudo;
	}
	public void setIdConteudo(int idConteudo) {
		this.idConteudo = idConteudo;
	}
}
