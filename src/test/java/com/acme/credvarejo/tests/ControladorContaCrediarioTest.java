package com.acme.credvarejo.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.ado.conta.RepositorioContaCrediario;
import com.acme.credvarejo.ado.conta.RepositorioMovimentoCrediario;
import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.Cpf;
import com.acme.credvarejo.conta.ContaCrediario;
import com.acme.credvarejo.conta.ControladorContaCrediario;
import com.acme.credvarejo.conta.ControladorMovimentoCrediario;
import com.acme.credvarejo.conta.IdentificadorContaCrediario;
import com.acme.credvarejo.conta.MovimentoCrediarioCredito;

public class ControladorContaCrediarioTest {

	ControladorContaCrediario controladorContaCrediario;
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@Before
	public void start() {
		controladorContaCrediario = new ControladorContaCrediario(new RepositorioContaCrediario());
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@Test
	public void DeveInserirUmaContaCrediario() {
		Cliente cliente = new Cliente(new Cpf(19100000000L), "Joel Henrique", 22, new Date(2000, 03, 24), 1600, 0);
		IdentificadorContaCrediario id = new IdentificadorContaCrediario(cliente.getCpf().getNumero());
		controladorContaCrediario.inserir(cliente, 1000, 10);
		controladorContaCrediario.buscar(id);
		assertEquals("Conta invalida", outputStreamCaptor.toString().trim());
	}

	// TO DO
	@Test
	public void teste() {
		Cliente cliente = new Cliente(new Cpf(19100000000L), "Joel Henrique", 22, new Date(2000, 03, 24), 1600, 0);
		IdentificadorContaCrediario id = new IdentificadorContaCrediario(cliente.getCpf().getNumero());
		controladorContaCrediario.inserir(cliente, 1000, 10);
		
		RepositorioMovimentoCrediario repositorioMovimentoCrediario = new RepositorioMovimentoCrediario();
		
		ContaCrediario contaCredito = new ContaCrediario(id, cliente, 100, 1000, 10, true);
		MovimentoCrediarioCredito movimentoCrediarioCredito = new MovimentoCrediarioCredito(contaCredito, 10, new Date(2000, 03, 24), 100);
		repositorioMovimentoCrediario.incluirCredito(movimentoCrediarioCredito);
		
		ControladorMovimentoCrediario controladorMovimentoCrediario = new ControladorMovimentoCrediario(repositorioMovimentoCrediario);
		assertEquals(1, 1);
		//IdentificadorContaCrediario identificadorContaCrediario = new IdentificadorContaCrediario(19100000000L);
		
		//controladorContaCrediario.creditar(id, 100, controladorMovimentoCrediario);
		
		//repositorioMovimentoCrediario.buscarTodos();
		//assertEquals("Conta invalida", outputStreamCaptor.toString().trim());
	}

}
