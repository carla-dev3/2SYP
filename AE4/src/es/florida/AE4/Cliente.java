package es.florida.AE4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		
		System.out.println("CLIENTE >> Arranca cliente");
		System.out.println("CLIENTE >> Conexion al servidor");
		
		
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introducir IP: ");
		String host = teclado.nextLine();
		System.out.println("Introducir puerto: ");
		int puerto = Integer.parseInt(teclado.nextLine());
		System.out.println("CLIENTE >> Arranca el cliente -> esperando mensaje del servidor... ");
		Socket cliente = new Socket(host, puerto);
		/* En el cliente primero recibimos los datos, al contrario que el servidor */
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		EncriptarContrasenya e = (EncriptarContrasenya) inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo del servidor: " + e.getContrasenyaTexto());
		System.out.println("CLIENTE >> Actualizar información del objeto... ");
		System.out.println("Introducir contraseña: ");
		String contrasenya = teclado.nextLine();
		e.setContrasenyaTexto(contrasenya);
		Thread.sleep(2000);
		System.out.println("CLIENTE >> Envio al servidor: " + e.getContrasenyaTexto());
		/* Le enviamos al cliente los datos*/
		ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
		outObjeto.writeObject(e);
		inObjeto.close();
		outObjeto.close();
		cliente.close();
		teclado.close();	
		
		
	/*	System.out.println("CLIENTE >> Arranca el cliente");
		System.out.println("CLIENTE >> Conexión establecida con el servidor");
		
		//InetSocketAddress direccion = new InetSocketAddress("localhost", 1234);

		//cliente.connect(direccion);
		Socket cliente = new Socket("localhost", 1234);
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		EncriptarContrasenya e = (EncriptarContrasenya) inObjeto.readObject();
		
	
		/* ---------------------------------------------------------------------- */
		
		/*System.out.println("Introduce la contraseña: ");
		@SuppressWarnings("resource")
		Scanner variable = new Scanner(System.in);
		String contra = variable.nextLine();
		
		c.setContrasenyaEncriptada(contra);
		
		ObjectOutputStream pMod = new ObjectOutputStream(socket.getOutputStream());
		pMod.writeObject(c);
		
		System.out.println("CLIENTE >> Preparado el canal para recibir encriptación");
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bfr = new BufferedReader(isr);
		
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introducir IP: ");
		String host = teclado.nextLine();
		System.out.println("Introducir puerto: ");
		int puerto = Integer.parseInt(teclado.nextLine());
		System.out.println("CLIENTE >> Arranca el cliente -> esperando mensaje del servidor... ");
		Socket cliente = new Socket(host, puerto);
		/* En el cliente primero recibimos los datos, al contrario que el servidor 
		ObjectInputStream inputObjeto = new ObjectInputStream(cliente.getInputStream());
		EncriptarContrasenya servidor = (EncriptarContrasenya) inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo del servidor: " + servidor.getContrasenyaTexto() + " - " + servidor.getContrasenyaEncriptada());
		System.out.println("CLIENTE >> Actualizar información del objeto... ");
		System.out.println("Introducir contraseña: ");
		String encript = teclado.nextLine();
		servidor.setContrasenyaEncriptada(encript);
		System.out.println("CLIENTE >> Envio al servidor: " + servidor.getContrasenyaEncriptada());
		/* Le enviamos al cliente los datos*/
		//ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
		/*outObjeto.writeObject(s);
		inObjeto.close();
		outObjeto.close();
		cliente.close();
		teclado.close();	 */
	}
}


