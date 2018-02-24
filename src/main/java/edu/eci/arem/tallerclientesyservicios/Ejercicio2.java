package edu.eci.arem.tallerclientesyservicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;

/**
 *
 * @author 2110137
 */
public class Ejercicio2 {

    public static void main(String[] args) throws Exception {
        String pageUrl = "http://www.escuelaing.edu.co/";
        URL webPage = new URL(pageUrl);
        String filename = "resultado.html";

        try (BufferedReader reader
                = new BufferedReader(new InputStreamReader(webPage.openStream()));
                PrintStream writer = new PrintStream(new File(filename))) {
            String inputLine = null;
            while ( (inputLine = reader.readLine()) != null ) {
                writer.println(inputLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
