package edu.eci.arem.tallerclientesyservicios.ejercicio431;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Alejandro Anzola email: alejandro.anzola@mail.escuelaing.edu.co
 */
public class Ejercicio431Server {

    public static final int SERVER_SOCKET = 35000;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(SERVER_SOCKET);
        } catch (IOException ex) {
            System.err.println("Could not listen on port: " + SERVER_SOCKET);
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException ex) {
            System.err.println("Accept failed");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));

        String inputLine, outputLine;

        while ((inputLine = in.readLine()) != null) {
            System.out.println("Mensaje: " + inputLine);
            outputLine = "Respuesta: " + proc(inputLine);

            out.println(outputLine);
            if (outputLine.equals("Respuesta: Bye")) {
                break;
            }
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
    
    private static String proc(String input) {
        String response = "";
        try {
            Integer i = Integer.parseInt(input);
            response += (int) Math.pow(i, 2);
        } catch (NumberFormatException ex) {
            System.err.println(input + " is not a number");
            response = "error";
        }
        
        return response;
    }
}
