package es.florida.AE2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Colision {

	/**
	 * Metodo: probabilidadColision
	 * Descripcion: calcular la probabilidad de que el NEO colisione con la tierra y guardarla en ficheros independientes. También avisa de
	 * las alertas dependiendo la probabilidad de colision
	 * 
	 *  @param posicionNEO, velocidadNEO
	 */
    public double probabilidadColision(double posicionNEO, double velocidadNEO) {
        double posicionTierra = 1;
        double velocidadTierra = 100;
        long inicio = System.nanoTime();

        for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
            posicionNEO = posicionNEO + velocidadNEO * i;
            posicionTierra = posicionTierra + velocidadTierra * i;
        }

        double resultado = 100 * Math.random() * Math.pow(((posicionNEO - posicionTierra) / (posicionNEO + posicionTierra)), 2);
        BigDecimal bigDecimal = new BigDecimal(resultado).setScale(2, RoundingMode.UP);
        
        long fin = System.nanoTime();
        double form = (double) (fin - inicio) * 1.0e-9;
        System.out.println("\n El tiempo total de ejecución es de " + form + " segundos");
        
        if (resultado > 10) {
            System.out.println("\nALERTA MUNDIAL! Alta probabilidad de colisión");
        } else {
            System.err.println("\nBaja probabilidad de colisión. No hay peligro");
        }

        return bigDecimal.doubleValue();
    }

	public static void main(String[] args) {
		 	Colision colision = new Colision();
	        double posicionTierra = Float.parseFloat(args[0]);
	        double velocidadTierra = Float.parseFloat(args[1]);
	        FileWriter fichero = null;
	        PrintWriter printer = null;
	        try {
	            fichero = new FileWriter(args[2]);
	            printer = new PrintWriter(fichero);
	            double resultado = colision.probabilidadColision(posicionTierra, velocidadTierra);
	            printer.println(resultado);
	            System.out.println("La probabilidad de colisión es de " + resultado);
	        } catch (Exception e) {
	            e.printStackTrace();

	        } finally {
	            try {
	                if (null != fichero)
	                    fichero.close();
	            }catch (IOException e2) {
	                e2.printStackTrace();
	            }
	      }
	}
}
