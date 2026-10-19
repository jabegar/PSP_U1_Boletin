package PSPU1Boletin.controllers;

public class CalculaSuma {
	public static void main(String[] args) {
		CalculaSuma cs = new CalculaSuma();
		if (args.length == 2) {
			cs.calculaSuma(args[0], Integer.parseInt(args[1]));
		}
		else {
			throw new IllegalArgumentException("No hay los dos argumentos requeridos");
		}
	}
	
	public void calculaSuma(String par, int max) {
		if (par.equals("par")) {
			for (int i = 0; i <= max; i+=2) {
				System.out.println(i);
			}
		}
		else {
			for (int i = 1; i <= max; i+=2) {
				System.out.println(i);
			}
		}
	}
}
