package es.florida.AE1_T1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		System.out.println("Número de ejercicio:");
		String numString = br.readLine();
		int num = Integer.parseInt(numString);

		switch (num) {
			case 1:
				sayHello();
				break;
			case 2:
				array();
				break;
			case 3:
				suma(15);
				break;
			case 4:
				factorial();
				break;
				
		}
	}
	public static void sayHello() {
		System.out.println("Hola Mundo");
	}
	
	public static void array() {
		String[] nombresClase = new String[6];
		String nombre;
		int i = 0;

		System.out.println("EJERCICIO 2-A");
		System.out.println("---------------");
		System.out.println("Dime los 6 nombres: ");
		do {
			Scanner teclado = new Scanner(System.in);
			nombre = teclado.nextLine();
			nombresClase[i] = nombre;
			i++;
		} while (i < 6);

		for (int x = 0; x < nombresClase.length; x++) {
			System.out.println(nombresClase[x]);
		}

		System.out.println("EJERCICIO 2-B");
		System.out.println("---------------");
		System.out.println("Dime los 6 nombres: ");
		ArrayList<String> nombreClase2 = new ArrayList<>();
		for (int j = 0; j < 6; j++) {
			Scanner teclado = new Scanner(System.in);
			nombre = teclado.nextLine();
			nombreClase2.add(nombre);
		}
		Iterator<String> itr = nombreClase2.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
	
	private static int suma(int num) {
		System.out.println("EJERCICIO 3");
		System.out.println("---------------");

		int suma = 0;
		for (int m = 1; m <= num; m++) {
			if (m % 2 == 0) {
				suma += m;
			}
		}
		return suma;
	}
	
	public static void factorial() {
		System.out.println("EJERCICIO 4");
		System.out.println("---------------");
		long factorial = 1;

		for (int i = 2; i <= 15; i++) {
			factorial *= i;
		}

		System.out.println("El factorial del número " + Integer.toString(15) + " es el: " + Long.toString(factorial));
	}

}
