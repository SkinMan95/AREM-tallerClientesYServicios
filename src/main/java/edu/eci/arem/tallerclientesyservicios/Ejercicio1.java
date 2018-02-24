package edu.eci.arem.tallerclientesyservicios;

import java.net.URL;

/**
 *
 * @author 2110137
 */
public class Ejercicio1 {

    public static void main(String[] args) throws Exception {
        String pageUrl = "http://www.escuelaing.edu.co/";
        URL webPage = new URL(pageUrl);
        
        System.out.println("Protocol " + webPage.getProtocol());
        System.out.println("Authority " + webPage.getAuthority());
        System.out.println("Host " + webPage.getHost());
        System.out.println("Port " + webPage.getPort());
        System.out.println("Path " + webPage.getPath());
        System.out.println("Query " + webPage.getQuery());
        System.out.println("File " + webPage.getFile());
        System.out.println("Ref " + webPage.getRef());
    }

}
