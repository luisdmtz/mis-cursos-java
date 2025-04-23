package org.example.streams;

import java.util.Arrays;
import java.util.List;

public class StreamFilter {
	
	public static void main(String[] arg) {
		
        List<String> names = Arrays.asList("Ana", "Luis", "Maria", "Pedro", "Juan", "Carla");

        // 🎯 filter(): Filtra los elementos que cumplen una condición. OPERADOR NO FINAL->terminar con un operador final
        names.stream()
        		.filter((name)->{ // operador no final
        		return name.length() > 4;
        		})
        		.forEach((name) -> { // se debe terminar con uno: como foreach
        			System.out.println(name);
        		});
        
        // forma deducible
        names.stream()
                .filter(name -> name.length() > 4) // filter -> Predicate = Recibe un valor y devuelve un booleano (true o false)
                .forEach(System.out::println);
        
	}
}
