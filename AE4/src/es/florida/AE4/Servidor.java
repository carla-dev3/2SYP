package es.florida.AE4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException {
		
		System.err.println("SERVIDOR >> Arranca el servidor, esperando petición...");
		ServerSocket socketEscucha = new ServerSocket(1234);	
	}
}
