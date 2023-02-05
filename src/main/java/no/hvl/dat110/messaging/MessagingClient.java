package no.hvl.dat110.messaging;

import java.io.IOException;

import java.net.Socket;


import no.hvl.dat110.TODO;

public class MessagingClient {

    
	private String server;
	private int port;
	
	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	// setup of a messaging connection to a messaging server
	public MessageConnection connect () {

		// client-side socket for underlying TCP connection to messaging server
		Socket clientSocket;

		MessageConnection connection = null;
		
		try {
			clientSocket = new Socket(server, port);
			connection = new MessageConnection(clientSocket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
