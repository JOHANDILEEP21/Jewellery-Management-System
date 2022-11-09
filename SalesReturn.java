package MainForm;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesReturn extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_8;
	Connection con = null;
	String driver = "com.mysql.jdbc.Driver";
	String  url = "jdbc:mysql://localhost:3306/demo";
	String dbuser = "root";
	String dbpass = "root";
	
	private String sSQL,sSQL1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesReturn frame = new SalesReturn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SalesReturn() {
		setTitle("SALES RETURN");
		setResizable(false);
	
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = myformat.format(date);
		
		JLabel lblSalesReturnDetails = new JLabel("Sales Return Details");
		lblSalesReturnDetails.setForeground(Color.WHITE);
		lblSalesReturnDetails.setFont(new Font("Script MT Bold", Font.BOLD, 30));
		lblSalesReturnDetails.setBounds(145, 25, 297, 30);
		contentPane.add(lblSalesReturnDetails);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDate.setBounds(360, 90, 41, 20);
		contentPane.add(lblDate);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(415, 90, 150, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(today);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblProductName.setBounds(120, 225, 128, 20);
		contentPane.add(lblProductName);
		
		textField_3 = new JTextField();
		textField_3.setBounds(290, 225, 160, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblQuantity.setBounds(120, 400, 128, 20);
		contentPane.add(lblQuantity);
		
		textField_4 = new JTextField();
		textField_4.setBounds(290, 280, 160, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCustomerName.setBounds(120, 280, 128, 20);
		contentPane.add(lblCustomerName);
		
		textField_5 = new JTextField();
		textField_5.setBounds(290, 340, 160, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblMobileNo = new JLabel("Mobile NO");
		lblMobileNo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMobileNo.setBounds(120, 340, 128, 20);
		contentPane.add(lblMobileNo);
		
		textField_6 = new JTextField();
		textField_6.setBounds(290, 400, 160, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblAmount.setBounds(120, 450, 83, 20);
		contentPane.add(lblAmount);
		
		textField_8 = new JTextField();
		textField_8.setBounds(290, 450, 160, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSave.setBounds(60, 510, 100, 25);
		contentPane.add(btnSave);
		btnSave.addActionListener(this);
		
		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 15));
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
		btnClose.setBounds(430, 510, 100, 25);
		contentPane.add(btnClose);
		
		JButton btnFind = new JButton("Clear");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_8.setText("");
			}
		});
		btnFind.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnFind.setBounds(245, 509, 100, 25);
		contentPane.add(btnFind);
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,dbuser ,dbpass);
		}
		catch(ClassNotFoundException ex){

			JOptionPane.showMessageDialog(null,ex.getMessage(), "Could not find the database driver" ,JOptionPane.PLAIN_MESSAGE);
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null,e.getMessage(),"Could not connect to the database",JOptionPane.INFORMATION_MESSAGE);
		}
}
	public void actionPerformed(ActionEvent e){
		try{
			Statement stmt   = con.createStatement();
			sSQL1= "select * from purchase where Productname='"+getName()+"';";
			ResultSet rst1 = stmt.executeQuery(sSQL1);
			boolean record1 = rst1.next();
			if(record1){
				textField_3.setText(rst1.getString("ProductName"));
			}
			stmt.close();
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null,ex.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
		}
		String action = e.getActionCommand();
		if(action.equals("Save")){
			RecordSave();
		}
	}
	
	public void RecordSave(){
		String date = textField_1.getText();
		String prodname = textField_3.getText();
		String cusname = textField_4.getText();
		String cusmobileno = textField_5.getText();
		String quantity = textField_6.getText();
		String amount = textField_8.getText();
		
		if(date.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please enter Date");
		}
		else if(prodname.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please enter Product Name");
		}
		else if(cusname.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please enter Customer Name");
		}
		else if(cusmobileno.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please enter Customer Mobile Number");
		}
		else if(quantity.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please enter Quantity");
		}
		else if(amount.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please enter Amount");
		}
		else if((cusmobileno.length()>9)&&(cusmobileno.length()<11)){
			try{
				Statement stmt   = con.createStatement();
				sSQL = "insert into salesreturn(Date,ProductName,CusName,MobNo,Quantity,Amount) values('"+date+"','"+prodname+"','"+cusname+"','"+cusmobileno+"','"+quantity+"','"+amount+"');";
				stmt.executeUpdate(sSQL);
				stmt.close();
				JOptionPane.showMessageDialog(null, "Record Updated Successfully");
			}
			catch(SQLException e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
