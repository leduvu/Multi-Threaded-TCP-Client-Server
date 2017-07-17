/**
## ASSIGNMENT 1 ##
Producer Client

Le Duyen Sandra, Vu
Computational Linguistics
14-720-361

19.10.2014

**/

import java.io.*;
import java.net.*;

public class Producer {
    public static void main(String[] args) throws IOException {
        
        // Check the numbers of arguments
        if (args.length != 4) {
            System.err.println("Usage: java Producer <server name> <port number> <client name> <filename>");
            System.exit(1);
        }
		
		// Arguments input through terminal
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        String clientName = args[2];
		String inputFileName = args[3];

        try (
        	// Open socket
            Socket producerSocket = new Socket(serverName, port);
            // create output stream and input stream to the server
            PrintWriter out = new PrintWriter(producerSocket.getOutputStream(), true);
            // create a file Object and a file input stream reader
			FileReader fr = new FileReader(inputFileName);
            BufferedReader br = new BufferedReader(fr);
                        
        ) {
        	// Sends String "PRODUCER" to the server
            out.println("PRODUCER");
            
            // Read input file
            String zeile;
            zeile = br.readLine();
            while (zeile != null) {
            	// Add the client name to the beginning of every line
            	// Send it through output stream to the server
                out.println(clientName + ":" + zeile); 
                zeile = br.readLine();
                
                // Stop after reading .bye
            try{
                if (zeile.equals(".bye")) break;
    		} catch(java.lang.NullPointerException e) {}        

                }
            // Close socket
            producerSocket.close();
           
        // Catching errors        
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + serverName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                serverName);
            System.exit(1);
        }
    }
}

// Sources for supporting:
// http://www.cs.umd.edu/~shankar/417-F01/Slides/chapter2b-aus/sld016.htm
// http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html