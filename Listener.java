/**
## ASSIGNMENT 1 ##
Listener Client

Le Duyen Sandra, Vu
Computational Linguistics
14-720-361

19.10.2014

**/

import java.io.*;
import java.net.*;

public class Listener {
    public static void main(String[] args) throws IOException {
        
        // Check the numbers of arguments
        if (args.length != 2) {
            System.err.println("Usage: java Listener <server name> <port number>");
            System.exit(1);
        }
		
		// Arguments input through terminal
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        

    try (
    		// Open socket
            Socket listenerSocket = new Socket(serverName, port);
            // create output stream and input stream to the server
            PrintWriter out = new PrintWriter(listenerSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(listenerSocket.getInputStream()));
        ) {
        	// Sends String "LISTENER" to the server
        	out.println("LISTENER");
			
			String fromServer;
			fromServer = in.readLine();
			
            while (true) {    
            	// Waiting for input to print out directly
            	if(fromServer != null){
            		System.out.println(fromServer);  
                	fromServer = in.readLine();
            	} 
            	// If there is no input, then just read and do nothing else
            	else {
            		fromServer = in.readLine();
                }
            }
        
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