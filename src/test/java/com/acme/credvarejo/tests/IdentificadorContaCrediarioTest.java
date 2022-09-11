package com.acme.credvarejo.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.conta.IdentificadorContaCrediario;


public class IdentificadorContaCrediarioTest {

	IdentificadorContaCrediario identificadorContaCrediario;
	
	@Before
	public void start() {
		identificadorContaCrediario = new IdentificadorContaCrediario(19100000000L);
	}
	
	@Test
	public void DeveCalcularDigitoVerificador() {
		assertEquals(0, identificadorContaCrediario.calcularDigitoVerificador());
	}
	
	@Test
	public void DeveCalcularDigitoVerificadorSeCpfTiverUmCaracter() {
		Long soma = IdentificadorContaCrediario.somaNumeros(0L);
		assertEquals(0L, soma.longValue());
	}
	
	@Test
	public void DeveVerificarValidadeDigito() {
		int digito = identificadorContaCrediario.calcularDigitoVerificador();
		Long numero = identificadorContaCrediario.getNumero();
		
		boolean statusVerificacao = identificadorContaCrediario.verificarValidadeDigito(numero.intValue());
		
		assertFalse(statusVerificacao);
		
	}

}
