package base.Interface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import base.Client;


public class SendMessage extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsers;
	private JTextField txtMessage;
	String user;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendMessage frame = new SendMessage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public SendMessage() {
		Client client = new Client();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrInTheField = new JTextArea();
		txtrInTheField.setFont(new Font("Century", Font.PLAIN, 13));
		txtrInTheField.setText("Below write usernames you want to send message to, like:\r\nAlex,Michael");
		txtrInTheField.setBounds(10, 11, 414, 44);
		contentPane.add(txtrInTheField);
		
		txtUsers = new JTextField();
		txtUsers.setBounds(10, 66, 414, 20);
		contentPane.add(txtUsers);
		txtUsers.setColumns(10);
		
		JTextArea txtrWriteYourMessage = new JTextArea();
		txtrWriteYourMessage.setFont(new Font("Century", Font.PLAIN, 13));
		txtrWriteYourMessage.setText("Write your message below:");
		txtrWriteYourMessage.setBounds(10, 97, 414, 29);
		contentPane.add(txtrWriteYourMessage);
		
		txtMessage = new JTextField();
		txtMessage.setBounds(10, 137, 414, 20);
		contentPane.add(txtMessage);
		txtMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		//if send is clicked, user names you entered are send to server and there checked and also message. if they exist, message will be sent to them. 
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//6 represents send message.
				client.send("6" + "#" + user + "#" + txtUsers.getText() + "#" + txtMessage.getText());
				//users sent by server are saved in a string.
				String recievedUsers = client.readString();
				//users are split by "#" and then are saved in this string array.
				String[] users = recievedUsers.split("#");
				//here if user received contains "+" it means it exists and message is sent to it. else it doesn't exist.
				for(int i=0; i<users.length; i++) {
					if(users[i].contains("+")) {
						JOptionPane.showMessageDialog(null, "Sent to "+users[i].substring(1));				
					}
					else {
						JOptionPane.showMessageDialog(null, users[i]+" doesn't exist");
					}
				}
				//after sending message, this page is refreshed to be used again.
				dispose();
				SendMessage sm = new SendMessage();
				sm.user = user;
				sm.setTitle(user+" - Send Message");
				sm.setVisible(true);
				}
		});
		btnSend.setFont(new Font("Century", Font.PLAIN, 11));
		btnSend.setBounds(162, 168, 89, 23);
		contentPane.add(btnSend);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu mn = new Menu();
				mn.user = user;
				mn.setTitle(user + " - Menu");
				mn.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Century", Font.PLAIN, 11));
		btnBack.setBounds(162, 202, 89, 23);
		contentPane.add(btnBack);	
	}
}
