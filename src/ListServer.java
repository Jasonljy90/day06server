import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ListServer {

public static void main(String[] args) throws Exception {
    // Get the port
    Integer port = Integer.parseInt(args[0]);

    // Create a server socket and bind to port
    ServerSocket server = new ServerSocket(port);
    
    // Random number generator
    Random rnd = new SecureRandom();
    
    ExecutorService thrpool = Executors.newFixedThreadPool(2);

    System.out.printf("Listening on port %d\n", port);
  
        // Server loop
        while(true) {
        Socket socket = server.accept();

        thrpool.submit(
                () -> {
                    System.out.printf("New connection on port %d\n", socket.getLocalPort());
                    String payload = "";
                    try {
                        payload = IOUtils.read(socket);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }                           
                    String[] values = payload.split(" ");
                    Integer count = Integer.parseInt(values[0]);
                    Integer range = Integer.parseInt(values[1]);
            
                    List<Integer> randNums = new LinkedList<>();
                    for (Integer i = 0; i < count; i++) {
                        randNums.add(rnd.nextInt(range));
                    }
                    String response = randNums.stream().map(v -> v.toString()).collect(Collectors.joining(":"));
                    try {
                        IOUtils.write(socket, response);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }    
                    }
            );
        }
    }
}
        //socket.close();
        