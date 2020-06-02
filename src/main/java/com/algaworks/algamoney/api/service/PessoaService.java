package com.algaworks.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.PessoaRepository;



@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	//Regra de negocio para atualização completa pessoa
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		return buscarPessoaPeloCodigo(codigo, pessoa);
		
	}


	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		//Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo, pessoa);
		//pessoaSalva.setAtivo(ativo);
		//pessoaRepository.save(pessoaSalva);
		
		Pessoa pessoaSalva = this.pessoaRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		pessoaSalva.setAtivo(ativo);
		BeanUtils.copyProperties(codigo, ativo, "pessoa");
		pessoaRepository.save(pessoaSalva);
		
	}
	
	public Pessoa buscarPessoaPeloCodigo(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = this.pessoaRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);
	}


}
