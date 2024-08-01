package com.ana;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ana.dao.ClienteDaoMock;
import com.ana.dao.IClienteDAO;
import com.ana.domain.Cliente;
import com.ana.exceptions.DAOException;
import com.ana.exceptions.TipoChaveNaoEncontradaException;
import com.ana.services.ClienteService;
import com.ana.services.IClienteService;

public class ClienteServiceTest {
	
	private IClienteService clienteService;
	
	private Cliente cliente;
	
	public ClienteServiceTest() {
		IClienteDAO dao = new ClienteDaoMock();
		clienteService = new ClienteService(dao);
	}
	
	@Before
	public void init() {
		cliente = new Cliente();
		cliente.setCpf(999999999L);
		cliente.setNome("Jailson");
		cliente.setEmail("jjs2@hotmail.com");
		cliente.setCidade("João Pessoa");
		cliente.setEndereco("Cabo Branco");
		cliente.setEstado("PB");
		cliente.setNumero(30);
		cliente.setTelefone(83999098837L);
	}
	
	@Test
	public void pesquisarCliente() throws DAOException {
		Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
	}
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, DAOException {
		Boolean retorno = clienteService.cadastrar(cliente);
		
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void excluirCliente() throws DAOException {
		clienteService.excluir(cliente.getCpf());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException {
		cliente.setNome("Jailson Joventino");
		clienteService.alterar(cliente);
		
		Assert.assertEquals("Jailson Joventino", cliente.getNome());
	}
}
