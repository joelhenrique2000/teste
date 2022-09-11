package com.acme.credvarejo.tests;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.Cpf;
import com.acme.credvarejo.conta.ContaCrediario;
import com.acme.credvarejo.conta.IdentificadorContaCrediario;
import com.acme.credvarejo.conta.MovimentoCrediarioCredito;

public class MovimentoCrediarioCreditoTest {

	Cliente cliente;
	IdentificadorContaCrediario identificadorContaCrediario;
	MovimentoCrediarioCredito movimentoCrediarioCredito;
	ContaCrediario contaCrediario;
	
	@Before
	public void start() {
		cliente = new Cliente(new Cpf(19100000000L), "Joel Henrique", 22, new Date(2000, 03, 24), 1600, 0);
		identificadorContaCrediario = new IdentificadorContaCrediario(19100000000L);
		
		contaCrediario = new ContaCrediario(identificadorContaCrediario, cliente, 1000, 10000, 10, false);
		movimentoCrediarioCredito = new MovimentoCrediarioCredito(contaCrediario, 100, new Date(2022, 10, 02), 1000);
	}
	
	@Test
	public void DeveRetornarMRComSucesso() {
		String nome = "Joel Henrique";
		
		movimentoCrediarioCredito.getContaCrediario().getCliente().setNome("Joel Henrique");
		String nomeExtrato = movimentoCrediarioCredito.getNomeExtrato(0);
		
		assertEquals(nomeExtrato, "HENRIQUE, JOEL MR.");
	}
	
	@Test
	public void DeveRetornarMRSComSucesso() {
		String nome = "Joel Henrique";
		
		movimentoCrediarioCredito.getContaCrediario().getCliente().setNome("Joel Henrique");
		String nomeExtrato = movimentoCrediarioCredito.getNomeExtrato(1);
		
		assertEquals(nomeExtrato, "HENRIQUE, JOEL MRS.");
	}
	
	@Test
	public void DeveRetornarErroCasoOpcaoNomeExtratoNaoExista() {
		String nome = "Joel Henrique";
		
		movimentoCrediarioCredito.getContaCrediario().getCliente().setNome("Joel Henrique");
		String nomeExtrato = movimentoCrediarioCredito.getNomeExtrato(10);
		
		assertEquals(nomeExtrato, "Inválido!");
	}

}
