package com.diegobonnin.ipoo.ejercicios.polimorfismo;

public class CalculadoraNumerosSinDecimales implements Calculadora{
	
	public CalculadoraNumerosSinDecimales(){
		
	}
	
	@Override
	public Number sumar(Number sumando1, Number sumando2) {
		Integer _sumando1=sumando1.intValue();
		Integer _sumando2=sumando2.intValue();
		return _sumando1 + _sumando2;
	}

	@Override
	public Number restar(Number minuendo, Number sustraendo) {
		Integer _minuendo=minuendo.intValue();
		Integer _sustraendo=sustraendo.intValue();
		return _minuendo - _sustraendo;
	}

	@Override
	public Number multiplicar(Number multiplicando, Number multiplicador) {
		Integer _multiplicando=multiplicando.intValue();
		Integer _multiplicador=multiplicador.intValue();
		return _multiplicando * _multiplicador;
	}

	@Override
	public Number dividir(Number dividendo, Number divisor) {
		Integer _dividendo=dividendo.intValue();
		Integer _divisor=divisor.intValue();
		return _dividendo / _divisor;
	}

}
