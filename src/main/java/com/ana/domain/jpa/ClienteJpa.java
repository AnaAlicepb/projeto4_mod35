package com.ana.domain.jpa;

import javax.persistence.*;

import com.ana.dao.Persistente;

@Entity
@Table(name = "TB_CLIENTE")
public class ClienteJpa implements Persistente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "cliente_seq")
	@SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_sq",
			initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name = "NOME", nullable = false, length = 100)
	private String nome;
	
	@Column(name = "CPF", unique = true, nullable = false)
    private Long cpf;

	@Column(name = "EMAIL", nullable = false, length = 100)
	private String email;

	@Column(name = "TELEFONE", nullable = false, length = 20)
    private Long telelefone;

	@Column(name = "ENDERECO", nullable = false, length = 100)
    private String endereco;

	@Column(name = "NUMERO", nullable = false)
    private Integer numero;

	@Column(name = "CIDADE", nullable = false, length = 100)
    private String cidade;

	@Column(name = "ESTADO", nullable = false, length = 50)
    private String estado;
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getTelelefone() {
		return telelefone;
	}

	public void setTelelefone(Long telelefone) {
		this.telelefone = telelefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
