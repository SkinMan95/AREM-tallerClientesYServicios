package edu.eci.arem.tallerclientesyservicios.ejercicio641;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Alejandro Anzola email: alejandro.anzola@mail.escuelaing.edu.co
 */
public class ChatClient {

    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    public void executeService(String ip, int port, String serviceName) throws RemoteException, InterruptedException {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        ChatInterface client = new Chat();
        ChatInterface server = null;

        try {
            Registry registry = LocateRegistry.getRegistry(ip, port);
            server = (ChatInterface) registry.lookup(serviceName);
            
            server.setClient(client);
            server.sendMsg("Stablished connection...");
            client.setClient(server);

        } catch (NotBoundException | RemoteException ex) {
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

                server.sendMsg(input);
            } else {
                Thread.sleep(500);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        
        ChatClient ec = new ChatClient();

        System.out.println("IP address: ");
        String ip = stdIn.readLine().trim();

        System.out.println("Port: ");
        int port = Integer.parseInt(stdIn.readLine().trim());

        ec.executeService(ip, port, ChatServer.PUBLISH_NAME);
    }

}
