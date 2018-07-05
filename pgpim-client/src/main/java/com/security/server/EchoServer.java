package com.security.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	public static void main(String args []) {
		System.out.println("Echo server starting");
		try (
			ServerSocket serverSocket = new ServerSocket(8005);
			Socket clientSocket = serverSocket.accept();
			PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(),true);
			BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
			String inputLine = fromClient.readLine();
			while(inputLine != null) {
				System.out.println("Client sent: " + inputLine);
				toClient.println("Echo: " + inputLine);
				inputLine = fromClient.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
