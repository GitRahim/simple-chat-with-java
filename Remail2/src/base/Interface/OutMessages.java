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


public class OutMessages extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String user;
	//the messages sent from server will be saved in this.
	static String oMsgs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutMessages frame = new OutMessages(oMsgs);
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
	public OutMessages(String oMsgs) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 333);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		btnBack.setBounds(197, 267, 89, 23);
		contentPane.add(btnBack);
		//messages sent by server to Menu page. then in Menu page they were sent as parameter of this page's constructor. now they are shown in a JtextArea.
		JTextArea textArea = new JTextArea(oMsgs);
		textArea.setFont(new Font("Century", Font.PLAIN, 12));
		textArea.setFont(new Font("Century", Font.PLAIN, 12));
		JScrollPane scrl = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrl.setBounds(10, 11, 461, 245);
		contentPane.add(scrl);		
	}
}
