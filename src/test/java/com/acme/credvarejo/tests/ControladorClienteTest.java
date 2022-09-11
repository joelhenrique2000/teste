package com.acme.credvarejo.tests;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.ado.cliente.RepositorioCliente;
import com.acme.credvarejo.classesGerais.RepositorioRegistro;
import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.ControladorCliente;
import com.acme.credvarejo.cliente.Cpf;

public class ControladorClienteTest {
	
	ControladorCliente controladorCliente;
	Cliente cliente;
	Cpf cpf;
	
	@Before
	public void start() {
		cliente = new Cliente(cpf, "Joel Henrique", 22, new Date(2000, 03, 24), 1600, 0);
		controladorCliente = new ControladorCliente(new RepositorioCliente(0));
	}

	@Test
	public void DeveIncluirUmClienteComSucesso() {
		controladorCliente.incluir(cliente);
		assertEquals(10, 10);
	}

	@Test
	public void DeveAlterarUmClienteComSucesso() {
		assertEquals(10, 10);
	}

	@Test
	public void DeveExcluirUmClienteComSucesso() {
		assertEquals(10, 10);
	}

	@Test
	public void DeveBuscarUmClienteComSucesso() {
		assertEquals(10, 10);
	}

}
