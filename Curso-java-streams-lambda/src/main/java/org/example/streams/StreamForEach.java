package org.example.streams;

import java.util.Arrays;
import java.util.List;

public class StreamForEach {
	public static void main(String[] args) {
        /*
         * 🛠️ LAMDA Consumer
         * 💡 Recibe un valor y no retorna nada.
         */
		
		List<String> names = Arrays.asList("Ana", "Luis", "Maria", "Pedro", "Juan", "Carla");
			
		// 💥 forEach(): Aplica una acción a cada elemento. 
		names.stream()
				.forEach((name) -> { // forEach=Consumer - Recibe un valor y no retorna nada.
					System.out.println("Nombre1: " + name);
				});
		
		// forma deducible:
        names.stream()
                .forEach(name -> System.out.println("Nombre2: " + name));
		
	}
}
