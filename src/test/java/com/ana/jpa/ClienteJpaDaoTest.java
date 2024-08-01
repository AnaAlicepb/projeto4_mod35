package com.ana.jpa;

import com.ana.dao.jpa.ClienteJpaDao;
import com.ana.dao.jpa.IClienteJpaDao;
import com.ana.domain.jpa.ClienteJpa;
import com.ana.exceptions.DAOException;
import com.ana.exceptions.MaisDeUmRegistroException;
import com.ana.exceptions.TableException;
import com.ana.exceptions.TipoChaveNaoEncontradaException;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import java.util.Collection;
import java.util.Random;

public class ClienteJpaDaoTest {
    private IClienteJpaDao clienteDao;

    private Random random;

    public ClienteJpaDaoTest() {
        this.clienteDao = new ClienteJpaDao();
        random = new Random();
    }

    @After
    public void end() throws DAOException {
        Collection<ClienteJpa> list = clienteDao.buscarTodos();
        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Test
    public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        ClienteJpa cliente = criarCliente();
        clienteDao.cadastrar(cliente);

        ClienteJpa clienteConsultado = clienteDao.consultar(cliente.getId());
        assertNotNull(clienteConsultado);

    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        ClienteJpa cliente = criarCliente();
        ClienteJpa retorno = clienteDao.cadastrar(cliente);
       assertNotNull(retorno);

        ClienteJpa clienteConsultado = clienteDao.consultar(retorno.getId());
        assertNotNull(clienteConsultado);

        clienteDao.excluir(cliente);

        ClienteJpa clienteConsultado1 = clienteDao.consultar(retorno.getId());
        assertNull(clienteConsultado1);
    }

    @Test
    public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        ClienteJpa cliente = criarCliente();
        ClienteJpa retorno = clienteDao.cadastrar(cliente);
        assertNotNull(retorno);

        ClienteJpa clienteConsultado = clienteDao.consultar(cliente.getId());
        assertNotNull(clienteConsultado);

        clienteDao.excluir(cliente);
        clienteConsultado = clienteDao.consultar(cliente.getId());
        assertNull(clienteConsultado);
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        ClienteJpa cliente = criarCliente();
        ClienteJpa retorno = clienteDao.cadastrar(cliente);
        assertNotNull(retorno);

        ClienteJpa clienteConsultado = clienteDao.consultar(cliente.getId());
        assertNotNull(clienteConsultado);

        clienteConsultado.setNome("Samuel Ferreira");
        clienteDao.alterar(clienteConsultado);

        ClienteJpa clienteAlterado = clienteDao.consultar(clienteConsultado.getId());
        assertNotNull(clienteAlterado);
        assertEquals("Samuel Ferreira", clienteAlterado.getNome());

        clienteDao.excluir(cliente);
        clienteConsultado = clienteDao.consultar(clienteAlterado.getId());
        assertNull(clienteConsultado);
    }

    @Test
    public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
        ClienteJpa cliente = criarCliente();
        ClienteJpa retorno = clienteDao.cadastrar(cliente);
        assertNotNull(retorno);

        ClienteJpa cliente1 = criarCliente();
        ClienteJpa retorno1 = clienteDao.cadastrar(cliente1);
        assertNotNull(retorno1);

        Collection<ClienteJpa> list = clienteDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        Collection<ClienteJpa> list1 = clienteDao.buscarTodos();
        assertTrue(list1 != null);
        assertTrue(list1.size() == 0);
    }

    private ClienteJpa criarCliente() {
        ClienteJpa cliente = new ClienteJpa();
        cliente.setCpf(random.nextLong());
        cliente.setNome("Samuel");
        cliente.setEmail("samuel@ebac.com");
        cliente.setCidade("Beberibe");
        cliente.setEndereco("Serra do Félix");
        cliente.setEstado("Ceará");
        cliente.setNumero(69);
        cliente.setTelelefone(1199999999L);
        return cliente;
    }
}
