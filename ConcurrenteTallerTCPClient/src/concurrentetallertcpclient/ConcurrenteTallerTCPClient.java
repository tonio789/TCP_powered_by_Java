package concurrentetallertcpclient;
public class ConcurrenteTallerTCPClient {
    public static void main(String[] args) {
        
        //inicio objeto de cliente
        Client client = new Client();
        client.start();
        while (true) {
            client.write();
        }
        
    }
}
