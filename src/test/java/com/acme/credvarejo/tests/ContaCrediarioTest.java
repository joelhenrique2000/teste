package com.acme.credvarejo.tests;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.Cpf;
import com.acme.credvarejo.conta.ContaCrediario;
import com.acme.credvarejo.conta.IdentificadorContaCrediario;

public class ContaCrediarioTest {

	ContaCrediario contaCrediario;
	private Cliente cliente;

	@Before
	public void start() {
		cliente = new Cliente(new Cpf(19100000000L), "Joel Henrique", 22, new Date(2000, 03, 24), 1600, 0);
		contaCrediario = new ContaCrediario(new IdentificadorContaCrediario(123456L), cliente, 100, 5000, 12, true);
	}
	
	
	@Test
	public void DeveEfetuarUmPagamentoComSucesso() {
		double valorPagamento = 200;
		double saldo = 1000;
		
		contaCrediario.setSaldoDevido(saldo);
		
		contaCrediario.efetuarPagamento(valorPagamento);
		
		assertEquals(contaCrediario.getSaldoDevido(), (saldo-valorPagamento), 0.5);
	}
	
	
	@Test
	public void DeveEfetuarUmaCompraComSucesso() {
		double valorPagamento = 200;
		double saldo = 1000;
		
		contaCrediario.setSaldoDevido(saldo);
		
		contaCrediario.efetuarCompra(valorPagamento);
		
		assertEquals(contaCrediario.getSaldoDevido(), (saldo+valorPagamento), 0.5);
	}
	
	@Test
	public void DeveDesativarAContaComSucesso() {
		assertTrue(contaCrediario.isAtiva());
		
		contaCrediario.desativar();
		
		assertFalse(contaCrediario.isAtiva());
	}
	
	@Test
	public void DeveReativarAContaComSucesso() {
		contaCrediario.desativar();
		assertFalse(contaCrediario.isAtiva());
		
		contaCrediario.reativar();
		
		assertTrue(contaCrediario.isAtiva());
	}
	
	
}
