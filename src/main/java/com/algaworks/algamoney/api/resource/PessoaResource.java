package com.algaworks.algamoney.api.resource;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.event.RecursoCriadoEvent;
import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.PessoaRepository;
import com.algaworks.algamoney.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	
	//Injetando a implementação de CategoriaRepository
		@Autowired
		private PessoaRepository pessoaRepository;
		
		
		
		//Dispara evento
		@Autowired
		private ApplicationEventPublisher publisher;
		
		//Injetando a classe PessoaService
		@Autowired
		private PessoaService pessoaService;
		
		
		//Lista categoria
		@GetMapping
		public List<Pessoa> listar(){
			return pessoaRepository.findAll();
			
		}
		
		//Busca pelo código
		@GetMapping("/{codigo}")
		public ResponseEntity<Pessoa> buscaPeloCodigo(@PathVariable Long codigo) {
			 Optional<Pessoa> pessoa = this.pessoaRepository.findById(codigo);
			 
			 return pessoa.isPresent() ? 
					 ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();
		}
		
		//Criando categoria
		
		//O @Valid está validando o @NotNull na categoria - no model
		@PostMapping
		public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
			Pessoa pessoaSalva = pessoaRepository.save(pessoa);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
			return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
		}
		
		//Remove pessoa
		@DeleteMapping("/{codigo}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void remover(@PathVariable Long codigo) {
			pessoaRepository.deleteById(codigo);
			
		}
		
		//Atualização completa pessoa - utlizando regra de negocio separada na classe PessoaService
		@PutMapping("/{codigo}")
		public ResponseEntity<Pessoa> atualizar(@PathVariable long codigo, @Valid @RequestBody Pessoa pessoa){
			Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);			
			return ResponseEntity.ok(pessoaSalva);
		}
		
		//Atualização parcial - Status Ativo - Inativo
		@PutMapping("/{codigo}/ativo")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
			pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
			
		}

}
