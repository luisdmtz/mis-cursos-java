package org.example.streams;

import java.util.Arrays;
import java.util.List;

public class StreamNoneMatch {

	public static void main(String[] args) {
		
		List<String> names = Arrays.asList("Ana", "Luis", "Maria", "Pedro", "Juan", "Carla");
		
        // ❌ noneMatch(): Verifica si ningún elemento cumple una condición. 
		
		boolean result = names.stream() 
				.noneMatch( (name) -> { 
					return name.length() == 3;// es false porque Ana cumple con la condicion 
				});
		System.out.println(result); // y es verdadero si ninguno cumple con la condicion
		
        boolean noneStartsWithZ = names.stream()
                .noneMatch(name -> name.startsWith("Z")); // es true porque ninguno cumple con la condicion
        System.out.println("¿Ningún nombre empieza con Z?: " + noneStartsWithZ);
		
	}

}
