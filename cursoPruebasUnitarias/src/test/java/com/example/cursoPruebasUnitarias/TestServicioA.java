package com.example.cursoPruebasUnitarias;

import org.junit.jupiter.api.Test;

import com.example.cursoPruebasUnitarias.services.ServicioA;
import com.example.cursoPruebasUnitarias.services.Impl.ServicioAImpl;

import junit.framework.Assert;

public class TestServicioA {

	@Test
	public void testSumar() {
		int a = 2;
		int b = 2;
		ServicioA servicioA = new ServicioAImpl();
		Assert.assertEquals(servicioA.sumar(a, b), 4);
	}
}
