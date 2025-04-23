package org.example.streams;

import java.util.Arrays;
import java.util.List;

public class StreamAllMatch {

	public static void main(String[] args) {

		List<String> names = Arrays.asList("Ana", "Luis", "Maria", "Pedro", "Juan", "Carla");
		
        // 🔒 allMatch(): Verifica si todos los elementos cumplen una condición.
		
		boolean result = names.stream()
				.allMatch( (name) -> name.equalsIgnoreCase("A")); // allMatch Recibe un valor y devuelve un booleano (true o false).
		System.out.println(result);
		
		
        boolean allHaveMoreThan3Letters = names.stream()
                .allMatch(name -> name.length() > 2); // FILTRO SI LA CANTIDAD DE CARACTER ES MAYOR DE 2
        System.out.println("¿Todos los nombres tienen más de 3 letras?: " + allHaveMoreThan3Letters);


	}

}
