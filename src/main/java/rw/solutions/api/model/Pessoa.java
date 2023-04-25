package rw.solutions.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.solutions.api.model.record.AtualizacaoPessoa;
import rw.solutions.api.model.record.CadastroPessoa;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "descricao", nullable = false, length = 1080)
	private String descricao;
	
	@Column(name = "cargo", nullable = false)
	private String cargo;
	
	@Column(name = "url_linkedin", nullable = false)
	private String urlLinkedin;
	
	@Lob
	@Column(name = "foto", nullable = true)
	private byte[] foto;
	
	
	public Pessoa(CadastroPessoa cadastro) {
		this.nome = cadastro.nome();
		this.descricao = cadastro.descricao();
		this.cargo = cadastro.cargo();
		this.urlLinkedin = cadastro.urlLinkdin();
	}


	public void atualizarInformacoes(@Valid AtualizacaoPessoa dados) {
		if(null != this.nome) {
			this.nome = dados.nome();
		}
		
		if(null != this.descricao) {
			this.descricao = dados.descricao();
		}
		
		if(null != this.cargo) {
			this.cargo = dados.cargo();
		}
		
		if(null != this.urlLinkedin) {
			this.urlLinkedin = dados.urlLinkdin();
		}
	}
}
