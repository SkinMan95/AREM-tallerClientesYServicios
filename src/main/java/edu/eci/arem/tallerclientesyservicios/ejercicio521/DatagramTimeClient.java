package edu.eci.arem.tallerclientesyservicios.ejercicio521;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro Anzola email: alejandro.anzola@mail.escuelaing.edu.co
 */
public class DatagramTimeClient {

    public static final String HOST_ADDRESS = "127.0.0.1";
    public static final int TIMEOUT_TIME = 500;
    public static final int TIME_BEETWEEN_REQUESTS = 5000;

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Date actualTime = new Date();
        String received = "";

        boolean finished = false;
        while (!finished) {
            try {
                DatagramSocket socket = new DatagramSocket();
                socket.setSoTimeout(TIMEOUT_TIME);
                byte[] buf = new byte[256];
                InetAddress address = InetAddress.getByName(HOST_ADDRESS);

                try {
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, address, DatagramTimeServer.SERVER_PORT);
                    socket.send(packet);

                    packet = new DatagramPacket(buf, buf.length);

                    socket.receive(packet);

                    received = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("Date from server: " + received);
                    
                    actualTime = DateFormat.getDateInstance().parse(received);
                } catch (ParseException | SocketTimeoutException ex) {
                    actualTime.setTime(actualTime.getTime() + TIMEOUT_TIME + TIME_BEETWEEN_REQUESTS);
                }

                System.out.println("Local Date: " + actualTime.toString());

                Thread.sleep(TIME_BEETWEEN_REQUESTS);
            } catch (InterruptedException | IOException ex) {
                System.err.println(ex);
            }
        }
    }

}
