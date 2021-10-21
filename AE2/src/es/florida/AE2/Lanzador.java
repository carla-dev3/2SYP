package es.florida.AE2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lanzador {
	
	/** 
	 * Metodo: lanzar
	 * Descripcion: lectura del fichero de NEOs el cual separamos por comas
	 * 
	 * @param posicionNEO, velocidadNEO, ficheroResultado
	 */

	 static void lanzar(String posicionNEO, String velocidadNEO, String ficheroResultado) {
	        String clase = "es.florida.AE2.Colision";
	        try {
	            String javaHome = System.getProperty("java.home");
	            String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
	            String classpath = System.getProperty("java.class.path");
	            String className = clase;

	            ArrayList<String> command = new ArrayList<>();
	            command.add(javaBin);
	            command.add("-cp");
	            command.add(classpath);
	            command.add(className);
	            command.add(posicionNEO.toString());
	            command.add(velocidadNEO.toString());
	            command.add(ficheroResultado.toString());

	            ProcessBuilder builder = new ProcessBuilder(command);
	            Process process = builder.inheritIO().start();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	   }
	 public static void main(String[] args) throws IOException {
	        Lanzador lanzador = new Lanzador();
	        long inicio = System.nanoTime();
	        int cores = Runtime.getRuntime().availableProcessors();
	        System.out.println("El numero de cores es de " + cores);
	        
	        try {
	            File file = new File("NEOs.txt");
	            FileReader fr = new FileReader(file);
	            BufferedReader br = new BufferedReader(fr);
	            String linea;

	            for(int i = 0; i < cores; i++) {
	                linea = br.readLine();
	                System.out.println(linea);
	                String[] NEO = linea.split(",");
	                String ficheroResultado = NEO[0];
	                lanzar(NEO[1], NEO[2], ficheroResultado);
	            }
	        } catch (Exception e) {

	        } finally {
	        	
	        }
	 }
}
