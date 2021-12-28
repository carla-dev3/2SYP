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
		
		
	/*	String[] texto = {"contraseña", "ContraseñaEncript"};
		
		System.out.println("-> Arranca el cliente");
		System.out.println("-> Conexión con el servidor");
		Scanner teclado = new Scanner(System.in);
		System.out.print("Introduce IP: ");
		String host = teclado.nextLine();
		InetSocketAddress direccion = new InetSocketAddress(host, 1234);
		Socket socket = new Socket();
		socket.connect(direccion);
		
		System.out.println("-> Envio a cliente: " + texto[1] + " - " + texto[2]);
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		pw.print(texto[0] + "\n");
		pw.print(texto[1] + "\n");
		pw.flush();
		
		System.out.println("-> Preparado el canal para recibir el resultado");
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bfr = new BufferedReader(isr);
		String resultado = bfr.readLine();
		System.out.println("-> Recibe resultado: " + resultado);
		
		socket.close(); */
		
		System.out.println("CLIENTE >> Arranca cliente");
		System.out.println("CLIENTE >> Conexion al servidor");
		
		InetSocketAddress direccion = new InetSocketAddress("localhost", 1234);
		Socket socket = new Socket();
		socket.connect(direccion);
		
		ObjectInputStream inObjeto = new ObjectInputStream(socket.getInputStream());
		EncriptarContrasenya c = (EncriptarContrasenya) inObjeto.readObject();
		
		System.out.println("Dime la contraseña: ");
		Scanner variable = new Scanner(System.in);
		String contrasenya = variable.nextLine();
		
		c.setContrasenyaTexto(contrasenya);
		
		ObjectOutputStream pMod = new ObjectOutputStream(socket.getOutputStream());
		pMod.writeObject(c);
		
		System.out.println("CLIENTE >> Preparado canal para recibir encriptacion");
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bfr = new BufferedReader(isr);
		
		String resultado = bfr.readLine();
		System.out.println("CLIENTE >> Recibe resultado: " + resultado);

		
		socket.close();
		
		
		
		/*Scanner teclado = new Scanner(System.in);
		System.out.println("Introducir IP: ");
		String host = teclado.nextLine();
		System.out.println("Introducir puerto: ");
		int puerto = Integer.parseInt(teclado.nextLine());
		System.out.println("CLIENTE >> Arranca el cliente -> esperando mensaje del servidor... ");
		Socket cliente = new Socket(host, puerto);
		// En el cliente primero recibimos los datos, al contrario que el servidor 
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		EncriptarContrasenya e = (EncriptarContrasenya) inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo del servidor: " + e.getContrasenyaTexto() + " - " + e.getContrasenyaEncriptada());
		System.out.println("CLIENTE >> Actualizar información del objeto... ");
		System.out.println("Introducir contraseña: " + "\n");
		String contrasenya = teclado.nextLine();
		e.setContrasenyaTexto(contrasenya);
		Thread.sleep(2000);
		System.out.println("CLIENTE >> Envio al servidor: " + e.getContrasenyaTexto() + " - " + e.getContrasenyaEncriptada());
		ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
		outObjeto.writeObject(e);
		inObjeto.close();
		outObjeto.close();
		cliente.close();
		teclado.close();	*/ 
	}
}


