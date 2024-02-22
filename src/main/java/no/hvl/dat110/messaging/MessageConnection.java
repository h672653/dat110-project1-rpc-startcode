package no.hvl.dat110.messaging;

import java.io.DataInputStream;
import static no.hvl.dat110.messaging.MessageUtils.SEGMENTSIZE;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;

public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection

	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream(socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {
		byte[] data = MessageUtils.encapsulate(message);

		try {
			outStream.write(data);
		} catch (IOException e) {
			System.out.println("TCP client: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	public Message receive() {

		Message message = null;

		byte[] data = new byte[SEGMENTSIZE];

		try {
			data = inStream.readNBytes(SEGMENTSIZE);
			message = MessageUtils.decapsulate(data);

			// data = MessageUtils.decapsulate(inStream.readAllBytes()).getData();

			// message = new Message(data);

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
		;

		return message;

	}

	// close the connection by closing streams and the underlying socket
	public void close() {

		try {

			outStream.close();
			inStream.close();

			socket.close();

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}