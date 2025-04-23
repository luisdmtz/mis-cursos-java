package org.example.streams;

import java.util.Arrays;
import java.util.List;

public class StreamReduce {
	
	public static void main(String[] args) {
		 /*
         * 🧮 BinaryOperator
         * 💡 Recibe dos valores del mismo tipo y retorna un valor del mismo tipo.
         */
		
		List<String> names = Arrays.asList("Ana", "Luis", "Maria", "Pedro", "Juan", "Carla");
		
		// 🔗 reduce(): Combina todos los elementos en un solo valor.
		String result = names.stream()
				.reduce("", (a,b) -> { //colocar identificador "" puede ir vacio, es un string que se le antepone al resultado de la cadena
					return a + " " + b;
				});
		System.out.println("Nombres concatenados: " + result);
		
		
		//"**** FORMA DEDUCIBLE :");
        String concatenatedNames = names.stream()
                .reduce("", (a, b) -> a + " " + b); //Operador: reduce = Expresion Lambda: BinaryOperator -> Recibe dos valores del mismo tipo y retorna un valor del mismo tipo.
        System.out.println("Nombres concatenados: " + concatenatedNames);

        
	}

}
