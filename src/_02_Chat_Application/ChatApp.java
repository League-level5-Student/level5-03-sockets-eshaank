package _02_Chat_Application;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame{
JTextArea text = new JTextArea("Type message here", 25, 25);
JButton sendButton = new JButton("Send");
JPanel panel = new JPanel();
	Server server;
	Client client;
	
	
	public static void main(String[] args) {
		new ChatApp();
	}
	
	public ChatApp(){
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "texts!", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			sendButton.setVisible(true);
			server = new Server(8080);
			setTitle("SERVER");			
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			sendButton.addActionListener((e)->{
				server.sendClick(text.getText());
			});
			add(panel);
			panel.add(text);
			panel.add(sendButton);
			sendButton.setVisible(true);
			text.setSize(500, 500);
			setVisible(true);
			pack();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
			
		}else{
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new Client(ipStr, port);
			add(sendButton);
			setVisible(true);
			sendButton.addActionListener((e)->{
				client.sendClick(text.getText());
			});
			add(panel);
			panel.add(text);
			panel.add(sendButton);
			sendButton.setVisible(true);
			text.setSize(500, 500);
			setVisible(true);
			pack();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
	}
}
