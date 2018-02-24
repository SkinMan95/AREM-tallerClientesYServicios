package edu.eci.arem.tallerclientesyservicios.ejercicio431;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro Anzola email: alejandro.anzola@mail.escuelaing.edu.co
 */
public class Ejercicio431Client {

    public static final String IP_ADDRESS = "127.0.0.1";

    public static void main(String[] args) throws IOException {

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket(IP_ADDRESS, Ejercicio431Server.SERVER_SOCKET);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException ex) {
            System.err.println("Don't know about host");
            System.exit(1);
        } catch (IOException ex) {
            System.err.println("Couldn't get I/O for the connection to: " + IP_ADDRESS);
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String userInput;

        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("echo: " + in.readLine());;
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}
