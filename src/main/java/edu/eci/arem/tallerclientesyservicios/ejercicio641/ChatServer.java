package edu.eci.arem.tallerclientesyservicios.ejercicio641;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * CHAT: Utilizando RMI, escriba un aplicativo que pueda conectarse a otro
 * aplicativo del mismo tipo en un servidor remoto para comenzar un chat. El
 * aplicativo debe solicitar una direccíon IP y un puerto antes de conectarse
 * con el cliente que se desea. Igualmente, debe solicitar un puerto antes de
 * iniciar para que publique el objeto que recibe los llamados remotos en dicho
 * puerto.
 *
 * @author Alejandro Anzola email: alejandro.anzola@mail.escuelaing.edu.co
 */
public class ChatServer {
    
    public static final String IP_ADDRESS = "127.0.0.1";
    public static final int PORT = 25000;
    public static final String PUBLISH_NAME = "chatServer";
    
    public static void main(String[] args) throws Exception {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
        ChatInterface server = new Chat();
        
        try {
            System.out.println("IP address: ");
            String ip = stdIn.readLine().trim();
            
            System.out.println("Port: ");
            int port = Integer.parseInt(stdIn.readLine().trim());
            
            server = (ChatInterface) UnicastRemoteObject.exportObject(server, port);
            Registry registry = LocateRegistry.getRegistry(ip, port);
            registry.rebind(PUBLISH_NAME, server);
        } catch (IOException ex) {
            System.err.println("ERROR: " + ex);
            ex.printStackTrace();
            System.exit(1);
        }
        
        boolean finished = false;
        while (!finished) {
            if (server.getClient() != null) {
                String input;
                try {
                    input = stdIn.readLine();
                } catch (IOException ex) {
                    input = "";
                }
                
                server.getClient().sendMsg(input);
            } else {
                Thread.sleep(500);
            }
        }
        
    }
    
}
