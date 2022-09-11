package com.acme.credvarejo.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.classesGerais.Repositorios;
import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.Cpf;

public class RepositoriosTest {


	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	Repositorios repositorios;
	
	@Before
	public void start() {
		System.setOut(new PrintStream(outputStreamCaptor));
		repositorios = new Repositorios(0);
	}
	
	@Test
	public void DeveAdicionarClientePrimeiraPosicao() {
		Cliente cliente = new Cliente(new Cpf(19100000000L), "Joel Henrique", 22, new Date(2000, 03, 24), 1600, 0);
		
		repositorios.setClienteRepo(0, cliente);
		
		assertEquals(cliente.getCpf(), repositorios.getClienteRepo(0).getCpf());
	}
	
	@Test
	public void DeveRetornarVazio() {
		repositorios.buscarChave("19100000000");

		assertEquals("Vazio!", outputStreamCaptor.toString().trim());
	}
	

}
