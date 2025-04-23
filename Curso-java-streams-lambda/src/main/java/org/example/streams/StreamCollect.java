package org.example.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamCollect {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Ana", "Luis", "Maria", "Pedro", "Juan", "Carla");

        // 📦 collect(): Recoge los elementos en una colección.
		
		/*List<String> result = names.stream()
				.map( (name) -> { 
					return name.toUpperCase(); // convertir todos a mayusculas, ahora necesito que estos elementos se guarden en otra lista
				}).collect(Collectors.toList());
		result.stream().forEach(System.out::println); */
		
		
		List<String> result = names.stream()
				.map( (name) -> name.toUpperCase()) // convertir todos a mayusculas, ahora necesito que estos elementos se guarden en otra lista
				.collect(Collectors.toList());
		result.stream().forEach(System.out::println);
		
		//"**** FORMA DEDUCIBLE :");
        List<String> namesStartingWithA = names.stream()
                .filter(name -> name.startsWith("C")) // filtrar con caracter
                .collect(Collectors.toList());
        System.out.println("Nombres que empiezan con A: " + namesStartingWithA);

	}

}

