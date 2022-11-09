package MainForm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class Mainframe extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection con = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainframe frame = new Mainframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Mainframe() {
		
		setTitle("WELCOME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200,30,900,700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToJewellery = new JLabel("Welcome To Jewellery Shop ");
		lblWelcomeToJewellery.setForeground(Color.BLACK);
		lblWelcomeToJewellery.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 45));
		lblWelcomeToJewellery.setBounds(150, 50, 600, 60);
		contentPane.add(lblWelcomeToJewellery);

		JLabel label = new JLabel();
		label.setBackground(Color.WHITE);
		label.setBounds(60,100,650,480);
		contentPane.add(label);
		label.setLayout(null);
		
		JButton btnNewButton1 = new JButton("Purchase Details");
		btnNewButton1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton1.setBounds(50, 158, 270, 40);
		label.add(btnNewButton1);
		btnNewButton1.addActionListener(this);
		
		JButton btnNewButton2 = new JButton("Purchase Return");
		btnNewButton2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton2.setBounds(50, 250, 270, 40);
		label.add(btnNewButton2);
		btnNewButton2.addActionListener(this);
		
		JButton btnNewButton = new JButton("Sales Details");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.setBounds(380, 158, 270, 40);
		label.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		JButton btnSalesReturn = new JButton("Sales Return");
		btnSalesReturn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSalesReturn.setBounds(380, 250, 270, 40);
		label.add(btnSalesReturn);
		btnSalesReturn.addActionListener(this);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBorderPainted(true);
		btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnClose.setBounds(200, 390, 270, 40);
		label.add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try {
					con.close();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
			}
		});
}
		public void actionPerformed(ActionEvent event){
			String action = event.getActionCommand();
			if(action=="Purchase Details"){
				Purchaseframe pf = new Purchaseframe();
				pf.setVisible(true);
			}
			else if(action=="Purchase Return"){
				PurchaseReturn pr = new PurchaseReturn();
				pr.setVisible(true);
			}
			else if(action=="Sales Details"){
				Salesdetails in1 = new Salesdetails();
				in1.setVisible(true);
			}
			else if(action=="Sales Return"){
				SalesReturn in2 = new SalesReturn();
				in2.setVisible(true);
			}
		}
}
