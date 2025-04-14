
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Switch con int como condicion
		Integer dia = 9;
		switch (dia) {
		    case 1:
		        System.out.println("Domingo");
		        break;
		    case 2:
		        System.out.println("Lunes");
		        break;
		    case 3:
		        System.out.println("Martes");
		        break;
		    case 4:
		        System.out.println("Miercoles");
		        break;
		    case 5:
		        System.out.println("Jueves");
		        break;
		    case 6:
		        System.out.println("Viernes");
		        break;
		    case 7:
		        System.out.println("Sabado");
		        break;
		     default: 
		    	 System.out.println("No es un dia de la semana");
		}


	}

}
