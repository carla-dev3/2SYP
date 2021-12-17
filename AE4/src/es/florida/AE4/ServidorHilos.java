package es.florida.AE4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorHilos {
	

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		ServerSocket socketEscucha;
		try {
			socketEscucha = new ServerSocket(1234);
		} catch (IOException e) {
			System.err.println("SERVIDOR >> Error");
			return;
		}
		
		while (true) {
			Socket conexion = socketEscucha.accept();
			
			System.err.println("SERVIDOR >> Conexion recibida >>> Lanzamos el hilo");
			Servidor s = new Servidor(conexion);
			Thread hilo = new Thread(s);
			hilo.start();
		}
	}

}
