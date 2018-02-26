package edu.eci.arem.tallerclientesyservicios.ejercicio44;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * El ćodigo 4 presenta un servidor web que atiende una solicitud. Implemente
 * el servidor e intente conectarse desde el browser.
 *
 * @author Alejandro Anzola email: alejandro.anzola@mail.escuelaing.edu.co
 */
public class HttpServer {

    public static final int SERVER_PORT = 35000;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(SERVER_PORT);

        Socket clientSocket = null;
        clientSocket = serverSocket.accept();

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine, outputLine;

        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            if (!in.ready()) {
                break;
            }
        }

        outputLine = "HTTP/1.1 200 OK\n"
                + "Content-Type: text/html; charset=utf-8\n"
                + "Cache-Control: public, max-age=60, s-maxage=300\n"
                + "Vary: Accept-Encoding\n"
                + "Content-Encoding: raw\n"
                + "Server: DPS/1.3.5\n"
                + "X-SiteId: 2000\n"
                + "Set-Cookie: dps_site_id=2000; path=/\n"
                + "ETag: a1be084ec13bc207c17c49955f875fab\n"
                + "Date: Mon, 26 Feb 2018 21:47:54 GMT\n"
                + "Connection: keep-alive\n"
                + "Transfer-Encoding: chunked\n"
                + "\n"
                + "3c6f"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "  <head>\n"
                + "    <meta charset=\"UTF-8\" />\n"
                + "    <title>Title of the document</title>\n"
                + "  </head>\n"
                + "  <body>\n"
                + "    <h1>My website</h1>\n"
                + "  </body>\n"
                + "</html>\n"
                + inputLine;
        out.println(outputLine);

        System.out.println(">>>\"" + inputLine + "\"");

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }

}
