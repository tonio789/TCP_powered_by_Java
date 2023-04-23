package concurrentetallertcpclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Client extends Thread{
    
    //creo in out y socket
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    // inicio los in out y socket
    public Client() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 7123);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // creo metodo read para que escuche
    public void read() {
        try {
            String data = in.readUTF();
            System.out.println(data);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // creo metodo write para enviar mensajes
    public void write() {
        try {
            String info = JOptionPane.showInputDialog("Escribre una letra: ");
            out.writeUTF(info);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // creo metodo run para que funcione
    @Override
    public void run() {
        while (true) {
            read();
        }
    }
}
