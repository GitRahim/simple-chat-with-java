package base;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	ObjectInputStream inObject;

	public Client() {
		try {
			socket = new Socket("127.0.0.1", 1030);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Server is down!", "Server is down", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	//if this method is called in GUI, it'll get a string as parameter and send it to Server.
	public void send(String str) {
		try {
			out.writeUTF(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//reads the string sent by server. In GUI by calling this, you can have the server's sent string.
	public String readString() {
		String str = null;
		try {
			str = (String) in.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}
