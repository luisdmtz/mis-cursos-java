package org.example.streams;

import java.util.Arrays;
import java.util.List;

public class StreamSorted {
	
	public static void main(String[] args) {
		
		  List<String> names = Arrays.asList("Ana", "Luis", "Maria", "Pedro", "Juan", "Carla");

	        // 📊 sorted(): Ordena los elementos del stream.
	  
	      names.stream()
	                .sorted() // ordena los elementos
	                .forEach(System.out::println);
	       
		  
	}
	
}
