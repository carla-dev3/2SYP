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
	
	
	@Override
	public void run() {
		try {
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			bfr = new BufferedReader(isr);
			OutputStream os = socket.getOutputStream();
			pw = new PrintWriter(os);
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Envío al cliente: ");
			String linea = bfr.readLine();		
			String contrasenya = bfr.readLine();
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Realiza la operación");
			//Integer resultado = calcular(linea, num1, num2);
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Devuelve el resultado");
			//pw.write(resultado.toString() + "\n");
			pw.flush();
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Espera nueva petición");
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Error");
		}
	} 
}

