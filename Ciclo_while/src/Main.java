
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

/*#######################################################################*/

	/*	char ch;
		
		//imprime el alfabeto con e bucle while de a->z
		ch = 'a';
		while(ch <= 'z') {
			System.out.println(ch);
			ch++;
		} */
/*#######################################################################*/
		// Uso de break para sair de un bucle
		int num;
		num = 100;
		
		//recorre e bucle si i al cuadrado es menor que num
		for(int i=0; i<num; i++) {
			if(i*i >=num) break; //termina el bucle si i*i >=100
			System.out.println(i+ " ");
		}
		System.out.println("Bucle completo.");
	}

}
