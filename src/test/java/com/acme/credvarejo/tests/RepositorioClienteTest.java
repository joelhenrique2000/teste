package com.acme.credvarejo.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.ado.cliente.RepositorioCliente;
import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.Cpf;

public class RepositorioClienteTest {

	RepositorioCliente repositorioCliente;
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@Before
	public void start() {
		repositorioCliente = new RepositorioCliente(0);
		System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@Test
	public void DeveIncluirUmNovoCliente() {
		int posicaoAntesDeIncluir = repositorioCliente.repositorios.posicao;
		assertEquals(0, posicaoAntesDeIncluir);
		
		repositorioCliente.incluir(new Cliente(new Cpf(19100000000L), "Joel Henrique", 22, new Date(2000, 03, 24), 1600, 0));
		
		assertEquals(1, repositorioCliente.posicao);
		
	}

	@Test
	public void DeveExcluirUmNovoCliente() {
		int posicaoAntesDeIncluir = repositorioCliente.repositorios.posicao;
		assertEquals(0, posicaoAntesDeIncluir);
		
		repositorioCliente.incluir(new Cliente(new Cpf(19100000000L), "Joel Henrique", 22, new Date(2000, 03, 24), 1600, 0));
		assertEquals(1, repositorioCliente.posicao);
		repositorioCliente.excluir(19100000000L);
		
		assertEquals(0, repositorioCliente.posicao);
	}
	
	@Test
	public void NaoDeveExcluirNadaSeAListaEstiverVazia() {
		assertEquals(0, repositorioCliente.posicao);
		repositorioCliente.excluir(19100000000L);
		assertEquals("Vazio!", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void DeveAlterarNomeCliente() {
		String nomeAlterado = "Joel Henrique Silva Santos";
		
		repositorioCliente.incluir(new Cliente(new Cpf(19100000000L), "Joel Henrique", 22, new Date(2000, 03, 24), 1600, 0));
		
		repositorioCliente.alterar(0, nomeAlterado);
		
		assertEquals(1, repositorioCliente.posicao);
	}
	
	@Test
	public void NaoDeveAlterarNadaSeAListaEstiverVazia() {
		String nomeAlterado = "Joel Henrique Silva Santos";

		repositorioCliente.alterar(0, nomeAlterado);
		
		assertEquals("Vazio!\r\n"
				+ "Inv?lido!", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void DeveBuscarChaveCliente() {
		repositorioCliente.incluir(new Cliente(new Cpf(19100000000L), "Joel Henrique", 22, new Date(2000, 03, 24), 1600, 0));
		
		repositorioCliente.buscarPorChave(19100000000L);
		assertEquals("Cliente: Joel Henrique", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void NaoDeveBuscarNadaSeAListaEstiverVazia() {
		repositorioCliente.buscarPorChave(19100000000L);
		assertEquals("Inv?lido", outputStreamCaptor.toString().trim());
	}
	

	@Test
	public void DeveBuscarTodosClientes() {
		repositorioCliente.incluir(new Cliente(new Cpf(19100000000L), "Joel Henrique", 22, new Date(2000, 03, 24), 1600, 0));
		repositorioCliente.incluir(new Cliente(new Cpf(19100000000L), "Silva Santos", 22, new Date(2000, 03, 24), 1600, 0));

		assertEquals(0, outputStreamCaptor.size());
		repositorioCliente.buscarTodos();
		assertEquals(49, outputStreamCaptor.size());
	}
	

	@Test
	public void NaoDeveBuscarTodosClientesSeListaEstiverVazia() {
		repositorioCliente.buscarTodos();
		assertEquals("Vazio!", outputStreamCaptor.toString().trim());
	}

}
