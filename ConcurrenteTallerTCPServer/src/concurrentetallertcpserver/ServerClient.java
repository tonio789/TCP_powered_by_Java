package concurrentetallertcpserver;

import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerClient implements Runnable {

    //Socket + in + out
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    // char letra
    private char letra;

    // constructor para socket y accept
    public ServerClient(final Socket socket) {
        this.socket = socket;
        accept();
    }

    // void accept
    public void accept() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // void read
    public void read() {
        try {
            Juego juego = new Juego();
            juego.llenarArreglo();

            //envio los print
            out.writeUTF(juego.print());
            out.flush();
            // ciclo del juego
            boolean win = false;
            while (!win) {

                // inicio el input
                String info = in.readUTF();
                System.out.println(info);

                // valido la letra y actualizo mi array de char
                letra = info.charAt(0);
                for (int i = 0; i < juego.palabra.length(); i++) {
                    if (juego.arregloChar[i] == letra) {
                        juego.ArregloCharGuion[i] = letra;
                    }
                }

                // valido si las listas estan iguales para saber si gano el jugador
                if (Arrays.equals(juego.arregloChar, juego.ArregloCharGuion)) {
                    win = true;
                    out.writeUTF("Ganaste!!!");
                    out.flush();
                }

                //envio los print
                out.writeUTF(juego.print());
                out.flush();
            }

        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // ciclo para que siempre escuche
    @Override
    public void run() {
        while (true) {
            read();
        }
    }

    // void close
    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
