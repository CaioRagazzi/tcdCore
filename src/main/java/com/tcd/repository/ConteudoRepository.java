package com.tcd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcd.model.Conteudo;

public interface ConteudoRepository extends JpaRepository<Conteudo, Integer> {

}
