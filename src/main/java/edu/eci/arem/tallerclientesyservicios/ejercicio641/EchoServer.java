package edu.eci.arem.tallerclientesyservicios.ejercicio641;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Alejandro Anzola email: alejandro.anzola@mail.escuelaing.edu.co
 */
public interface EchoServer extends Remote {

    public String echo(String msg) throws RemoteException;
}
