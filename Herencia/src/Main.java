
public class Main {

	public static void main(String[] args) {

		//instanciar las clases gato y perro
		Gato gato = new Gato("gato", "raza", 2);
		Perro perro = new Perro("perro", "raza", 3);
		
		gato.dormir();
		perro.dormir();
	}

}
