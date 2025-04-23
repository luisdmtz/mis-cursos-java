package org.example.streams;

import java.util.Arrays;
import java.util.List;

public class StreamLimit {

	public static void main(String[] args) {
		
		List<String> names = Arrays.asList("Ana", "Luis", "Maria", "Pedro", "Juan", "Carla");
		
        // 🎚️ limit(): Limita el número de elementos procesados.
		
        names.stream()
                .limit(3) // filtra limite de elementos
                .forEach(System.out::println);
        
	}

}

