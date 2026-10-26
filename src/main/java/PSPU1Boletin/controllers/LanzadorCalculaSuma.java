package PSPU1Boletin.controllers;

import java.io.File;
import java.io.IOException;

public class LanzadorCalculaSuma {
	public static void main(String[] args) {
		LanzadorCalculaSuma lzc = new LanzadorCalculaSuma();
		String rutaCompila = "src/main/java/PSPU1Boletin/controllers/CalculaSuma.java";
		String rutaClass = "target/classes";
		String nombreClass = "PSPU1Boletin.controllers.CalculaSuma";
		
		try {
			lzc.compilaCalculaSuma(rutaCompila, rutaClass, nombreClass);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			lzc.ejecutaCalculaSuma(rutaClass, nombreClass, "par", "10");
			lzc.ejecutaCalculaSuma(rutaClass, nombreClass, "impar", "10");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void compilaCalculaSuma(String rutaCompila, String rutaClass, String nombreClass) throws IOException, InterruptedException {
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
	
	public void ejecutaCalculaSuma(String ruta, String nombreClass, String par, String max) throws IOException, InterruptedException {
		String[] comando = {"java", "-cp", ruta, nombreClass, par, max};
		ProcessBuilder pb = new ProcessBuilder(comando);
		pb.redirectErrorStream(true);
		pb.inheritIO();
		Process p = pb.start();
		int exit = p.waitFor();
		System.out.println("Codigo de salida: " + exit);
		
	}
}
