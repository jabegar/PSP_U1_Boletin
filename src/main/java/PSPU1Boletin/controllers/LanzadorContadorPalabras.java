package PSPU1Boletin.controllers;

import java.io.File;
import java.io.IOException;

public class LanzadorContadorPalabras {
	public static void main(String[] args) {
		LanzadorContadorPalabras lzc = new LanzadorContadorPalabras();
		String rutaCompila = "src/main/java/PSPU1Boletin/controllers/ContadorPalabras.java";
		String rutaClass = "target/classes";
		String nombreClass = "PSPU1Boletin.controllers.ContadorPalabras";
		
		try {
			lzc.compilaContadorPalabras(rutaCompila, rutaClass, nombreClass);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			lzc.ejecutaContadorPalabras(rutaClass, nombreClass, "src/main/resources/texto_servicio_hilo_proceso_multihilo_concurrencia.txt", "servicio");
			lzc.ejecutaContadorPalabras(rutaClass, nombreClass, "src/main/resources/texto_servicio_hilo_proceso_multihilo_concurrencia.txt", "hilo");
			lzc.ejecutaContadorPalabras(rutaClass, nombreClass, "src/main/resources/texto_servicio_hilo_proceso_multihilo_concurrencia.txt", "proceso");
			lzc.ejecutaContadorPalabras(rutaClass, nombreClass, "src/main/resources/texto_servicio_hilo_proceso_multihilo_concurrencia.txt", "multihilo");
			lzc.ejecutaContadorPalabras(rutaClass, nombreClass, "src/main/resources/texto_servicio_hilo_proceso_multihilo_concurrencia.txt", "concurrencia");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void compilaContadorPalabras(String rutaCompila, String rutaClass, String nombreClass) throws IOException, InterruptedException {
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
	
	public void ejecutaContadorPalabras(String ruta, String nombreClass, String par, String max) throws IOException, InterruptedException {
		String[] comando = {"java", "-cp", ruta, nombreClass, par, max};
		ProcessBuilder pb = new ProcessBuilder(comando);
		pb.redirectErrorStream(true);
		pb.inheritIO();
		Process p = pb.start();
		int exit = p.waitFor();
		System.out.println("Codigo de salida: " + exit);
		
	}
}
