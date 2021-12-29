package es.florida.AE4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		
		
		System.out.println("CLIENTE >> Arranca cliente");
		System.out.println("CLIENTE >> Conexion al servidor");
		
		InetSocketAddress direccion = new InetSocketAddress("localhost", 1234);
		Socket socket = new Socket();
		socket.connect(direccion);
		
		ObjectInputStream inObjeto = new ObjectInputStream(socket.getInputStream());
		EncriptarContrasenya c = (EncriptarContrasenya) inObjeto.readObject();
		
		System.out.println("Introduce contraseña: ");
		Scanner variable = new Scanner(System.in);
		String contrasenya = variable.nextLine();
		
		c.setContrasenyaTexto(contrasenya);
	
		ObjectOutputStream pMod = new ObjectOutputStream(socket.getOutputStream());
		pMod.writeObject(c);
		System.out.println("CLIENTE >> Enviando al servidor la contraseña: " + contrasenya);
		
		
		System.out.println("CLIENTE >> Preparado canal para recibir encriptacion");
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bfr = new BufferedReader(isr);
		
		Thread.sleep(2000);
		String resultado = bfr.readLine();
		System.out.println("CLIENTE >> La contraseña encriptada es: " + resultado);
		
		Thread.sleep(2000);
		System.out.println("CLIENTE >> La opcion que esoges es: ");
		Scanner opcion = new Scanner(System.in);
		String contrasenya_opcion = opcion.nextLine();
		
		
		socket.close(); 
	}
}


