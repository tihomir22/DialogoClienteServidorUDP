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
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sportak
 */
public class Cliente extends Thread {

    final int puertoServidor = 5000;
    byte[] buffer = new byte[1024];

    @Override
    public void run() {
        try {
            buffer = new byte[1024];
            System.out.println("CLIENTE INICIADO ");
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            DatagramSocket socket_cliente = new DatagramSocket(); //no necesitamos un puerto en concreto para el cliente
            String mensaje = "Hola señor servidor que pasa loco";
            this.buffer = mensaje.getBytes();
            DatagramPacket paquete_cliente = new DatagramPacket(this.buffer, this.buffer.length, direccionServidor, puertoServidor);
            System.out.println("CLIENTE > Envio el datagrama al servidor");
            socket_cliente.send(paquete_cliente);

            DatagramPacket respuesta_servidor = new DatagramPacket(this.buffer, this.buffer.length);
            socket_cliente.receive(respuesta_servidor);

            mensaje = new String(respuesta_servidor.getData());
            System.out.println("CLIENTE > Recibido desde el servidor " + mensaje);

            socket_cliente.close();
        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
