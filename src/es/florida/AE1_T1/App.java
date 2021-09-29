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
				
		}
	}
	public static void sayHello() {
		System.out.println("Hola Mundo");
	}

}
