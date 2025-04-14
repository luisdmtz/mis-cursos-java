package com.example.cursoPruebasUnitarias.services.Impl;

import com.example.cursoPruebasUnitarias.services.ServicioA;
import com.example.cursoPruebasUnitarias.services.ServicioB;

public class ServicioBImpl implements ServicioB{

	private ServicioA servicioA;
	
	@Override
	public ServicioA getServicioA() {
		return servicioA;
	}

	@Override
	public void setServicioA(ServicioA servicioA) {
		this.servicioA = servicioA;
	}

	@Override
	public int multiplicarSuma(int a, int b, int multiplicar) {
		return servicioA.sumar(a, b) * multiplicar;
	}

	@Override
	public int multiplicar(int a, int b) {
		return a*b;
	}
	

}
