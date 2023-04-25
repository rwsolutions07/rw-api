package rw.solutions.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import rw.solutions.api.model.record.AtualizacaoPessoa;
import rw.solutions.api.model.record.CadastroPessoa;
import rw.solutions.api.model.record.DadosPessoa;
import rw.solutions.api.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaService service;
	
	@GetMapping("")
	public Page<DadosPessoa> getPessoas(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		return this.service.getPessoas(paginacao);
	}
	
	@GetMapping("/id/{id}")
	public DadosPessoa buscaPorID(@PathVariable(required=true) Long id) {
		return this.service.buscaPorID(id);
	}
	
	@Transactional
	@PostMapping("")
	public void cadastrar(@RequestBody @Valid CadastroPessoa pessoa) {
		this.service.cdastrarPessoa(pessoa);
	}
	
	@Transactional
	@PutMapping("")
	public void atualizar(@RequestBody @Valid AtualizacaoPessoa pessoa) {
		this.service.atualizarPessoa(pessoa);
	}
	
	@Transactional
	@DeleteMapping("")
	public void deletar(@RequestParam(required=true) Long id) {
		 this.service.deletar(id);
	}
}
