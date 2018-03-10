package edu.eci.arem.tallerclientesyservicios.ejercicio641;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

/**
 *
 * @author Alejandro Anzola email: alejandro.anzola@mail.escuelaing.edu.co
 */
public class Chat implements ChatInterface {

    private static final long serialVersionUID = 31684616546516L;

    private final BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    private ChatInterface client;

    @Override
    public String getMsg() throws RemoteException {
        String input;
        try {
            input = stdIn.readLine();
        } catch (IOException ex) {
            input = "";
        }

        System.out.println("Me: " + input);
        return input;
    }

    @Override
    public void sendMsg(String msg) throws RemoteException {
        System.out.println("Peer says: " + msg);
    }

    @Override
    public void setClient(ChatInterface client) throws RemoteException {
        this.client = client;
    }

    @Override
    public ChatInterface getClient() throws RemoteException {
        return client;
    }

}
