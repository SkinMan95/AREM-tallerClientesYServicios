package edu.eci.arem.tallerclientesyservicios.ejercicio432;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Escriba un servidor que pueda recibir un n ́umero y responda con un
 * operacíon sobre este n ́umero. Este servidor puede recibir un mensaje que
 * empiece por “fun:”, si recibe este mensaje cambia la operacíon a las
 * especificada. El servidor debe responder las funciones seno, coseno y
 * tangente. Por defecto debe empezar calculando el coseno. Por ejemplo, si el
 * primer n ́umero que recibe es 0, debe responder 1, si despu ́es recibe π/ 2
 * debe responder 0, si luego recibe “fun:sin” debe cambiar la operacíon actual
 * a seno, es decir a a partir de ese momento debe calcular senos. Si enseguida
 * recibe 0 debe responder 0.
 *
 * @author Alejandro Anzola email: alejandro.anzola@mail.escuelaing.edu.co
 */
public class Ejercicio432Server {

    public static final int SERVER_SOCKET = 8080;

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

    private static Map<String, MathFunction> functions;
    private static List<String> functionNames;
    private static String actualFunction = "cos";

    private static String proc(String input) {
        if (functions == null) {
            createMathFunctions();
        }

        String response = "";
        StringTokenizer tokenizer = new StringTokenizer(input, ":");
        List<String> tokens = new ArrayList<>();

        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }

        if (tokens.size() > 2) {
            System.err.println("Error in input \"" + input + "\": more than"
                    + "one field specified");
            response = "error";
        }

        if (tokens.size() == 2) {
            boolean valid = true;
            valid &= tokens.get(0).equals("fun");
            valid &= functionNames.contains(tokens.get(1));

            if (valid) {
                actualFunction = tokens.get(1);
            } else {
                System.err.println("Error in input \"" + input + "\": invalid input");
                response = "error";
            }
        } else if (tokens.size() == 1) {
            try {
                response = "" + functions.get(actualFunction).compute(Float.parseFloat(tokens.get(0)));
            } catch (NumberFormatException ex) {
                System.err.println("Error in input \"" + input + "\": invalid input");
                response = "error";
            }
        }

        return response;
    }

    private static void createMathFunctions() {
        if (functions == null) {
            functions = new HashMap<>();
            functions.put("sin", (double x) -> Math.sin(x));
            functions.put("cos", (double x) -> Math.cos(x));
            functions.put("tan", (double x) -> Math.tan(x));

            // ----
            functionNames = new ArrayList<String>(functions.keySet());
        }
    }

    private interface MathFunction {

        public double compute(double x);
    }
}
