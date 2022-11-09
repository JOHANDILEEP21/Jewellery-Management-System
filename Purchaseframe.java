package MainForm;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class Purchaseframe extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	Connection con = null;
	String driver = "com.mysql.jdbc.Driver";
	String  url = "jdbc:mysql://localhost:3306/demo";
	String dbuser = "root";
	String dbpass = "root";
	
	String sSQL,sSQL1,sSQL2,sSQL3,sSQL4,sSQL5;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Purchaseframe frame = new Purchaseframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Purchaseframe() {
		setResizable(false);
		setTitle("PURCHASE ");
	
		setBounds(200, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = myformat.format(date);
		
		JLabel lblPurchaseEntry = new JLabel("PURCHASE  ENTRY");
		lblPurchaseEntry.setForeground(new Color(255, 255, 255));
		lblPurchaseEntry.setFont(new Font("Lucida Handwriting", Font.BOLD, 30));
		lblPurchaseEntry.setBounds(225, 35, 340, 35);
		contentPane.add(lblPurchaseEntry);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDate.setBounds(520, 100, 50, 20);
		contentPane.add(lblDate);
		
		textField_1 = new JTextField();
		textField_1.setEditable(true);
		textField_1.setBounds(580, 100, 130, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(today);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblProductName.setBounds(180, 170, 130, 20);
		contentPane.add(lblProductName);
		
		textField_2 = new JTextField();
		textField_2.setEditable(true);
		textField_2.setBounds(355, 170, 160, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblProductCatagory = new JLabel("Product Categ(G/S)");
		lblProductCatagory.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblProductCatagory.setBounds(180, 250, 180, 25);
		contentPane.add(lblProductCatagory);
		
		textField_3 = new JTextField();
		textField_3.setEditable(true);
		textField_3.setBounds(355, 250, 160, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblQuantity.setBounds(180, 330, 125, 20);
		contentPane.add(lblQuantity);
		
		textField_4 = new JTextField();
		textField_4.setEditable(true);
		textField_4.setBounds(355, 330, 160, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAmount.setBounds(180, 410, 125, 20);
		contentPane.add(lblAmount);
		
		textField_5 = new JTextField();
		textField_5.setEditable(true);
		textField_5.setBounds(355, 410, 160, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSave.setBounds(610, 250, 100, 25);
		contentPane.add(btnSave);
		btnSave.addActionListener(this);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnClear.setBounds(610, 330, 100, 25);
		contentPane.add(btnClear);	
		
		JButton btnClose = new JButton("Close");
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
		btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnClose.setBounds(610, 410, 100, 25);
		contentPane.add(btnClose);
		try
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url,dbuser ,dbpass);
		}
		catch(ClassNotFoundException ex)
		{

			JOptionPane.showMessageDialog(null,ex.getMessage(), "Could not find the database driver" ,JOptionPane.PLAIN_MESSAGE);
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(), "Error",JOptionPane.INFORMATION_MESSAGE);
		}
		try
		{
			Statement stmt   = con.createStatement();
			stmt.close();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(), "Error",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void actionPerformed(ActionEvent arg0) 
	{
		String action = arg0.getActionCommand();
		if(action.equals("Save"))
		{
			RecordSave();
		}
	}
	
	public void RecordSave()
	{
	
		String pudate = textField_1.getText();
		String prodname = textField_2.getText();
		String prodcatagory = textField_3.getText();
		String purquantity = textField_4.getText();
		String puramount= textField_5.getText();
		
		if(puramount.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Amount, Becouse it is Empty ");
		}
		else if(prodname.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Product Name");
		}
		else if(prodcatagory.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Product Catagory");
		}
		else if(purquantity.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please Enter Quantity");
		}
		else if(pudate.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Date is empty");
		}
		else{
			try{
			
				Statement stmt   = con.createStatement();
				sSQL = "insert into purchase(productname,procat,quantity,amount,purdate) values('"+prodname+"','"+prodcatagory+"','"+purquantity+"','"+puramount+"','"+pudate+"');";		
				stmt.executeUpdate(sSQL);
				stmt.close();
				JOptionPane.showMessageDialog(null, "Record inserted Successfully");
				
			}catch(SQLException e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.getMessage(), "Error",JOptionPane.INFORMATION_MESSAGE);
			}
			try{
				
				Statement stmt1   = con.createStatement();
				sSQL = "insert into purchasereturn(purdate,productname,quantity,amount) values('"+pudate+"','"+prodname+"','"+purquantity+"','"+puramount+"');";		
				stmt1.executeUpdate(sSQL);
				stmt1.close();
				JOptionPane.showMessageDialog(null, "Record inserted Successfully");
				
			}catch(SQLException e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.getMessage(), "Error",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}


