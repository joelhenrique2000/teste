package com.acme.credvarejo.tests;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.Cpf;
import com.acme.credvarejo.conta.IdentificadorContaCrediario;
import com.acme.credvarejo.contaCrediario.ContaCrediarioEspecial;


public class ContaCrediarioEspecialTest {

	IdentificadorContaCrediario identificadorContaCrediario;
	ContaCrediarioEspecial contaCrediarioEspecial;
	ContaCrediarioEspecial contaCrediarioEspecialComPontosAcumulados;
	Cliente cliente;
	
	@Before
	public void start() {
		cliente = new Cliente(new Cpf(19100000000L), "Joel Henrique", 22, new Date(2000, 03, 24), 1600, 0);
		identificadorContaCrediario = new IdentificadorContaCrediario(123L);
		contaCrediarioEspecial = new ContaCrediarioEspecial(identificadorContaCrediario, cliente, 1000, 10000, 10, true, 10);
		contaCrediarioEspecialComPontosAcumulados = new ContaCrediarioEspecial(identificadorContaCrediario, cliente, 1000, 10000, 10, true, 10, 100);
	}
	
	@Test
	public void DeveEfetuarPagamentoComSucesso() {
		double saldoAntesPagamento = contaCrediarioEspecial.getSaldoDevido();
		double valorPagamento = 100;
		
		contaCrediarioEspecial.efetuarPagamento(valorPagamento);
		
		assertEquals(contaCrediarioEspecial.getSaldoDevido(), (saldoAntesPagamento - valorPagamento), 0.5);
	}
	
	@Test
	public void DeveEfetuarPagamentoComPercentualDeDescontoPassadoZero() {
		double saldoAntesPagamento = contaCrediarioEspecialComPontosAcumulados.getSaldoDevido();
		double valorPagamento = 100;
		
		contaCrediarioEspecialComPontosAcumulados.setPercentualDeDesconto(0);
		contaCrediarioEspecialComPontosAcumulados.efetuarPagamento(valorPagamento, 0);
		
		assertEquals(contaCrediarioEspecialComPontosAcumulados.getSaldoDevido(), (saldoAntesPagamento - valorPagamento), 0.4);
	}
	
	@Test
	public void DeveEfetuarPagamentoComPercentualDeDescontoMaiorQuePercentualDeDescontoPassado() {
		double saldoAntesPagamento = contaCrediarioEspecialComPontosAcumulados.getSaldoDevido();
		double valorPagamento = 100;
		
		contaCrediarioEspecialComPontosAcumulados.setPercentualDeDesconto(10);
		contaCrediarioEspecialComPontosAcumulados.efetuarPagamento(valorPagamento, 0);
		
		double desconto = saldoAntesPagamento * (contaCrediarioEspecialComPontosAcumulados.getPercentualDeDesconto() / 100);
		
		assertEquals(desconto, 100, 0.5);
		
		assertEquals(contaCrediarioEspecialComPontosAcumulados.getSaldoDevido(), ((saldoAntesPagamento - valorPagamento) - desconto), 0.4);
	}
	
	@Test
	public void DeveEfetuarPagamentoComPercentualDeDescontoMenorQuePercentualDeDescontoPassado() {
		double saldoAntesPagamento = contaCrediarioEspecialComPontosAcumulados.getSaldoDevido();
		double valorPagamento = 100;
		
		contaCrediarioEspecialComPontosAcumulados.setPercentualDeDesconto(0);
		contaCrediarioEspecialComPontosAcumulados.efetuarPagamento(valorPagamento, 10);
		
		double desconto = saldoAntesPagamento * 10 / 100;
		
		assertEquals(desconto, 100, 0.5);
		
		assertEquals(contaCrediarioEspecialComPontosAcumulados.getSaldoDevido(), ((saldoAntesPagamento - valorPagamento) - desconto), 0.4);
	}

}
