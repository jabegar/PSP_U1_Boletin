package PSPU1Boletin.controllers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

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
			String[] palabras = {"servicio", "hilo", "proceso", "multihilo", "concurrencia"};
			for (int i = 0; i < palabras.length; i++) {
				lzc.ejecutaContadorPalabras(rutaClass, nombreClass, "src/main/resources/texto_servicio_hilo_proceso_multihilo_concurrencia.txt", palabras[i]);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void compilaContadorPalabras(String rutaCompila, String rutaClass, String nombreClass) throws IOException, InterruptedException {
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
	
	public void ejecutaContadorPalabras(String ruta, String nombreClass, String rutaArchivoTXT, String buscaPalabra) throws IOException, InterruptedException {
		String[] comando = {"java", "-cp", ruta, nombreClass, rutaArchivoTXT, buscaPalabra};
		ProcessBuilder pb = new ProcessBuilder(comando);
		pb.redirectErrorStream(true);
		pb.inheritIO();
		Process p = pb.start();
//		int exit = p.waitFor();
//		System.out.println("Codigo de salida: " + exit);
		
	}
}
