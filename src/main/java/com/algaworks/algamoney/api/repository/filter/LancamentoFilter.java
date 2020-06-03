package com.algaworks.algamoney.api.repository.filter;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LancamentoFilter {
	
	private String descricao;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimentoDe;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimentoAte;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descrição) {
		this.descricao = descrição;
	}
	public LocalDate getDataVencimentoDe() {
		return dataVencimentoDe;
	}
	public void setDataVencimentoDe(LocalDate dataVencimentoDe) {
		this.dataVencimentoDe = dataVencimentoDe;
	}
	public LocalDate getDataVencimentoAte() {
		return dataVencimentoAte;
	}
	public void setDataVencimentoAte(LocalDate dataVencimentoAte) {
		this.dataVencimentoAte = dataVencimentoAte;
	}
	
	

}
