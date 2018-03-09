package edu.eci.arem.tallerclientesyservicios.ejercicio521;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

/**
 * Utilizando Datagramas escriba un programa que se conecte a un servidor que
 * responde la hora actual en el servidor. El programa debe actualizar la hora
 * cada 5 segundos seǵun los datos del servidor. Si una hora no es recibida
 * debe mantener la hora que teńıa. Para la prueba se apagaŕa el servidor y
 * despúes de unos segundos se reactivaŕa. El cliente debe seguir funcionando
 * y actualizarse cuando el servidor este nuevamente funcionando
 *
 * @author Alejandro Anzola email: alejandro.anzola@mail.escuelaing.edu.co
 */
public class DatagramTimeServer {

    DatagramSocket socket;
    public static final int SERVER_PORT = 4445;

    public DatagramTimeServer() {
        try {
            socket = new DatagramSocket(SERVER_PORT);
        } catch (SocketException ex) {
            System.err.println(ex);
            System.exit(1);
        }
    }

    public void startServer() {
        boolean finished = false;
        while (!finished) {
            byte[] buf = new byte[256];
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String dString = new Date().toString();
                buf = dString.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);

                socket.send(packet);
            } catch (IOException ex) {
                System.err.println(ex);
                System.exit(1);
            }
        }

        socket.close();
    }

    public static void main(String[] main) {
        DatagramTimeServer ds = new DatagramTimeServer();
        ds.startServer();
    }

}
