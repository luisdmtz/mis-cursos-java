package org.example.streams;

import java.util.Arrays;
import java.util.List;

public class StreamDistinct {

	public static void main(String[] args) {
		
		List<String> names = Arrays.asList("Ana", "Ana", "Ana", "Ana", "Juan", "Carla");		
		
        // ✨ distinct(): Elimina los elementos duplicados.
		
        names.stream()
                .distinct() // elimina duplicados
                .forEach(System.out::println);
		
	}

}

