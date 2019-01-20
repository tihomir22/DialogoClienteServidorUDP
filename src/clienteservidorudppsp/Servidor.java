/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteservidorudppsp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sportak
 */
public class Servidor extends Thread {

    final int puertoServidor = 5000;
    byte[] buffer = new byte[1024];

    @Override
    public void run() {
        try {
            DatagramSocket socket_servidor = new DatagramSocket(this.puertoServidor);
            while (true) {
                buffer = new byte[1024];
                System.out.println("SERVIDOR > Iniciado servidor UDP");

                DatagramPacket paquete_recibido = new DatagramPacket(this.buffer, this.buffer.length);
                System.out.println("SERVIDOR > Esperando recibir paquete");
                socket_servidor.receive(paquete_recibido);

                String mensaje = new String(paquete_recibido.getData());
                System.out.println("SERVIDOR > Recibido mensaje " + mensaje);

                int puertoCliente = paquete_recibido.getPort();
                InetAddress direccionCliente = paquete_recibido.getAddress();

                mensaje = "Suelta eltorpedo loco";
                System.out.println("SERVIDOR > Enviando respuesta desde el servidor :" + mensaje);

                this.buffer = mensaje.getBytes();
                DatagramPacket respuesta_servidor = new DatagramPacket(this.buffer, this.buffer.length, direccionCliente, puertoCliente);
                socket_servidor.send(respuesta_servidor);

            }
        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
