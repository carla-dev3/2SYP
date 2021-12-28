package es.florida.AE4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Servidor implements Runnable {
	
	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;
	
	public Servidor(Socket socket) {
		super();
		this.socket = socket;
	}
	
	public static String encriptacion(String contraPlana) {
		
		int max = contraPlana.length();
		String contrasenyaEncriptada = " ";
		
		for(int i = 0; i < max; i++) {
			List<String> listaContrasenya = new ArrayList<String>();
			char caracter = contraPlana.charAt(i);
			int numero = (int) caracter;
			int suma = numero + 1;
			char nuevoCaracter = ((char) suma);
			
			if (Character.isWhitespace(nuevoCaracter)) {
				listaContrasenya.add("*");
			} else {
				listaContrasenya.add(Character.toString(nuevoCaracter));
			}
		}
		return contrasenyaEncriptada;
	}

	@Override
	public void run() {
		try {
			
			ObjectOutputStream outObjeto = new ObjectOutputStream(socket.getOutputStream());
			EncriptarContrasenya c = new EncriptarContrasenya(" ", " ");
			outObjeto.writeObject(c);
			
			System.err.println("SERVIDOR >> Conexi�n recibida!! ");
			
			ObjectInputStream inObjeto = new ObjectInputStream(socket.getInputStream());
			EncriptarContrasenya pMod = (EncriptarContrasenya) inObjeto.readObject();
			String contrasenyaPlana = pMod.getContrasenyaTexto();
			
			System.err.println("SERVIDOR >> Realiza la encriptaci�n ");
			String resultado = encriptacion(contrasenyaPlana);
			
			System.err.println("SERVIDOR >> Devuelve resultado ");
			OutputStream os = socket.getOutputStream();
			
			
			/*InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			bfr = new BufferedReader(isr);
			OutputStream os = socket.getOutputStream();
			pw = new PrintWriter(os);
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Env�o al cliente: ");
			String contrasenya = bfr.readLine();
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Realiza la operaci�n");
			//Integer resultado = calcular(linea, num1, num2);
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Devuelve el resultado");
			//pw.write(resultado.toString() + "\n");
			pw.flush();
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Espera nueva petici�n"); */
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Error");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}