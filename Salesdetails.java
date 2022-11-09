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

import java.util.Date;

public class Salesdetails extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	Connection con = null;
	String driver = "com.mysql.jdbc.Driver";
	String  url = "jdbc:mysql://localhost:3306/demo";
	String dbuser = "root";
	String dbpass = "root";
	String sSQL,sSQL1,sSQL2,sSQL3,sSQL4;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Salesdetails frame = new Salesdetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Salesdetails() {
		setTitle("SALES DETAILS");
		setResizable(false);
	
		setBounds(200, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = myformat.format(date);
		
		JLabel lblSalesDetails = new JLabel("Sales Details");
		lblSalesDetails.setForeground(Color.WHITE);
		lblSalesDetails.setFont(new Font("Script MT Bold", Font.BOLD, 35));
		lblSalesDetails.setBounds(285, 10, 220, 40);
		contentPane.add(lblSalesDetails);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDate.setBounds(570, 60, 46, 20);
		contentPane.add(lblDate);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(630, 60, 120, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(today);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCustomerName.setBounds(150, 110, 125, 20);
		contentPane.add(lblCustomerName);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_2.setBounds(345, 110, 160, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblMobileNo = new JLabel("Mobile No");
		lblMobileNo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMobileNo.setBounds(150, 170, 125, 20);
		contentPane.add(lblMobileNo);
		
		textField_3 = new JTextField();
		textField_3.setBounds(345, 170, 160, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblCustomerAddress = new JLabel("Customer Address");
		lblCustomerAddress.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCustomerAddress.setBounds(150, 230, 139, 20);
		contentPane.add(lblCustomerAddress);
		
		textField_4 = new JTextField();
		textField_4.setBounds(345, 230, 160, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblProductName.setBounds(150, 290, 125, 20);
		contentPane.add(lblProductName);
		
		textField_5 = new JTextField();
		textField_5.setBounds(345, 290, 160, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblProductCatagories = new JLabel("Product Catagories");
		lblProductCatagories.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblProductCatagories.setBounds(150, 350, 144, 20);
		contentPane.add(lblProductCatagories);
		
		textField_6 = new JTextField();
		textField_6.setBounds(345, 350, 160, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblAmount.setBounds(150, 410, 100, 20);
		contentPane.add(lblAmount);
		
		textField_7 = new JTextField();
		textField_7.setBounds(345, 410, 160, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblQuantity.setBounds(150, 470, 107, 20);
		contentPane.add(lblQuantity);
		
		textField_8 = new JTextField();
		textField_8.setBounds(345, 470, 160, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		JButton btnSve = new JButton("Save");
		btnSve.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSve.setBounds(605, 180, 100, 25);
		contentPane.add(btnSve);
		btnSve.addActionListener(this);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnClear.setBounds(605, 250, 100, 25);
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
		btnClose.setBounds(605, 320, 100, 25);
		contentPane.add(btnClose);
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,dbuser ,dbpass);	
		}
		catch(ClassNotFoundException ex){

			JOptionPane.showMessageDialog(null,ex.getMessage(), "Could not find the database driver" ,JOptionPane.PLAIN_MESSAGE);
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null,e.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
		}
}
	public void actionPerformed(ActionEvent e){
		String action = e.getActionCommand();
		if(action.equals("Save")){
			RecordSave();
		}
	}
	public void RecordSave(){
		String salesdate = textField_1.getText();
		String cusname   = textField_2.getText();
		String cusmobileno = textField_3.getText();
		String cusaddress  = textField_4.getText();
		String prodname = textField_5.getText();
		String prodcatagory = textField_6.getText();
		String quantity  = textField_7.getText();
		String amount = textField_8.getText();
		
		if(salesdate.isEmpty()){
			JOptionPane.showMessageDialog(this,"Date is empty");
		}
		else if(cusname.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please Enter customer Name");
		}
		else if(cusmobileno.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please Enter Customer mobile Number");
		}
		else if(cusaddress.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please Enter Customer Address");
		}
		else if(prodname.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please Enter Product Name");
		}
		else if(prodcatagory.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please Enter Catagory");
		}else if(quantity.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please Enter Quantity");
		}
		else if(amount.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please Enter Calculate Button Because Amount Is Empty ");
		}
		
		else if((cusmobileno.length()>9)&&(cusmobileno.length()<12)){
			try{
				Statement stmt   = con.createStatement();
				sSQL = "insert into sales(salesdate,CusName,MobNO,Address,ProductName,Procat,Quantity,Amount) values('"+salesdate+"','"+cusname+"','"+cusmobileno+"','"+cusaddress+"','"+prodname+"','"+prodcatagory+"','"+quantity+"','"+amount+"');";
				stmt.executeUpdate(sSQL);
				stmt.close();
				JOptionPane.showMessageDialog(null, "record inserted Successfully");
			}
			catch(SQLException e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
