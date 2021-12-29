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

	/**
	 * Método: encriptacion Descripción: sustitución de carácteres de la contraseña
	 * por el carácter ASCII posterior de la tabla ASCII
	 * 
	 * @param contrasenyaPlana
	 * @return
	 */
	public static String encriptacion(String contrasenyaPlana) {

		int max = contrasenyaPlana.length();
		String contrasenya_encript = " ";
		List<String> listaContrasenya = new ArrayList<String>();
		for (int i = 0; i < max; i++) {

			char caracter = contrasenyaPlana.charAt(i);
			int numero = (int) caracter;
			int suma = numero + 1;
			char nuevoCaracter = ((char) suma);

			if (Character.isWhitespace(nuevoCaracter)) {
				listaContrasenya.add("*");
			} else {
				listaContrasenya.add(Character.toString(nuevoCaracter));
			}
		}
		for (String s : listaContrasenya) {
			contrasenya_encript = contrasenya_encript + s;
		}
		return contrasenya_encript;
	}

	@Override
	public void run() {

		try {

			ObjectOutputStream outObjeto = new ObjectOutputStream(socket.getOutputStream());
			EncriptarContrasenya c = new EncriptarContrasenya();
			outObjeto.writeObject(c);

			ObjectInputStream inObjeto = new ObjectInputStream(socket.getInputStream());
			EncriptarContrasenya pMod = (EncriptarContrasenya) inObjeto.readObject();
			String contrasenyaPlana = pMod.getContrasenyaTexto();

			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + ">> Contraseña del cliente: "
					+ contrasenyaPlana);
			Thread.sleep(2000);
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + ">> Realiza la encriptación ");
			String nuevaContrasenya = encriptacion(contrasenyaPlana);
			pMod.setContrasenyaEncriptada(nuevaContrasenya);

			Thread.sleep(2000);
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + ">> Devuelve el resultado");
			OutputStream os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write(nuevaContrasenya + "\n");
			pw.flush();
			Thread.sleep(2000);
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + ">>> Espera nueva peticion");
			System.out.println("SERVIDOR Hilo" + Thread.currentThread().getName()
					+ ">> Escoge una opcion de encriptado. 1. ASCII o 2. MD5");
			System.err.println("SERVIDOR Hilo" + Thread.currentThread().getName()
					+ ">> La opcion escogida por el cliente es la: ");

			pw.flush();
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Error");
		}
	}
}