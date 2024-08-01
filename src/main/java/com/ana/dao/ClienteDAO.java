package com.ana.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ana.dao.generic.GenericDAO;
import com.ana.domain.Cliente;


public class ClienteDAO extends GenericDAO<Cliente, Long> implements IClienteDAO {

	public ClienteDAO() {
		super();
	}

	@Override
	public Class<Cliente> getTipoClasse() {
		return Cliente.class;
	}

	@Override
	public void atualizarDados(Cliente entity, Cliente entityCadastrado) {
		entityCadastrado.setCidade(entity.getCidade());
		entityCadastrado.setCpf(entity.getCpf());
		entityCadastrado.setEmail(entity.getEmail());
		entityCadastrado.setEndereco(entity.getEndereco());
		entityCadastrado.setEstado(entity.getEstado());
		entityCadastrado.setNome(entity.getNome());
		entityCadastrado.setNumero(entity.getNumero());
		entityCadastrado.setTelefone(entity.getTelefone());
		
	}

	@Override
	protected String getQueryInsercao() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO TB_CLIENTE ");
		sb.append("(ID, NOME, CPF, EMAIL, TELEFONE, ENDERECO, NUMERO, CIDADE, ESTADO) ");
		sb.append("VALUES (nextval('sq_cliente'),?,?,?,?,?,?,?,?)");
		return sb.toString();
	}

	@Override
	protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Cliente entity) throws SQLException {
		stmInsert.setString(1, entity.getNome());
		stmInsert.setLong(2, entity.getCpf());
		stmInsert.setString(3, entity.getEmail());
		stmInsert.setLong(4, entity.getTelefone());
		stmInsert.setString(5, entity.getEndereco());
		stmInsert.setLong(6, entity.getNumero());
		stmInsert.setString(7, entity.getCidade());
		stmInsert.setString(8, entity.getEstado());
		
	}

	@Override
	protected String getQueryExclusao() {
		return "DELETE FROM TB_CLIENTE WHERE CPF = ?";
	}

	@Override
	protected void setParametrosQueryExclusao(PreparedStatement stmExclusao, Long valor) throws SQLException {
		stmExclusao.setLong(1, valor);
	}
	
	@Override
	protected String getQueryAtualizacao() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE TB_CLIENTE ");
		sb.append("SET NOME = ?, ");
		sb.append("TELEFONE = ?, ");
		sb.append("EMAIL = ?, ");
		sb.append("ENDERECO = ?, ");
		sb.append("NUMERO = ?, ");
		sb.append("CIDADE = ?, ");
		sb.append("ESTADO = ? ");
		sb.append("WHERE CPF = ? ");
		return sb.toString();
	}

	@Override
	protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Cliente entity) throws SQLException {
		stmUpdate.setString(1, entity.getNome());
		stmUpdate.setLong(2, entity.getTelefone());
		stmUpdate.setString(3, entity.getEmail());
		stmUpdate.setString(4, entity.getEndereco());
		stmUpdate.setLong(5, entity.getNumero());
		stmUpdate.setString(6, entity.getCidade());
		stmUpdate.setString(7, entity.getEstado());
		stmUpdate.setLong(8, entity.getCpf());
	}

	@Override
	protected void setParametrosQuerySelect(PreparedStatement stmSelect, Long valor) throws SQLException {
		stmSelect.setLong(1, valor);
	}

}
