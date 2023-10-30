package base.Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base.Client;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditAccount extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtPass;
	String user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditAccount frame = new EditAccount();
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
	public EditAccount() {
		Client client = new Client();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Name :");
		lblNewLabel.setFont(new Font("Century", Font.PLAIN, 11));
		lblNewLabel.setBounds(49, 55, 92, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewPassword = new JLabel("New Password :");
		lblNewPassword.setFont(new Font("Century", Font.PLAIN, 11));
		lblNewPassword.setBounds(49, 81, 92, 14);
		contentPane.add(lblNewPassword);
		
		txtName = new JTextField();
		txtName.setBounds(189, 52, 195, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(189, 78, 195, 20);
		contentPane.add(txtPass);
		
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
		btnBack.setBounds(167, 183, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnSave = new JButton("Save");
		//if Save button is clicked, new information are sent as a string to server and then saved to account and its file in Account class.
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//3 represents Edit Account
				client.send("3" + "#" + user + "#" + txtName.getText() +"#" + txtPass.getText());
				JOptionPane.showMessageDialog(null, "Saved");
				//then we go back to menu page
				dispose();
				Menu mn = new Menu();
				mn.user = user;
				mn.setTitle(user + " - Menu");
				mn.setVisible(true);
			}
		});
		btnSave.setFont(new Font("Century", Font.PLAIN, 11));
		btnSave.setBounds(167, 149, 89, 23);
		contentPane.add(btnSave);
	}

}
