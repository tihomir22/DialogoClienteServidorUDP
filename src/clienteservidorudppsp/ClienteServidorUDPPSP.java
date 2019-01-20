/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteservidorudppsp;

/**
 *
 * @author sportak
 */
public class ClienteServidorUDPPSP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Servidor server = new Servidor();
        Cliente cli = new Cliente();
        Cliente cli2 = new Cliente();

        server.start();
        Thread.sleep(500);
        cli.start();
        Thread.sleep(5000);
        cli2.start();

    }

}
