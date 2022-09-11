package com.acme.credvarejo.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import com.acme.credvarejo.cliente.Cpf;

public class CpfTest {

	Cpf cpf;
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@Before
	public void start() {
		System.setOut(new PrintStream(outputStreamCaptor));
		cpf = new Cpf(19100000000L);
	}
	
	@Test
	public void IsCpfValido() {
		assertTrue((new Cpf(0L)).isCPF("19100000000"));
	}
	
	@Test
	public void SequenciaIgual() {
		assertFalse((new Cpf(0L)).isCPF("00000000000"));
		assertFalse((new Cpf(0L)).isCPF("11111111111"));
		assertFalse((new Cpf(0L)).isCPF("22222222222"));
		assertFalse((new Cpf(0L)).isCPF("33333333333"));
		assertFalse((new Cpf(0L)).isCPF("44444444444"));
		assertFalse((new Cpf(0L)).isCPF("55555555555"));
		assertFalse((new Cpf(0L)).isCPF("66666666666"));
		assertFalse((new Cpf(0L)).isCPF("77777777777"));
		assertFalse((new Cpf(0L)).isCPF("88888888888"));
		assertFalse((new Cpf(0L)).isCPF("99999999999"));
	}

	@Test
	public void CpfMaiorQueOnzeCaracteres () {
		assertFalse((new Cpf(0L)).isCPF("9999999999"));
	}
	
	@Test
	public void CpfMenorQueOnzeCaracteres () {
		assertFalse((new Cpf(0L)).isCPF("999999999999"));
	}
	
	@Test
	public void CpfComCaracteresAlfanumericos () {
		assertFalse((new Cpf(0L)).isCPF("###########"));
	}
	
	@Test
	public void DigitosCalculadosNaoConferemComDigitosInformados () {
		assertFalse((new Cpf(0L)).isCPF("19100000001"));
	}
	
	@Test
	public void DeveRetornarStringCpf () {
		assertEquals("191.000.000-00", cpf.imprimeCPF("19100000000"));
	}
	
	@Test
	public void DeveCalcularDigito () {
		assertEquals("9", cpf.calculaDigitoMod11("19100000000", 10, 10, false));
	}
	

}
