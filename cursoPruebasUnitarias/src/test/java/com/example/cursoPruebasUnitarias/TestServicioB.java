package com.example.cursoPruebasUnitarias;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.cursoPruebasUnitarias.services.ServicioA;
import com.example.cursoPruebasUnitarias.services.ServicioB;
import com.example.cursoPruebasUnitarias.services.Impl.ServicioAImpl;
import com.example.cursoPruebasUnitarias.services.Impl.ServicioBImpl;

import junit.framework.Assert;

public class TestServicioB {

	@Test
	public void testMultiplicar() {
		ServicioB servicioB = new ServicioBImpl();
		Assert.assertEquals(servicioB.multiplicar(2, 3), 6);
	}
	
	@Test
	public void testMultiplicarSumar() {
		ServicioA servicioA = new ServicioAImpl();
		ServicioB servicioB = new ServicioBImpl();
		
		servicioB.setServicioA(servicioA);
		Assert.assertEquals(servicioB.multiplicarSuma(2, 3, 2),10);
	}
	
    @Test
    public void testMultiplicarSumarMockito(){
        ServicioA servicioA = Mockito.mock(ServicioA.class);
        Mockito.when(servicioA.sumar(2,3)).thenReturn(5); //siempre retorna un 5, aisle dependencias

        ServicioB servicioB = new ServicioBImpl();
        servicioB.setServicioA(servicioA);
        Assert.assertEquals(servicioB.multiplicarSuma(2,3,2),10);
    }
}



