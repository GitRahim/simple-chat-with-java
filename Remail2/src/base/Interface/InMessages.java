package base.Interface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import base.Client;

public class InMessages extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String user;
	//the messages sent from server will be saved in this.
	static String msgs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InMessages frame = new InMessages(msgs);
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
	public InMessages(String msgs) {
		Client client = new Client();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 333);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//for going back to menu page.
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
		btnBack.setBounds(103, 267, 89, 23);
		contentPane.add(btnBack);
		//dispose and create this page again with messages received again from server.
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send("4" + "#" + user);
				String msgs = client.readString();
				dispose();
				InMessages in = new InMessages(msgs);
				in.user = user;
				in.setTitle(user+ " - In Messages");
				in.setVisible(true);
			}
		});
		btnRefresh.setFont(new Font("Century", Font.PLAIN, 11));
		btnRefresh.setBounds(277, 267, 89, 23);
		contentPane.add(btnRefresh);
		
		//messages sent by server to Menu page. then in Menu page they were sent as parameter of this page's constructor. now they are shown in a JtextArea.
		JTextArea textArea = new JTextArea(msgs);
		textArea.setFont(new Font("Century", Font.PLAIN, 12));
		JScrollPane scrl = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrl.setBounds(10, 11, 461, 245);
		contentPane.add(scrl);
		
		
	}
}
