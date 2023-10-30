package base;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		System.out.println("Server started");
		ServerSocket server = null;
		try {
			server = new ServerSocket(1030);
			while (true) {
				Socket socket = server.accept();
				MyThread th = new MyThread(socket);
				th.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static class MyThread extends Thread {
		Socket socket;

		public MyThread(Socket socket) {
			this.socket = socket;
		}

		public void run() {

			try {
				DataInputStream in = new DataInputStream(this.socket.getInputStream());
				DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
				//server receives the string that sent from GUI by Client.
				String str = in.readUTF();
				//the string is split by char '#' in order to understand what process should be done.
				String[] spl = str.split("#");
				//first character shows what server should do. so we switch on it.
				switch (spl[0]) {
				//sign up
				//if the first character of the string that is received from GUI is number 1, then Server does sign up process. 
				case "1":
					signUp(spl[1],spl[2],spl[3]);
					break;
				//log in
				//if the first character of the string that is received from GUI is number 2, then Server does log in process. 
				case "2":
					String user = logIn(spl[1],spl[2]);
					out.writeUTF(user);
					break;
				//Editing the account 
				case "3":
					Account a = findAccount(spl[1]);
					a.editAccount(spl[2], spl[3]);
					break;
				//Showing received messages	
				case "4":
					a = findAccount(spl[1]);
					out.writeUTF(a.showInMessages());
					break;
				//Showing sent messages	
				case "5":
					a = findAccount(spl[1]);
					out.writeUTF(a.showOutMessages());
					break;
				//Sending message	
				case "6":
					a = findAccount(spl[1]);
					out.writeUTF(a.sendMessage(spl[2], spl[3]));
					break;
				default:
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		}
	}
	//does sign up process. it'll save the info of account to a file with such name: username.txt
	public static void signUp(String name, String username, String password) {
		
		String fileName = username+".txt";
		try (FileOutputStream fos = new FileOutputStream(fileName);
			     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			
			    // create an account
			    Account ac = new Account(name, username, password);
			    
			    // write object to file
			    oos.writeObject(ac);
			    oos.flush();
			} catch (IOException ex) {
			    ex.printStackTrace();
			}
	}
	
	//for login server checks the files and find the file that is named username.txt . then if there is a file with
	//username's name, it'll check the password with entered password. if they're equal, it'll return the account.
	public static String logIn(String username, String password) {
		String user = " ";
		Account login = null;
		String fileName = username+".txt";
		try (FileInputStream fis = new FileInputStream(fileName);
			    ObjectInputStream ois = new ObjectInputStream(fis)) {
			    login = (Account) ois.readObject();
			} catch (Exception e) {
				user = " ";
			} 
		if( login!=null && login.getPassword().equalsIgnoreCase(password)) {
			user = username;
		}
			return user;		
	}
	
	//searches in files with user name given, and if there is such user name this method returns it.
	public static Account findAccount(String username) {
		Account ac = null;
		String fileName = username+".txt";
		try (FileInputStream fis = new FileInputStream(fileName);
			    ObjectInputStream ois = new ObjectInputStream(fis)) {
			    ac = (Account) ois.readObject();
			} catch (Exception e) {
				ac = null;
			} 
		return ac;
	}
	
}
