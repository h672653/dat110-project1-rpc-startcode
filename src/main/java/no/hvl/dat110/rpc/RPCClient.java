package no.hvl.dat110.rpc;

import no.hvl.dat110.TODO;

import no.hvl.dat110.messaging.*;

public class RPCClient {

	

	private MessagingClient msgclient;
    private MessageConnection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void connect() {
		
		connection = msgclient.connect();
		
	}
	
	public void disconnect() {
		
		connection.close();
	}

	

	public byte[] call(byte rpcid, byte[] params) {
		
		byte[] returnval = null;
		
		connection.send(new Message(RPCUtils.encapsulate(rpcid, params)));
		returnval = RPCUtils.decapsulate(connection.receive().getData());
		

		/*
		 *  Make a remote call om the method on the RPC server by sending an RPC request message and receive an RPC reply message

	 rpcid is the identifier on the server side of the method to be called
	 param is the marshalled parameter of the method to be called

		The rpcid and param must be encapsulated according to the RPC message format

		The return value from the RPC call must be decapsulated according to the RPC message format

		*/
				
		
		return returnval;
		
	}

}
