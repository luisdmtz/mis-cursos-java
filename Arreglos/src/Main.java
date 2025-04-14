
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		int[][]numeros = new int[3][3];
		int i, j; //i =filas y j = columnas
		
		//recorrer filas
		for(i = 0; i < numeros.length; i++) {
			System.out.println();
			
			for(j =0; j < numeros.length; j++) {
				//imprime el valor de la matriz en la posicion actual
				System.out.print(numeros[i][j] +  "");
			}
		}
		*/
		
		int[][]matriz = new int[4][4];
		matriz[0][0] = 4;
		matriz[0][1] = 4;
		matriz[1][0] = 6;
		matriz[1][1] = 7;
		
		int filas, columnas; //filas y columnas
		
		// recorrer filas
		for(filas = 0; filas < matriz.length; filas++) {
			System.out.println("");
			
			//recorrer columnas
			for(columnas = 0; columnas < matriz.length; columnas++) {
				System.out.print(matriz[filas][columnas] + " ");
			}
		}
		

	}

}
