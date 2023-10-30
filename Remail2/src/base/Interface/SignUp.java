package base.Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base.Client;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;


public class SignUp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		Client client = new Client();
		setTitle("Sign up");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setFont(new Font("Century", Font.PLAIN, 11));
		lblNewLabel.setBounds(31, 25, 68, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Century", Font.PLAIN, 11));
		lblUsername.setBounds(31, 50, 68, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Century", Font.PLAIN, 11));
		lblPassword.setBounds(31, 75, 68, 14);
		contentPane.add(lblPassword);
		
		txtName = new JTextField();
		txtName.setBounds(160, 22, 235, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(160, 47, 235, 20);
		contentPane.add(txtUsername);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(160, 72, 235, 20);
		contentPane.add(txtPassword);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Century", Font.PLAIN, 11));
		//if Create button is clicked, it'll send info to Server and Server saves them in a file.
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//this string is split in server by "#". 1 represents sign up. 
				client.send("1" + "#" + txtName.getText() + "#" + txtUsername.getText() + "#" + txtPassword.getText());
				
				JOptionPane.showMessageDialog(null, "Created");
				//after creating, we go back to first page.
				dispose();
				Start st = new Start();
				st.setVisible(true);
			}
		});
		btnCreate.setBounds(172, 145, 89, 23);
		contentPane.add(btnCreate);
		//going back to first page.
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Century", Font.PLAIN, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Start st = new Start();
				st.setVisible(true);
			}
		});
		btnBack.setBounds(172, 179, 89, 23);
		contentPane.add(btnBack);
	}
}
