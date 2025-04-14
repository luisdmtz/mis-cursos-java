import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//HOLA MUNDO
		//System.out.print("hola mundo\n");
		
		//ENTRADA DE DATOS CON SCANNER
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Cual es tu nombre?");
		String nombre = scanner.nextLine();
		
		System.out.println("Cuantos años tienes?");
		int edad = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Cual es tu comida favorita?");
		String comida = scanner.nextLine();
		
		System.out.println("hola " + nombre);
		System.out.println("Tienes " + edad + " años");
		System.out.println("Te gusta la " + comida);

	}

}
