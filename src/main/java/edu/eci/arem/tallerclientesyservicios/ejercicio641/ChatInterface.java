package edu.eci.arem.tallerclientesyservicios.ejercicio641;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Alejandro Anzola email: alejandro.anzola@mail.escuelaing.edu.co
 */
public interface ChatInterface extends Remote {

    public String getMsg() throws RemoteException;

    public void sendMsg(String msg) throws RemoteException;
    
    public void setClient(ChatInterface client) throws RemoteException;
    
    public ChatInterface getClient() throws RemoteException;
}
