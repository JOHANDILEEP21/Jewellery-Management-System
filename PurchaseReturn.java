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
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseReturn extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
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
					PurchaseReturn frame = new PurchaseReturn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PurchaseReturn() {
		setTitle("PURCHASE RETURN");
		setResizable(false);
		
		setBounds(200, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPurchaseDetails = new JLabel("Purchase Return details");
		lblPurchaseDetails.setFont(new Font("Script MT Bold", Font.BOLD, 30));
		lblPurchaseDetails.setForeground(Color.WHITE);
		lblPurchaseDetails.setBounds(130, 25, 340, 30);
		contentPane.add(lblPurchaseDetails);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDate.setBounds(353, 90, 40, 22);
		contentPane.add(lblDate);
		
		SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = myformat.format(date);
		
		textField = new JTextField();
		textField.setEditable(true);
		textField.setBounds(403, 90, 150, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(today);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblProductName.setBounds(120, 150, 150, 17);
		contentPane.add(lblProductName);
		
		textField_1 = new JTextField();
		textField_1.setEditable(true);
		textField_1.setBounds(340, 150, 200, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblQuantities = new JLabel("Quantity");
		lblQuantities.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblQuantities.setBounds(120, 250, 150, 20);
		contentPane.add(lblQuantities);
		
		textField_2 = new JTextField();
		textField_2.setEditable(true);
		textField_2.setBounds(340, 250, 200, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblAmount.setBounds(120, 350, 86, 20);
		contentPane.add(lblAmount);
		
		textField_3 = new JTextField();
		textField_3.setEditable(true);
		textField_3.setBounds(340, 350, 200, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSave.setBounds(89, 510, 100, 25);
		contentPane.add(btnSave);
		btnSave.addActionListener(this);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				try {
					con.close();
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnClose.setBounds(430, 510, 100, 25);
		contentPane.add(btnClose);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnClear.setBounds(258, 510, 100, 25);
		contentPane.add(btnClear);
		
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
		try{
			Statement stmt = con.createStatement();
			stmt.close();
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null,ex.getMessage(), "error",JOptionPane.INFORMATION_MESSAGE);
		}			
}
	public void actionPerformed(ActionEvent e){
		try{
			Statement stmt   = con.createStatement();
			String productname = null;
			sSQL1= "select * from purchase where Productname='"+productname+"';";
			ResultSet rst1 = stmt.executeQuery(sSQL1);
			boolean record1 = rst1.next();
			if(record1){
				textField_1.setText(rst1.getString("ProductName"));
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
		String returndate = textField.getText();
		String prodname = textField_1.getText();
		String quantity = textField_2.getText();
		String amount = textField_3.getText();
		
		if(returndate.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please enter Date");
		}
		else if(prodname.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please enter Product Name");
		}
		else if(quantity.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please enter Quantity");
		}
		else if(amount.isEmpty()){
			JOptionPane.showMessageDialog(this,"Please enter Amount");
		}
		else{
			try{
				Statement stmt   = con.createStatement();
				sSQL = "insert into purchasereturn(purdate,ProductName,Quantity,Amount) values('"+returndate+"','"+prodname+"','"+quantity+"','"+amount+"');";
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
