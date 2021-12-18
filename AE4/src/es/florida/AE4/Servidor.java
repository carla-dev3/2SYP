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
			String linea = bfr.readLine();
			System.err.println("SERVIDOR >>> Lee datos para la operacion");
			String contrasenya = bfr.readLine();
			System.err.println("SERVIDOR >>> Realiza la operacion");
			//Integer result = calcular(linea, num1, num2);
			System.err.println("SERVIDOR >>> Devuelve resultado");
			//pw.write(result.toString() + "\n");
			pw.flush();
			System.err.println("SERVIDOR >>> Espera nueva peticion");
			} catch (IOException e) {
			e.printStackTrace();
			System.err.println("SERVIDOR >>> Error.");
			}
	}
}


