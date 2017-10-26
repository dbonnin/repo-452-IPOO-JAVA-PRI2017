package com.diegobonnin.ipoo.ejercicios.polimorfismo;

public class CalculadoraNumerosConDecimales implements Calculadora {

	public Number sumar(Number sumando1, Number sumando2) {
		Double _sumando1=sumando1.doubleValue();
		Double _sumando2=sumando2.doubleValue();
		return _sumando1 + _sumando2;
	}

	public Number restar(Number minuendo, Number sustraendo) {
		Double _minuendo=minuendo.doubleValue();
		Double _sustraendo=sustraendo.doubleValue();
		return _minuendo - _sustraendo;
	}

	public Number multiplicar(Number multiplicando, Number multiplicador) {
		Double _multiplicando=multiplicando.doubleValue();
		Double _multiplicador=multiplicador.doubleValue();
		return _multiplicando * _multiplicador;
	}

	public Number dividir(Number dividendo, Number divisor) {
		Double _dividendo=dividendo.doubleValue();
		Double _divisor=divisor.doubleValue();
		return _dividendo / _divisor;
	}


}
