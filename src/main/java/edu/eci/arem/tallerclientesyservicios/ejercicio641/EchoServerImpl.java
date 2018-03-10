package edu.eci.arem.tallerclientesyservicios.ejercicio641;

import java.rmi.RemoteException;
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
public class EchoServerImpl implements EchoServer {

    public static final String IP_ADDRESS = "127.0.0.1";
    public static final int PORT = 25000;
    public static final String PUBLISH_NAME = "echoServer";

    public EchoServerImpl(String ip, int port, String publishName) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            EchoServer echoServer = (EchoServer) UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry(ip, port);
            registry.rebind(publishName, echoServer);

            System.out.println("Echo server ready...");
        } catch (RemoteException ex) {
            System.err.println("ERROR: " + ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public String echo(String msg) throws RemoteException {
        System.out.println("Received: " + msg);
        return "from server: " + msg;
    }

    public static void main(String[] args) {
        EchoServerImpl ec = new EchoServerImpl(IP_ADDRESS, PORT, PUBLISH_NAME);
    }

}
