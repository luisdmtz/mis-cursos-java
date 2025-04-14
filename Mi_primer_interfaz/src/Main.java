import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String nombre = JOptionPane.showInputDialog("Ingrese tu nombre");
		JOptionPane.showMessageDialog(null,"Hola " + nombre);
		
		int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingresa tu edad"));
		JOptionPane.showMessageDialog(null,"Tienes " + edad + " años");

		double altura = Double.parseDouble(JOptionPane.showInputDialog("Ingrese tu altura"));
		JOptionPane.showMessageDialog(null,"Mides " + altura + " centimetros");
	}

}
