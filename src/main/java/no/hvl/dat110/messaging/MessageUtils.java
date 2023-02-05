package no.hvl.dat110.messaging;

import java.util.Arrays;


import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
		byte[] segment = null;
		byte[] data;
		
		
		
		
		segment = new byte[SEGMENTSIZE];
		data = message.getData();
		segment[0] = (byte)data.length;
		for(int i = 0; i < data.length; i++){
			segment[i+1] = data[i];
		}
			
		
		
		
		
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {

		Message message = null;
		

				int msgLength = segment[0];
				byte[] newSegment = new byte[msgLength];

				for(int i = 0; i < msgLength; i++){
					newSegment[i] = segment[i+1];
				}
				message = new Message(newSegment);
				
				
		
		return message;
		
	}
	
}
