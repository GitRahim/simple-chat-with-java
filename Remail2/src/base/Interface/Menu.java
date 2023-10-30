package base.Interface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base.Client;

import java.awt.Font;


		//in menu page if you click on buttons, it'll send the user name to page related to button and opens that page. Except Buttons In and Out Messages.
public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//User name of account
	String user;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		Client client = new Client();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInMessages = new JButton("In Messages");
		btnInMessages.setFont(new Font("Century", Font.PLAIN, 11));
		btnInMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//send this string to server. 4 represents in messages.
				client.send("4" + "#" + user);
				//receives the in messages of this account from server and put it in an string named msgs. 
				String msgs = client.readString(); 
				dispose();
				//makes the InMessages page by sending msgs as parameter of its constructor.
				InMessages im = new InMessages(msgs);
				im.user = user;
				im.setTitle(user + " - In messages");
				im.setVisible(true);
			}
		});
		btnInMessages.setBounds(45, 89, 134, 23);
		contentPane.add(btnInMessages);
		//send this string to server. 5 represents out messages.		
		JButton btnOutMessages = new JButton("Out Messages");
		btnOutMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Like previous button.
				client.send("5" + "#" + user);
				String msgs = client.readString();
				dispose();
				OutMessages oM = new OutMessages(msgs);
				oM.setTitle(user + " - Out messages");
				oM.user = user;
				oM.setVisible(true);
			}
		});
		btnOutMessages.setFont(new Font("Century", Font.PLAIN, 11));
		btnOutMessages.setBounds(240, 89, 134, 23);
		contentPane.add(btnOutMessages);
		
		JButton btnEdit = new JButton("Edit Account");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EditAccount ea = new EditAccount();
				ea.user = user;
				ea.setTitle(user + " - Edit Account");
				ea.setVisible(true);
			}
		});
		btnEdit.setFont(new Font("Century", Font.PLAIN, 11));
		btnEdit.setBounds(240, 41, 134, 23);
		contentPane.add(btnEdit);
		
		JButton btnSendMessage = new JButton("Send Message");
		btnSendMessage.setFont(new Font("Century", Font.PLAIN, 11));
		btnSendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SendMessage sm = new SendMessage();
				sm.user = user;
				sm.setTitle(user + " - Send messages");
				sm.setVisible(true);
			}
		});
		btnSendMessage.setBounds(45, 41, 134, 23);
		contentPane.add(btnSendMessage);
		//go back to previous page.
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Century", Font.PLAIN, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Start start = new Start();
				start.setVisible(true);
			}
		});
		btnBack.setBounds(147, 161, 134, 23);
		contentPane.add(btnBack);
	}

}
