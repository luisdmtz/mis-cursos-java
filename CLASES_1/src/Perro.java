
public class Perro {
	
	String nombre;
	String raza;
	int edad;
	
	//PARAMETROS
	void establecerParametros(String nombre, String raza, int edad) {
		this.nombre = nombre;
		this.raza = raza;
		this.edad = edad;
	}
	
	//METODOS
	void comer() {
		System.out.println("El perro esta comiendo!");
	}
	
	void dormir() {
		System.out.println("El perro esta durmiendo!");
	}
	
	void ladrar() {
		System.out.println("El perro esta ladrando!");
	}
	

}
