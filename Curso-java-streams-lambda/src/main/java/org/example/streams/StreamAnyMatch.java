package org.example.streams;

import java.util.Arrays;
import java.util.List;

public class StreamAnyMatch {

	public static void main(String[] args) {

		List<String> names = Arrays.asList("Ana", "Luis", "Maria", "Pedro", "Juan", "Carla");
		
        // 🔍 anyMatch(): Verifica si algún elemento cumple una condición.
		
		boolean result = names.stream()
				.anyMatch( (name) -> { // 💡 Recibe un valor y devuelve un booleano (true o false).
					return name.startsWith("Juan"); // retorna un valor boolean: true o false
				});
		System.out.println(result);
				
		//"**** FORMA DEDUCIBLE Y FILTRO CON UN CARACTER :");		
        boolean anyStartsWithP = names.stream()
                .anyMatch(name -> name.startsWith("P"));
        System.out.println("¿Hay algún nombre que empiece con P?: " + anyStartsWithP);

	}

}
