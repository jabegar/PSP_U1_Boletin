package PSPU1Boletin.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LanzadorAnalizadorTemperaturas {

	public static void main(String[] args) {
		LanzadorAnalizadorTemperaturas lat = new LanzadorAnalizadorTemperaturas();
		String rutaCompila = "src/main/java/PSPU1Boletin/controllers/AnalizadorTemperaturas.java";
		String rutaClass = "target/classes";
		String nombreClass = "PSPU1Boletin.controllers.AnalizadorTemperaturas";
		int[] umbrales = {10, 20, 25, 30, 35};
		
		try {
			lat.compilaAnalizadorTemperaturas(rutaCompila, rutaClass, nombreClass);
			
			List<Integer> dias = new ArrayList<Integer>();
			for (int umbral : umbrales) {
				dias.add(lat.ejecutaAnalizadorTemperaturas(rutaClass, nombreClass, "src/main/resources/datos.txt", Integer.toString(umbral)));				
			}
			
//			lat.leerFicheros(umbrales);
			lat.imprimirDias(umbrales, dias);
		} catch (FileNotFoundException e) {
			System.err.print(e.getMessage());
		} catch (InterruptedException e) {
			System.err.print(e.getMessage());
		} catch (IOException e) {
			System.err.print(e.getMessage());
		} catch(RuntimeException e) {
			System.err.print(e.getMessage());
		}
	}
	
	public void compilaAnalizadorTemperaturas(String rutaCompila, String rutaClass, String nombreClass) throws IOException, InterruptedException {
		File clase = new File(rutaClass + '/' + nombreClass.replace(".", "/"));
		
		if (!clase.exists()) {	
			ProcessBuilder pb = new ProcessBuilder("javac", "-d", rutaClass,  rutaCompila);
			Process p = pb.start();
			int exit = p.waitFor();
			
			System.out.println("Codigo de salida: " + exit);
		}
		else {
			System.out.println("El archivo ya esta compilado");
		}
	}
	
	public int ejecutaAnalizadorTemperaturas(String ruta, String nombreClass, String rutaLeer, String umbral) throws IOException, InterruptedException {
		String[] comando = {"java", "-cp", ruta, nombreClass, rutaLeer, umbral};
		ProcessBuilder pb = new ProcessBuilder(comando);

		Process p = pb.start();
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String linea = br.readLine();
		int dias = 0;
		while (linea != null) {
			System.out.println(linea);
			dias = Integer.parseInt(linea);
			linea = br.readLine();
		}
		
		BufferedReader brError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		String lineaError = brError.readLine();
		StringBuilder errMsg = new StringBuilder();
		while (lineaError != null) {
			errMsg.append(lineaError).append("\n");
			lineaError = brError.readLine();
		}
		
		int exit = p.waitFor();
		
		if (exit != 0) {
			throw new RuntimeException("Error en subproceso: " + errMsg.toString());
		}
		
		return dias;
		
	}
	
	public void leerFicheros(int[] temperaturas) throws IOException {
		System.out.println("Resultados:");
		int total = 0;
		
		for (int temp : temperaturas) {
			File f = new File("src/main/resources/" + temp + ".txt");
			BufferedReader br = new BufferedReader(new FileReader(f));
			int dias = br.read();
			System.out.println("Umbral " + temp + " -> " + dias + " días");
			br.close();
			total += dias;
		}
		System.out.println("Dias contados: " + total);
	}
	
	public void imprimirDias(int[] temperaturas, List<Integer> dias) {
		for (int i = 0; i < dias.size(); i++) {
			System.out.println("Umbral " + temperaturas[i] + " -> " + dias.get(i) + " días");
		}
	}
}
