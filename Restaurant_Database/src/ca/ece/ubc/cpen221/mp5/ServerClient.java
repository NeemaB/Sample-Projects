package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;

import org.json.simple.JSONObject;

/**
 * A ServerClient object sends queries to the RestaurantDBServer via an
 * outputStream and may receive a JSON formatted string as a reply if the query
 * is properly formatted
 * 
 * @author neema
 *
 */
public class ServerClient {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	/**
	 * Sets up a ServerClient object that can access a port on the local
	 * computer.
	 * 
	 * @param hostname
	 * @param port
	 * @throws IOException
	 */

	public ServerClient(String hostname, int port) throws IOException {
		socket = new Socket(hostname, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	
	/**
	 * sends a Query to the server that the client is connected to via an outputStream
	 * 
	 * @param s
	 * 			the String representing the query
	 * 
	 * @throws IOException
	 */
	public void SendQuery(String s) throws IOException {
		out.print(s + "\n");
		out.flush(); 
	}
	
	/**
	 * Checks the inputStream of the client for replies from the server
	 * 
	 * @return
	 * 			a String representing the Server's reply
	 * 
	 * @throws IOException
	 */
	public String getReply() throws IOException {

		String reply = in.readLine();
		if (reply == null) {
			throw new IOException("connection terminated unexpectedly");
		}

		return new String(reply);
		
	}

	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}



}
