package com.diegobonninClase.ipoo.ejercicios;

public interface Calculadora {
	
	// por defecto, el modificador de acceso en una interaface
	// es public, no hace falta especificar
	
	Number sumar(Number sumando1, Number sumando2);
	Number restar(Number minuendo, Number sustraendo);
	Number multiplicar(Number multiplicando, Number multiplicador);
	Number dividir(Number dividendo, Number divisor);

}
