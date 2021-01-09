package com.tcd.model;

import java.io.Serializable;
import java.util.Collection;

public class Lista implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tipoLista;
	private Collection<Long> conteudos;
	
	public Collection<Long> getConteudos() {
		return conteudos;
	}
	public void setConteudos(Collection<Long> conteudos) {
		this.conteudos = conteudos;
	}
	public int getTipoLista() {
		return tipoLista;
	}
	public void setTipoLista(int tipoLista) {
		this.tipoLista = tipoLista;
	}
}
