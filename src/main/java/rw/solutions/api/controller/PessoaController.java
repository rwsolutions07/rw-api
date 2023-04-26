package rw.solutions.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import rw.solutions.api.model.record.AtualizacaoPessoa;
import rw.solutions.api.model.record.CadastroPessoa;
import rw.solutions.api.model.record.DadosPessoa;
import rw.solutions.api.service.PessoaService;


@Tag(name = "Pessoa", description = "Métodos de pessoa")
@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaService service;
	
	@GetMapping("")
	public ResponseEntity<Page<DadosPessoa>> getPessoas(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		Page<DadosPessoa> pessoas = this.service.getPessoas(paginacao);
		return ResponseEntity.ok(pessoas);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<DadosPessoa> buscaPorID(@PathVariable(required=true) Long id) {
		DadosPessoa buscaPorID = this.service.buscaPorID(id);
		return ResponseEntity.ok(buscaPorID);
	}
	
	@Transactional
	@PostMapping("")
	public ResponseEntity<AtualizacaoPessoa> cadastrar(@RequestBody @Valid CadastroPessoa pessoa, UriComponentsBuilder uriBuilder) {
		AtualizacaoPessoa response  = this.service.cdastrarPessoa(pessoa);
		
		URI uri = uriBuilder.path("/pessoa/id/{id}").buildAndExpand(response.id()).toUri();
		
		return ResponseEntity.created(uri).body(response);
	}
	
	@Transactional
	@PutMapping("")
	public ResponseEntity<AtualizacaoPessoa> atualizar(@RequestBody @Valid AtualizacaoPessoa pessoa) {
		AtualizacaoPessoa response = this.service.atualizarPessoa(pessoa);
		return ResponseEntity.ok(response);
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional
	@DeleteMapping("")
	public ResponseEntity deletar(@RequestParam(required=true) Long id) {
		 this.service.deletar(id);
		 return ResponseEntity.noContent().build();
	}
}
