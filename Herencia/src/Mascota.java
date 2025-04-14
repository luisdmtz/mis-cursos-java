
public class Mascota {

	// ATRIBUTOS
	public String nombre;
	public String raza;
	public int edad;
	
	// METODO DORMIR - IMPRIMIR EN CONSOLA
	public void dormir() {
		System.out.println("La mascota duerme" );
	}
	
	// METODOS GET AND SET
	public String getNombre() {
		return nombre;
	}
	
	public String setNombre() {
		return this.nombre;
	}
	
	public String getRaza() {
		return raza;
	}
	
	public String setRaza() {
		return this.raza;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public int setEdad() {
		return this.edad;
	}
}
