package base.Interface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import base.Client;


public class Start extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
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
	public Start() {
		setTitle("Log in or Sign up");
		client = new Client();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblusername = new JLabel("Username");
		lblusername.setFont(new Font("Century", Font.PLAIN, 11));
		lblusername.setBounds(34, 140, 60, 14);
		contentPane.add(lblusername);
		
		JLabel lblpassword = new JLabel("Password");
		lblpassword.setFont(new Font("Century", Font.PLAIN, 11));
		lblpassword.setBounds(34, 165, 60, 14);
		contentPane.add(lblpassword);
		
		username = new JTextField();
		username.setBounds(126, 137, 256, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(126, 162, 256, 20);
		contentPane.add(password);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Century", Font.PLAIN, 11));
		//if you click on Login button, it'll send 2 + entered user and pass to Server. 2 represents login. 
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send("2" + "#" + username.getText() + "#" + password.getText());
				//after entered info checked, Server sends the user name.
				 String user = client.readString();
				//if it's not " ", then you're logged in. and you'll go to Menu page.
				if( !(user.equalsIgnoreCase(" ")) ) {
					System.out.println(user);
					JOptionPane.showMessageDialog(null, "Logged in");
					//this page is closed and menu page is opened. the user of menu page is now the user sent by server.
					dispose();
					Menu mn = new Menu();
					mn.user = user;
					mn.setTitle(user + " - Menu");
					mn.setVisible(true);
				}
				//else a message tells that user or pass is wrong and then again this Start page will appear to try again.
				else {
					JOptionPane.showMessageDialog(null, "Username or Password is wrong");
					dispose();
					Start st = new Start();
					st.setVisible(true);
				}
			}
		});
		btnLogin.setBounds(168, 193, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnSignup = new JButton("Sign Up");
		btnSignup.setFont(new Font("Century", Font.PLAIN, 11));
		//if you don't have an account, you click on sign up button and Sign up page is opened. 
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SignUp su = new SignUp();
				su.setVisible(true);
			}
		});
		btnSignup.setBounds(168, 227, 89, 23);
		contentPane.add(btnSignup);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 434, 129);
		//adding an image to the Start page.
		Image im = new ImageIcon(this.getClass().getResource("/images/Post.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(im.getScaledInstance(434, 129, Image.SCALE_SMOOTH)));
		contentPane.add(lblNewLabel);
	}
}
