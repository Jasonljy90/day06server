import java.net.Socket;

public class ListClient{
    public static void main(String[] args) throws Exception {
        
        // Get list size
        Integer nums = Integer.parseInt(args[0]);
        
        // Get the number range
        Integer range = Integer.parseInt(args[1]);

        // Get the host
        String host = args[2];

        // Get the port
        int port = Integer.parseInt(args[3]);
    
        // Create the socket to the server
        Socket socket = new Socket(host, port);

        System.out.printf("Connected to %s:%d\n", host, port);

        IOUtils.write(socket, "%d %d".formatted(nums, range));
        String response = IOUtils.read(socket);
        // System.out.println(response);
        // Process response - calculate the average
        String[] numbers  = response.split(":");
        Integer totalSum = 0;

        for (String num : numbers) {
            int numbersIntegers = Integer.parseInt(num);
            totalSum += numbersIntegers;
        }      
        System.out.println(totalSum);
        float averageValue = (float) totalSum/nums;
        System.out.println("The average value is " + averageValue);
        //socket.close();        
        }
    }



    