package es.florida.AE5;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.mail.MessagingException;
import java.io.*;
import java.util.Scanner;

public class GestorHTTP implements HttpHandler {

    //La clase GestorHTTP tendrá dos atributos: temperaturaActual y temperaturaTermostato.
    //Inicialmente ambas temperaturas tendrán el mismo valor, por ejemplo 15º

    int temperaturaActual = 15;
    int temperaturaTermostato = 15;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {


        String requestParamValue = null;

        if ("GET".equals(httpExchange.getRequestMethod())) {
            temperaturaActual = Integer.parseInt(handleGetRequest(httpExchange));
            temperaturaTermostato = Integer.parseInt(handleGetRequest(httpExchange));
          //  handleGETResponse(httpExchange, 15, 15);
        } else if ("POST".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handlePostRequest(httpExchange);
            try {
                handlePOSTResponse(httpExchange,requestParamValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        System.out.println("Recibida URI tipo GET: " + httpExchange.getRequestURI().toString());
        return httpExchange.getRequestURI().toString().split("\\?")[1];
    }

    private String handlePostRequest(HttpExchange httpExchange) {
        System.out.println("Recibida URI tipo POST: " + httpExchange.getRequestBody().toString());
        InputStream is = httpExchange.getRequestBody();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private void handleGETResponse(HttpExchange httpExchange, int temp, int termo) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        String htmlResponse = "<html><body><h1>Temperatura Actual -> " + temp + "</h1></body></html><html><body><h1>Temperatura Termostato -> " + termo + "</h1></body></html>";
        httpExchange.sendResponseHeaders(200, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        System.out.println("Devuelve respuesta HTML: " + htmlResponse);
        outputStream.flush();
        outputStream.close();
    }

    private void handlePOSTResponse(HttpExchange httpExchange, String stringUsuario) throws IOException, InterruptedException, MessagingException {
        OutputStream outputStream = httpExchange.getResponseBody();
        String htmlResponse = "Parametro's POST: " + stringUsuario;
        System.out.println("Parametro/s POST: " + stringUsuario);

        String orden = stringUsuario.split("=")[0];
        String instruccion = stringUsuario.split("=")[1];

        if (orden.equals("setTemperatura")) {
            System.out.println(stringUsuario + " -> Programando la estufa... ");
            temperaturaTermostato = Integer.parseInt(stringUsuario.split("=")[1]);
            regularTemperatura();
            httpExchange.sendResponseHeaders(200, htmlResponse.length());
            outputStream.write(htmlResponse.getBytes());
        } else if (orden.equals("notificarAveria:email_remitente")) {
            System.out.println(stringUsuario + " -> Sistema de alerta en marcha... ");
            String email_remitente = instruccion.split(";")[0];
            String email_remitente_pass = stringUsuario.split("=")[2];
            Servidor.sistemaDeAlerta(email_remitente, email_remitente_pass);

            httpExchange.sendResponseHeaders(200, htmlResponse.length());
            outputStream.write(htmlResponse.getBytes());
        } else {
            System.out.println("La orden no es correcta");
        }
        outputStream.flush();
        outputStream.close();
    }

    private void regularTemperatura() throws InterruptedException {

        while(temperaturaTermostato != temperaturaActual) {
            if (temperaturaTermostato > temperaturaActual) {
                temperaturaActual += 1;
                Thread.sleep(5000);
                System.out.println("Temperatura Actual: " + temperaturaActual);
            } else {
                temperaturaActual -= 1;
                Thread.sleep(5000);
                System.out.println("Temperatura Actual: " + temperaturaActual);
            }
        }
        System.out.println("Has llegado a la temperatura deseada!!");
        System.out.println("-------------------------------------------------");

        }
}
