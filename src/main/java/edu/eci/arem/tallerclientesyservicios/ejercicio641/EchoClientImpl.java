package edu.eci.arem.tallerclientesyservicios.ejercicio641;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Alejandro Anzola email: alejandro.anzola@mail.escuelaing.edu.co
 */
public class EchoClientImpl {

    public void executeService(String ip, int port, String serviceName) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            Registry registry = LocateRegistry.getRegistry(ip, port);
            EchoServer echoServer = (EchoServer) registry.lookup(serviceName);
            System.out.println(echoServer.echo("Hello, how are you?"));

        } catch (NotBoundException | RemoteException ex) {
            System.err.println("ERROR: " + ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        EchoClientImpl ec = new EchoClientImpl();
        ec.executeService(EchoServerImpl.IP_ADDRESS, EchoServerImpl.PORT, EchoServerImpl.PUBLISH_NAME);
    }

}
