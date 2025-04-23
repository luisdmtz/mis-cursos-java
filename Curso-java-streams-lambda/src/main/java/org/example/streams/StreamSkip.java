package org.example.streams;

import java.util.Arrays;
import java.util.List;

public class StreamSkip {

	public static void main(String[] args) {
		
		List<String> names = Arrays.asList("Ana", "Luis", "Maria", "Pedro", "Juan", "Carla");
		
        // 🔄 skip(): Omite un número específico de elementos.
        names.stream()
                .skip(2) // omite elementos y muestra de 0 a 2
                .forEach(System.out::println);
	}

}
