package es.florida.AE5;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

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
         //   requestParamValue = handlePostRequest(httpExchange);
         //   handlePOSTResponse(httpExchange, requestParamValue);
        }
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        System.out.println("Recibida URI tipo GET: " + httpExchange.getRequestURI().toString());
        return httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
    }

    private void handleGETResponse(HttpExchange httpExchange, String temperaturaActual) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        String htmlResponse = "<html><body><h1>Temperatura Actual -> " + temperaturaActual + "</h1></body></html>";
        httpExchange.sendResponseHeaders(200, htmlResponse.length());
        outputStream.write(htmlResponse.getBytes());
        outputStream.flush();
        outputStream.close();
        System.out.println("Devuelve respuesta HTML: " + htmlResponse);
    }
}
