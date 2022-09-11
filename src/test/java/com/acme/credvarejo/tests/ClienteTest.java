package com.acme.credvarejo.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.Cpf;


public class ClienteTest {

	Cliente cliente;
	Cpf cpf;
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@Before
	public void start() {
		cpf = new Cpf(19100000000L);
		System.setOut(new PrintStream(outputStreamCaptor));
		cliente = new Cliente(cpf, "Joel Henrique", 22, new Date(2000, 03, 24), 1600, 0);
	}
	
	@Test
	public void DeveGravarNomeCompleto() {
		String nomeCompleto = "Joel Henrique Silva Santos";
		
		cliente.gravaNomeCompleto(nomeCompleto);
		
		assertEquals(cliente.getNome(), nomeCompleto);
	}
	
	@Test
	public void DeveRetornaPrimeiroNome() {
		String primeiroNome = "Joel";
		String segundoNome = "Henrique";
		
		cliente.setNome(primeiroNome + " " + segundoNome);
		
		assertEquals(cliente.getPrimeiroNome(), primeiroNome);
	}
	
	@Test
	public void DeveRetornaSegundoNome() {
		String primeiroNome = "Joel";
		String segundoNome = "Henrique";
		
		cliente.setNome(primeiroNome + " " + segundoNome);
		
		assertEquals(cliente.getSegundoNome(), segundoNome);
	}

	@Test
	public void DeveValidarComSucessoCliente() {
		Cliente cliente = new Cliente(new Cpf(19100000000L), "Joel Henrique", 22, new Date(2000, 03, 24), 1600, 0);
		cliente.validar();
		assertEquals("Cliente Validado", outputStreamCaptor.toString().trim());
	}

	@Test
	public void DeveValidarComErroCliente() {
		Cliente cliente = new Cliente(new Cpf(19100000000L), "Joel Henrique", 22, new Date(2000, 03, 24), 1600, 10000);
		cliente.validar();
		assertEquals("Cliente invalido", outputStreamCaptor.toString().trim());
	}

	@Test
	public void DeveRetornarChaveString() {
		assertEquals("19100000000", cliente.getChave());
	}

}
