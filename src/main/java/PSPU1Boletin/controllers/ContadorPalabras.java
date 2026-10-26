package PSPU1Boletin.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ContadorPalabras {
	public static void main(String[] args) {
		ContadorPalabras cp = new ContadorPalabras();
		
		String ruta = args[0];
		String buscaPalabra = args[1];
		
		List<String> palabras = cp.leerFichero(ruta);
		
		int cuenta = cp.cuentaPalabras(palabras, buscaPalabra);
		
		System.out.println("La palabra " + buscaPalabra + " aparece " + cuenta + " veces");
	}
	
	
	public int cuentaPalabras(List<String> palabras, String buscaPalabra) {
		int cuenta = 0;
		for (String palabra : palabras) {
			if (palabra.equals(buscaPalabra)) {
				cuenta++;
			}
		}
		return cuenta;
	}
	
	public List<String> leerFichero (String ruta) {
		File f = new File(ruta);
		List<String> palabras = new ArrayList<String>();
		
		try {
			FileReader fr = new FileReader(f);
			Scanner sc = new Scanner(fr);

			while(sc.hasNext()) {
				String palabra = sc.next();
				palabras.add(palabra);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return palabras;
	}
}
