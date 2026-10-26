package PSPU1Boletin.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AnalizadorTemperaturas {

	public static void main(String[] args) {
		AnalizadorTemperaturas at = new AnalizadorTemperaturas();
		
		String rutaLeer = args[0];
		int umbral = Integer.parseInt(args[1]);
		
		List<Integer> temps;
		try {
			temps = at.leerArchivo(rutaLeer);
			int supera = at.superaUmbral(temps, umbral);
			System.out.println(supera);
		
			at.escribeFichero(umbral, supera);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public List<Integer> leerArchivo(String ruta) throws FileNotFoundException {
		File f = new File(ruta);
		List<Integer> temps = new ArrayList<Integer>();
		
		FileReader fr = new FileReader(f);
		Scanner sc = new Scanner(fr);
		sc.useLocale(Locale.ENGLISH);

		while(sc.hasNextLine()) {
			String temp = sc.nextLine();
			temps.add(Integer.parseInt(temp));
		}
		return temps;
	}
	
	public int superaUmbral(List<Integer> temps, int umbral) {
		int contador = 0;
		for (Integer temp : temps) {
			if (temp >= umbral) {
				contador++;
			}
		}
		return contador;
	}
	
	public void escribeFichero(int umbral, int superado) throws FileNotFoundException {
		File file = new File("src/main/resources/" + umbral + ".txt");
		PrintWriter pw = new PrintWriter(file);
		pw.println("La temperatura de " + umbral + " se ha superado " + superado + " veces");
		pw.close();
	}
}
