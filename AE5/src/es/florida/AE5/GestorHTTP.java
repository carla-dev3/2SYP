package es.florida.AE5;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class GestorHTTP implements HttpHandler {

    //La clase GestorHTTP tendrá dos atributos: temperaturaActual y temperaturaTermostato.
    //Inicialmente ambas temperaturas tendrán el mismo valor, por ejemplo 15º

    String temperaturaActual = "15";
    double temperaturaTermostato = 15;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        if ("GET".equals(httpExchange.getRequestMethod())) {
            temperaturaActual = handleGetRequest(httpExchange);
            handleGETResponse(httpExchange, temperaturaActual);
        } else if ("POST".equals(httpExchange.getRequestMethod())) {
            temperaturaActual = handlePostRequest(httpExchange);
            handlePOSTResponse(httpExchange, temperaturaActual);
        }
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        System.out.println("Recibida URI tipo GET: " + httpExchange.getRequestURI().toString());
        return httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
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

    private void handleGETResponse(HttpExchange httpExchange, String temperaturaActual) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        String htmlResponse = "<html><body><h1>Temperatura Actual -> " + temperaturaActual + "</h1></body></html>";
        httpExchange.sendResponseHeaders(200, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        System.out.println("Devuelve respuesta HTML: " + htmlResponse);
        outputStream.flush();
        outputStream.close();

    }

    private void handlePOSTResponse(HttpExchange httpExchange, String temperaturaActual) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        String htmlResponse = "Parametro/s POST: " + temperaturaActual + " -> Se procesará por parte del servidor";
        System.out.println("Devuelve respueta HTML: " + htmlResponse);
        httpExchange.sendResponseHeaders(200, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();

    }
}
