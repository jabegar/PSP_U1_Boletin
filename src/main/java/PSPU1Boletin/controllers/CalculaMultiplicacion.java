package PSPU1Boletin.controllers;

public class CalculaMultiplicacion {

	public static void main(String[] args) {
		CalculaMultiplicacion cm = new CalculaMultiplicacion();
		if (args.length == 2) {
			int total = cm.calculaMultiplicacion(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			System.out.println(total);
		}
		else {
			throw new IllegalArgumentException("No hay los dos argumentos requeridos");
		}

	}
	
	public int calculaMultiplicacion(int n1, int n2) {
		int total = 1;
		for (int i = n1; i <= n2; i++) {
			total *= i;
		}
		
		return total;
	}
}
