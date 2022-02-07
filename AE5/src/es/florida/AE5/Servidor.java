package es.florida.AE5;

import com.sun.net.httpserver.HttpServer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Servidor {

	public static void main(String[] args) throws IOException, MessagingException {

		String host_email = "smtp.gmail.com"; //correo host
		String email_remitente = "pruebas.carlaaparicio@gmail.com";
		String port_email = "587"; //port 587
		String[] email_destino = {"pruebas.carlaaparicio@gmail.com","carlaaparicio0905@gmail.com"}; //email destino
		String email_remitente_pass = "6cen123,E";
		String mensaje = "Esto es un mensaje de la evaluable de PSP "; //mensaje libre
		String asunto = "AVERIA"; //asunto: Averia
		String[] anexo = {"D:/CarlaAparicio/paisaje.jpg", "D:/CarlaAparicio/pdf_pruebasSYP.pdf"}; //foto y pdf anexos

		for(String destinatario : email_destino) {
			sistemaDeAlerta(mensaje, asunto, email_remitente,  email_remitente_pass,
					host_email,  port_email,  destinatario, anexo);
		}


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

	private static void sistemaDeAlerta(String mensaje, String asunto, String email_remitente, String email_remitente_pass, String host_email, String port_email, String email_destino, String anexo[]) throws MessagingException {

		Properties props = System.getProperties();
		props.put("mail.smtp.host", host_email);
		props.put("mail.smtp.user", email_remitente);
		props.put("mail.smtp.clave", email_remitente_pass);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port_email);

		Session session = Session.getDefaultInstance(props);

		MimeMessage message = new MimeMessage(session);

		message.setFrom(new InternetAddress(email_remitente));
		message.addRecipients(Message.RecipientType.TO, email_destino);
		message.setSubject(asunto);

		BodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setText(mensaje);

		Multipart multipart = new MimeMultipart();

		for(String foto : anexo) {
			BodyPart messageBodyPart2 = new MimeBodyPart();
			FileDataSource src= new FileDataSource(foto);
			messageBodyPart2.setDataHandler(new DataHandler(src));
			messageBodyPart2.setFileName(foto);
			multipart.addBodyPart(messageBodyPart2);
		}
		multipart.addBodyPart(messageBodyPart1);
		message.setContent(multipart);

		Transport transport = session.getTransport("smtp");
		transport.connect(host_email, email_remitente, email_remitente_pass);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
}
