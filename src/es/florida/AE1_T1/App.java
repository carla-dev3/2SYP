package es.florida.AE1_T1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws IOException, InterruptedException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		System.out.println("N�mero de ejercicio:");
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
		case 5:
			mayor();
			break;
		case 6:
			inverso();
			break;
		case 7:
			experiencia();
			break;
		case 8:
			esPrimo();
			break;
		default:
			System.out.println("Introduce un n�mero entre el 1 y 8 para elegir ejercicio");

		}
	}

	/**
	 * Metodo: sayHello 
	 * Descripci�n: m�todo que devuelve Hola Mundo
	 * 
	 * @param no
	 **/
	public static void sayHello() {
		System.out.println("Hola Mundo");
	}

	/**
	 * Metodo: array 
	 * Descripci�n: m�todo que imprime los nombres introducidos de forma consecutiva
	 * 
	 * @param no
	 **/
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

	/**
	 * Metodo: suma 
	 * Descripci�n: m�todo que suma los n�meros pares hasta llegar al que se establece por par�metro
	 * 
	 * @param num Este par�metro es el n�mero que se fija para detener la suma
	 **/
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

	/**
	 * Metodo: factorial 
	 * Descripci�n: m�todo que devuelve el factorial del n�mero 15
	 * 
	 * @param no
	 **/
	public static void factorial() {
		System.out.println("EJERCICIO 4");
		System.out.println("---------------");
		long factorial = 1;

		for (int i = 2; i <= 15; i++) {
			factorial *= i;
		}

		System.out.println("El factorial del n�mero " + Integer.toString(15) + " es el: " + Long.toString(factorial));
	}

	/**
	 * Metodo: mayor 
	 * Descripci�n: m�todo que devuelve el mayor n�mero que hay en el array
	 * 
	 * @param no
	 **/
	public static void mayor()
	{
		System.out.println("EJERCICIO 5");
		System.out.println("---------------");
		int[] numeros = new int[] { 28, 50, 40, 200, 20, 44, 100, 153 };
		int mayor = numeros[0];
		for (int x = 1; x < numeros.length; x++) {
			if (numeros[x] > mayor) {
				mayor = numeros[x];
			}
		}
		System.out.println("El n�mero mayor dentro del array es: " + mayor);
	}

	/**
	 * Metodo: inverso 
	 * Descripci�n: m�todo que devuelve 5 n�meros de manera inversa y la suma de ellos
	 * 
	 * @param no
	 **/
	public static void inverso() {
		System.out.println("EJERCICIO 6");
		System.out.println("---------------");
		int i = 1, lugar, resultado = 0;
		List<Integer> datos = new ArrayList<Integer>();

		for (int j = 0; j < 5; j++) {
			System.out.println("N�mero: " + i);
			Scanner numeros = new Scanner(System.in);
			int numero = numeros.nextInt();
			datos.add(numero);
			i++;
		}

		lugar = datos.size() - 1;

		do {
			System.out.print(datos.get(lugar));
			lugar--;
		} while (lugar != -1);

		for (int j = 0; j < datos.size(); j++) {
			resultado = resultado + datos.get(j);
		}
		System.out.println(" La suma total es: " + resultado);
	}

	/**
	 * Metodo: experiencia 
	 * Descripci�n: m�todo que devuelve el puesto de trabajo y el salario en funci�n de los a�os de experiencia
	 * 
	 * @param no
	 **/
	public static void experiencia() throws IOException {
		System.out.println("EJERCICIO 7");
		System.out.println("---------------");
		Scanner teclado = new Scanner(System.in);
		System.out.println("Indica los a�os de experiencia: ");
		int anyos = teclado.nextInt();

		if (anyos < 1) {
			System.out.println("Desarrollador Junior L1 � 15000-18000");
		} else if (anyos >= 1 && anyos <= 2) {
			System.out.println("Desarrollador Junior L2 � 18000-22000");
		} else if (anyos >= 3 && anyos <= 5) {
			System.out.println("Desarrollador Senior L1 � 22000-28000");
		} else if (anyos >= 6 && anyos <= 8) {
			System.out.println("Desarrollador Senior L2 � 28000-36000");
		} else if (anyos > 8) {
			System.out.println("Analista / Arquitecto. Salario a convenir en base a rol");
		}
		teclado.close();
	}

	/**
	 * Metodo: esPrimo 
	 * Descripci�n: m�todo que devuelve un rango de n�meros e indica si es primo o no es primo. Tambi�n el tiempo total de ejecuci�n
	 * 
	 * @param no
	 **/
	public static void esPrimo() throws InterruptedException {
		long empieza = System.currentTimeMillis();
		System.out.println("EJERCICIO 8");
		System.out.println("---------------");
		Scanner teclado = new Scanner(System.in);
		int inicio, fin;

		System.out.print("Indica el valor en que inicia el rango: ");
		inicio = teclado.nextInt();
		System.out.print("Indica el valor en que finaliza el rango: ");
		fin = teclado.nextInt();

		int numero = 0;
		int cont = 0;
		for (int i = inicio; i <= fin; i++) {
			if (primo(i)) {
				System.out.println("El numero " + i + " es primo");
			} else {
				System.out.println("El numero " + i + " no es primo");
			}
		}

		long termina = System.currentTimeMillis();
		double tiempo = (double) ((termina - empieza) / 1000);

		System.out.println("El tiempo total de ejecuci�n son: " + tiempo + " segundos");
	}

	/**
	 * M�todo: primo 
	 * Descripci�n: m�todo que comprueba si el n�mero es primo o no y este se le pasa al m�todo esPrimo
	 * 
	 * @param numero Este par�metro hace referencia a los n�meros del rango
	 **/
	public static boolean primo(int numero) {

		if (numero == 0 || numero == 1 || numero == 4) {
			return false;
		}
		for (int x = 2; x < numero / 2; x++) {

			if (numero % x == 0)
				return false;
		}
		return true;
	}	
}

