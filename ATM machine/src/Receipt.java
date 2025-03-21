/*
 * @Author Nikhilesh Kovvuri
 * Date: 24-03-2021
 * version:1.0
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Receipt extends JFrame {
	//declaring variables for the program
	protected static String Account_Number;
	private JPanel contentPane;
	private static JTextField Accountnumber_textField;
	private static JTextField Totalbalance_textField;
	private static JTextField Amountwithdraw_textField;
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Receipt() {
		setTitle("Receipt");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 559, 415);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//components for displaying properties
		JLabel Receiptpage_label = new JLabel("Transaction Receipt");
		Receiptpage_label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		
		JLabel Accountnumber_label = new JLabel("Account Number");
		Accountnumber_label.setFont(new Font("Times New Roman", Font.BOLD, 22));
		
		JLabel Balance_label = new JLabel("Total Balance");
		Balance_label.setFont(new Font("Times New Roman", Font.BOLD, 22));
		
		JLabel lblNewLabel = new JLabel("Amount Withdrawn");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		
		Accountnumber_textField = new JTextField();
		Accountnumber_textField.setColumns(10);
		
		Totalbalance_textField = new JTextField();
		Totalbalance_textField.setColumns(10);
		
		Amountwithdraw_textField = new JTextField();
		Amountwithdraw_textField.setColumns(10);
		
		JLabel Bankname_label = new JLabel("Bank Of Striling");
		Bankname_label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(170)
							.addComponent(Bankname_label, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(23)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(Accountnumber_label, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
										.addComponent(Balance_label, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))))
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(Accountnumber_textField, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
								.addComponent(Amountwithdraw_textField, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
								.addComponent(Totalbalance_textField, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(130, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(148, Short.MAX_VALUE)
					.addComponent(Receiptpage_label, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
					.addGap(144))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(Bankname_label, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Receiptpage_label, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Accountnumber_label, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(Accountnumber_textField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Balance_label, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(Totalbalance_textField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(Amountwithdraw_textField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(33))
		);
		contentPane.setLayout(gl_contentPane);
	}

	private void ShowDate() {
		// TODO Auto-generated method stub
		
	}
/*
 * function for displaying withdraw fields like total withdraw , balance and account number
 */
	public static void main(double withdraw) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// displaying frame using try catch  statement 
				try {
					Receipt frame = new Receipt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// try catch statement for connecting db for selecting values from the user
				try
				{
					Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_machine","root", "Mybro@1998");

					PreparedStatement st = connection.prepareStatement("select balance from Bank where Account_number='"+432344+"';");

					ResultSet rs = st.executeQuery();
					while(rs.next())
					{
						// displaying values in textfields
						String customerBalance = rs.getString("balance");
						Totalbalance_textField.setText(customerBalance);
						Accountnumber_textField.setText("432344");
						Amountwithdraw_textField.setText(String.valueOf(withdraw));
					}
				}

				catch (SQLException sqlException) 
				{
					sqlException.printStackTrace();
				}
				
			}
		});
		
	}
}
