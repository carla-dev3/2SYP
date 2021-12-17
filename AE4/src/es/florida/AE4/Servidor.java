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
			ObjectOutputStream outObjeto = new ObjectOutputStream(socket.getOutputStream());
			
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Error");
		}
	}
}
