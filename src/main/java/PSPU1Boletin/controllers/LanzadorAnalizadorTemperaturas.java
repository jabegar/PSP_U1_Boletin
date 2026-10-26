package PSPU1Boletin.controllers;

import java.io.File;
import java.io.IOException;

public class LanzadorAnalizadorTemperaturas {

	public static void main(String[] args) {
		LanzadorAnalizadorTemperaturas lat = new LanzadorAnalizadorTemperaturas();
		String rutaCompila = "src/main/java/PSPU1Boletin/controllers/AnalizadorTemperaturas.java";
		String rutaClass = "target/classes";
		String nombreClass = "PSPU1Boletin.controllers.AnalizadorTemperaturas";
		int[] umbrales = {10, 20, 25, 30, 35};
		
		try {
			lat.compilaAnalizadorTemperaturas(rutaCompila, rutaClass, nombreClass);
			
			for (int umbral : umbrales) {
				lat.ejecutaContadorPalabras(rutaCompila, nombreClass, "src/main/resources/datos.txt", Integer.toString(umbral));				
			}
		} catch (IOException e) {
			System.err.print(e.getMessage());
		} catch (InterruptedException e) {
			System.err.print(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void compilaAnalizadorTemperaturas(String rutaCompila, String rutaClass, String nombreClass) throws IOException, InterruptedException {
		File clase = new File(rutaClass + nombreClass.replace(".", "/"));
		
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
	
	public void ejecutaContadorPalabras(String ruta, String nombreClass, String rutaLeer, String umbral) throws IOException, InterruptedException {
		String[] comando = {"java", "-cp", ruta, nombreClass, rutaLeer, umbral};
		ProcessBuilder pb = new ProcessBuilder(comando);
		pb.redirectErrorStream(true);
		pb.inheritIO();
		Process p = pb.start();
		int exit = p.waitFor();
		System.out.println("Codigo de salida: " + exit);
		
	}
}
