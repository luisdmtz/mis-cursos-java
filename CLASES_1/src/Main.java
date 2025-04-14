
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Perro lassie = new Perro(); // INSTANCIAR CLASE
		
		lassie.establecerParametros("lassie", "collie", 3); // ARGUMENTOS

//		lassie.nombre = "lassie";
//		lassie.raza = "collie";
//		lassie.edad = 3;
		
		System.out.println("El nombre del perro es: " + lassie.nombre);
		System.out.println("La raza es: " + lassie.raza);
		System.out.println("Su edad es: " + lassie.edad);
		
		// MANDAR A LLAMAR LOS METODOS
		lassie.comer();
		lassie.dormir();
		lassie.ladrar();
	}

}
