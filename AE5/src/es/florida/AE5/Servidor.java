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

	public static void main(String[] args) throws IOException {

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

	static void sistemaDeAlerta(String email_remitente, String email_remitente_pass) throws MessagingException {

		String host_email = "smtp.gmail.com";
		String asunto = "AVERIA";
		String mensaje = "Esto es un mensaje de la evaluable de PSP ";
		String[] anexo = {".imgs/paisaje.jpg", ".imgs/pdf_pruebasSYP.pdf"};
		String[] email_destino = {"mantenimientoinvernalia@gmail.com", "megustaelfresquito@gmail.com"};

		/*for(String destinatario : email_destino) {
			sistemaDeAlerta(email_remitente, email_remitente_pass, destinatario);
		}*/

		System.out.println("Envío de correo");
		System.out.println(" -> Remitente: " + email_remitente);
		System.out.println(" -> Destinatario: " + email_destino);
		System.out.println(" -> Asunto: " + asunto);
		System.out.println(" -> Mensaje: " + mensaje);

		Properties props = System.getProperties();
		props.put("mail.smtp.host", host_email);

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(props);

		MimeMessage message = new MimeMessage(session);

		message.setFrom(new InternetAddress(email_remitente));
		message.addRecipients(Message.RecipientType.TO, String.valueOf(email_destino));
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
		System.out.println("-------------------------------------------------");
		System.out.println("CORREO ENVIADO CON ÉXITO");
		System.out.println("-------------------------------------------------");
		transport.close();
	}
}
