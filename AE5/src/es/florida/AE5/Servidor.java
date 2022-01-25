package es.florida.AE5;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Servidor {

	public static void main(String[] args) throws IOException {

		//El servidor (Servidor.java) estará alojado en la IP 127.0.0.1 en el puerto 7777
		String host = "127.0.0.1";
		int puerto = 7777;
		InetSocketAddress direccionTCPIP = new InetSocketAddress(host,puerto);
		int backlog = 0;
		HttpServer servidor = HttpServer.create(direccionTCPIP, backlog);

		GestorHTTP gestorHTTP = new GestorHTTP();
		String rutaRespuesta = "/estufa";
		servidor.createContext(rutaRespuesta, gestorHTTP);

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		servidor.setExecutor(threadPoolExecutor);

		servidor.start();
		System.out.println("Servidor HTTP arranca en el puerto " + puerto);

	}

}
