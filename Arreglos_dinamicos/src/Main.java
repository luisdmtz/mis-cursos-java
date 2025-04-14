import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		/*// EJEMPLO 1
		
		//ArrayList es una matriz de capacidad ilimitada
		//.add = agregar elementos, 
		//.remove = eliminar elemento, 
		//.size = cambiar el tamaño de la matriz
		
		//arreglo de tipo string con list
		List<String> animales = new ArrayList<>();
		
		//Agregar elementos al array
		animales.add("Leon");
		animales.add("Tigre");
		animales.add("Gato");
		animales.add("Perro");
		
		System.out.println("Primer array: " + animales);
		//podemos integrar otro dato y posicionarlo al array fila 2 
		animales.add(2,"Elefante");
		
		System.out.println("Segundo array: " + animales);

		
		*/
		
		//EJEMPLO 2
		
		List<String> lenguajeProgramacion = new ArrayList<String>();
		
		lenguajeProgramacion.add("C++");
		lenguajeProgramacion.add("Pyton");
		lenguajeProgramacion.add("Java");
		lenguajeProgramacion.add("Rubi");
		
		System.out.println("Array 1 " + lenguajeProgramacion);
		
		//remover elemento del indice 3
		
		lenguajeProgramacion.remove(3);
		
		System.out.println("Array 2 sin el indice 3 " + lenguajeProgramacion);

	}

}
