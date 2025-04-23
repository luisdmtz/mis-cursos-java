package org.example.streams;

import java.util.Arrays;
import java.util.List;

public class StreamMap {
	
	public static void main(String[] args) {
		
		List<String> names = Arrays.asList("Ana", "Luis", "Maria", "Pedro", "Juan", "Carla");
		  
        // 🚀 map(): Transforma los elementos aplicando una función.
		names.stream()
				.map((name) -> { //Operador no final -> se debe terminar con uno: como foreach
					return name.toLowerCase(); // transforma a minusculas
				})
				.forEach((name) -> {
					System.out.println(name);
				});
		
		System.out.println("**** FORMA DEDUCIBLE :");
        names.stream()
                .map(String::toUpperCase) // transforma a mayusculas
                .forEach(System.out::println);
        
	}

}
