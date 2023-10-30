package base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name, username, password;

	//these Strings save out and in messages in themselves. 
	private String outMessages = " ";
	private String inMessages = " ";
	
	//Constructor
	public Account(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOutMessages() {
		return outMessages;
	}
	public void setOutMessages(String outMessages) {
		this.outMessages = outMessages;
	}
	public String getInMessages() {
		return inMessages;
	}
	public void setInMessages(String inMessages) {
		this.inMessages = inMessages;
	}
	//returns a users. valid ones with a "+" in first char. it'll save the message in both sender and receiver account and file.
	public synchronized String sendMessage(String usernames, String message) {
		Account ac = null;
		//the users sent by client to server are split by ",".
		String users[] = usernames.split(",");
		//here valid users with a "+" in first char and invalid users like before are saved in this string with "#" as separator between them.
		String returUsers = "";
		for(int i=0; i<users.length; i++) {
			ac = findAccount(users[i]);
			if(ac != null) {
				returUsers+="+"+users[i]+"#";
				//after every messages there is a "#" which separates them.
				//these 3 lines below give us the current time in a string.
				SimpleDateFormat  ft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				String d = ft.format(date);
				
				String messageWithInfo = "From " + this.username + " to " + users[i] + " in " + d +" : " + message + "#";
				//messages in outMessages and inMessages are then split by "#" to be shown well.
				this.outMessages += messageWithInfo;
				ac.inMessages += messageWithInfo;
				writeAccountToFile(this);
				writeAccountToFile(ac);
			}
			else {
				returUsers+=users[i]+"#";
			}
		}
		return returUsers;
	}
	//gets the new name and password and saves them in the account object and file.
	public synchronized void editAccount(String name, String pass) {
		this.name = name;
		this.password = pass;
		writeAccountToFile(this);
	}
	//searches in files with user name given, and if there is such user name this method returns it.
	//this method is used for sending message.
	public Account findAccount(String username) {
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
	
	//whenever we make changes to an account, we should do the same changes to its file too, so we call this method. 
	public void writeAccountToFile(Account ac) {
		String username = ac.username;
		String fileName = username+".txt";
		try (FileOutputStream fos = new FileOutputStream(fileName);
			     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			    // write object to file
			    oos.writeObject(ac);
			    oos.flush();
			} catch (IOException ex) {
			    ex.printStackTrace();
			}
	}
	
	//this method returns all sent messages in a string.
	public synchronized String showOutMessages() {
		String[] outM = outMessages.split("#"); 
		String ms = "Out Messages:\n";
		for(int i=0; i<outM.length; i++) {
			ms += outM[i]+"\n ";
		}
		return ms;
	}
	
	//this method returns all received messages in a string.
	public synchronized String showInMessages() {
		String[] inM = inMessages.split("#");
		String ms = "In Messages:\n";
		for(int i=0; i<inM.length; i++) {
			ms += inM[i]+"\n ";
		}
		return ms;
	}

}
