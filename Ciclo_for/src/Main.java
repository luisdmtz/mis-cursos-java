import java.io.IOException;
import java.io.FileInputStream;

public class Main {
	
	public static void main(String[] args) {
		
		
/*#######################################################################*/		
		// Muestra raices cuadradas de 1 a 99 y error de redondeo
	/*	double num, rcuad, erred;
		for(num = 1.0; num < 100.0; num++) {
			rcuad = Math.sqrt(num);
			System.out.println("La raiz cuadrada de " + num + " es " + rcuad);
			
	    // Calcula el error de redondeo
			erred = num - (rcuad * rcuad);
			System.out.println("El error de redondeo es " + erred);
			System.out.println();
		} */
		
/*#######################################################################*/
		// Bucles sin cuerpo
	/*	int i;
		int suma = 0;
		
		//suma los numeros hasta 5
		for(i = 1; i<= 5; suma += i++);
		System.out.println("La suma es " + suma); */
		
/*#######################################################################*/
		
		
		// Ejecucucion negativa de un bucle
		int x;
		
		for(x = 100; x > -100; x -= 5)
			System.out.println(x);
		
	}
}
