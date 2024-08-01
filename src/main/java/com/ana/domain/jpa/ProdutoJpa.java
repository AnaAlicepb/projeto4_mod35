/**
 * 
 */
package com.ana.domain.jpa;

import javax.persistence.*;

import com.ana.dao.Persistente;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUTO")
public class ProdutoJpa implements Persistente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
	@SequenceGenerator(name = "produto_seq", sequenceName = "produto_sq",
			initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "CODIGO", unique = true, nullable = false, length = 10)
	private String codigo;

	@Column(name = "NOME", nullable = false, length = 100)
	private String nome;

	@Column(name = "DESCRICAO", nullable = false, length = 100)
	private String descricao;

	@Column(name = "VALOR", nullable = false)
	private BigDecimal valor;

	@Column(name = "MARCA", nullable = false, length = 100)
	private String marca;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
}
