package com.security.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientServer {
	
	public static void main (String args []) {
		System.out.println("Client Server Starting");
		try(
			Socket echoSocket = new Socket("127.0.0.1", 8005);
			PrintWriter toServer = new PrintWriter(echoSocket.getOutputStream(), true);
			BufferedReader fromServer = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		) {
			String userInput = stdIn.readLine();
			while(userInput != null && !userInput.equalsIgnoreCase("quit()")) {
				toServer.println(userInput);
				System.out.println(fromServer.readLine());
				userInput = stdIn.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			System.out.println("main program loop ended. Exiting.");
		}
	}
}
