/*
 * @Author Nikhilesh Kovvuri
 * Date: 24-03-2021
 * version:1.0
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;

public class UserHome extends JFrame {
	//declaring variables for the program
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField Balance_Textfield;
    private String Amount;
    public String Receipts;
    double TotalWithdraw=0;
    public double WithdrawAmount=0;
    double Withdraw=0;
    double BalanceCustomer=0;
    double BalanceRemaining=0;
    String Account_number = "432344";
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserHome frame = new UserHome();
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
    public UserHome() {
    	setTitle("Customer Portal");
    	getContentPane().setBackground(Color.CYAN);
    	setBounds(450, 190, 611, 480);
    	//components for displaying properties
    	JLabel WelcomeCostomer_label = new JLabel("Welcome to Costomer portal");
    	WelcomeCostomer_label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
    	
    	JLabel Checkbalance_lbl = new JLabel("Check Account Balance");
    	Checkbalance_lbl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
    	
    	Balance_Textfield = new JTextField();
    	Balance_Textfield.setColumns(10);
    	
    	JButton Balance_button = new JButton("Check Balance");
    	Balance_button.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			try
				{
					Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_machine","root", "Mybro@1998");

					PreparedStatement st = connection.prepareStatement("select balance from Bank where Account_number='"+432344+"';");

					ResultSet rs = st.executeQuery();
					while(rs.next())
					{
						String customerBalance = rs.getString("balance");
						Balance_Textfield.setText(customerBalance);
					}
				}

				catch (SQLException sqlException) 
				{
					sqlException.printStackTrace();
				}
			
    		}
    	});
    	//components for displaying properties
    	JLabel Withdraw_lbl = new JLabel("Withdraw Amount");
    	Withdraw_lbl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
    	
    	JComboBox comboBox = new JComboBox();
    	comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
    	comboBox.setModel(new DefaultComboBoxModel(new String[] {"10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "110", "120", "130", "140", "150", "160", "170", "180", "190", "200", "210", "220", "230", "240", "250"}));
    	
    	JButton Logout_button = new JButton("Remove Card");
    	Logout_button.addActionListener(new ActionListener() {
    		/*
    		 * Adding action event  for Logout button in Costomer portal Screen
    		 */
    		public void actionPerformed(ActionEvent e) {
    			int a = JOptionPane.showConfirmDialog(Logout_button, "Are you sure?");
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    Login obj = new Login();
                    obj.setTitle("Login");
                    obj.setVisible(true);
                }
                dispose();
                Login obj = new Login();

                obj.setTitle("Login");
                obj.setVisible(true);
    		}
    	});
    	
    	JRadioButton Receipt_radiobutton = new JRadioButton("Print with receipt");
    	
    	JButton Withdraw_button = new JButton("Withdraw");
    	Withdraw_button.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) 
    		{
    			
    			try {
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_machine","root", "Mybro@1998");
					Statement sts = connection.createStatement();
					String sql = "select balance from Bank where Account_number='"+432344+"'";
					ResultSet rs =sts.executeQuery(sql);
					while(rs.next()) {
						BalanceCustomer= rs.getDouble("balance");
				        BalanceRemaining = BalanceCustomer;
					}
				}catch (SQLException sqlException) {
		            sqlException.printStackTrace();
		        }
				Amount= (String) comboBox.getSelectedItem();
				Withdraw=(Double.parseDouble(Amount));
				WithdrawAmount = Withdraw;
			 	/**
			 	 * if condition to check if the withdrawn amount is greater than 250
			 	 */
				TotalWithdraw();
				if(TotalWithdraw < 250) { 
					
					if(Withdraw > BalanceCustomer || Withdraw==0) {
						JOptionPane.showMessageDialog(Withdraw_button, "you have exceeded limit ! Logging off");
						dispose();
						Welcome.main(null);
					}
					else {
					
						
						/**
						 * if condition to check if the user has selected the radio button in order to print the receipt
						 */
						  if (Receipt_radiobutton.isSelected()) {
							  WithdrawAmount = BalanceCustomer  -(Withdraw / 1.16);
								TotalWithdraw= TotalWithdraw + Withdraw;
								JOptionPane.showMessageDialog(Withdraw_button, "Amount Withdrawn:" + Withdraw + " Current Balance: " + WithdrawAmount + "\n");
																
								 Receipt.main(TotalWithdraw);
								 
						  }
						  else { 
								WithdrawAmount = BalanceCustomer  -(Withdraw / 1.16);
								TotalWithdraw= TotalWithdraw + Withdraw;
								JOptionPane.showMessageDialog(Withdraw_button, "Amount Withdrawn:" + Withdraw + " Current Balance: " + WithdrawAmount + "\n");
								
							}
								
							}
						}
			
				
				else
				{
					JOptionPane.showMessageDialog(Withdraw_button,"You have reached the max limit of withdrawl, please try again tommorow");
				 Welcome.main(null);
				 dispose();
				}
				
				try {
					/**
					 * try catch statement to update the table in mySQL
					 */
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_machine","root", "Mybro@1998");
					 Statement stmt = conn.createStatement();
					 System.out.println(TotalWithdraw);
					 String sql = "update Bank set balance = " +WithdrawAmount+" where Account_number = '"+432344+"'";
					String sql_= "update Bank set withdraw=" +TotalWithdraw+" where Account_number = '"+432344+"'";
					 stmt.execute(sql);
					 stmt.execute(sql_);
					 conn.close();
				}catch (Exception exc)
				{
                    exc.printStackTrace(); 
                    
                    }
				  try {
						 CustomerSummary();
					 } 
				 
				 catch (IOException el) 
				 {
					 el.printStackTrace();
						 // TODO Auto-generated catch block el.printStackTrace();

				 } 
				
			}
			
    		public double TotalWithdraw() {
                double totalWithdrawl, WithdrawLimit = 0;
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_machine","root", "Mybro@1998");
                    PreparedStatement st =  conn
                            .prepareStatement("select withdraw from Bank where Account_number='"+432344+"';");
                    ResultSet rs = st.executeQuery();
                    while(rs.next()) {
                        totalWithdrawl=rs.getDouble("withdrawl");
                        WithdrawLimit = totalWithdrawl;
                    }

                }
                catch (Exception exc) 
                {
                    exc.printStackTrace();
                }

                return WithdrawLimit ;
            }
    		/*
    		 * function to delclare and initialise file and buffer reader in manager class
    		 */
    		public void CustomerSummary() throws IOException {
    	 	
    	 		int i;
    	 		FileOutputStream fOut;
    	 		fOut =new FileOutputStream("Transaction.txt", true);
    	 		String s=  "Account Number "+ Account_number +"       "+ "Withdrawn Amount     "+"    € " +Withdraw ;
    	 		String lineSeparator = System.getProperty("line.separator");
    	 		byte[] textBytes = s.getBytes();
    	 		fOut.write(textBytes);
    	 		fOut.write(lineSeparator.getBytes());	
    			}
		
		});
  
    
	    	
    	
    	GroupLayout groupLayout = new GroupLayout(getContentPane());
    	groupLayout.setHorizontalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    					.addComponent(Checkbalance_lbl, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
    					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
    						.addComponent(Balance_button, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
    						.addComponent(Balance_Textfield, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)))
    				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    					.addGroup(groupLayout.createSequentialGroup()
    						.addGap(128)
    						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    							.addComponent(Withdraw_lbl, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
    							.addGroup(groupLayout.createSequentialGroup()
    								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    								.addGap(18)
    								.addComponent(Receipt_radiobutton, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))))
    					.addGroup(groupLayout.createSequentialGroup()
    						.addGap(145)
    						.addComponent(Withdraw_button, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)))
    				.addGap(115))
    			.addGroup(groupLayout.createSequentialGroup()
    				.addGap(189)
    				.addComponent(WelcomeCostomer_label, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
    				.addContainerGap(231, Short.MAX_VALUE))
    			.addGroup(groupLayout.createSequentialGroup()
    				.addGap(223)
    				.addComponent(Logout_button, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
    				.addContainerGap(309, Short.MAX_VALUE))
    	);
    	groupLayout.setVerticalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addGap(19)
    				.addComponent(WelcomeCostomer_label, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
    				.addGap(47)
    				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
    					.addComponent(Checkbalance_lbl, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
    					.addComponent(Withdraw_lbl, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
    				.addGap(18)
    				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
    						.addComponent(Balance_Textfield, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
    						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
    					.addComponent(Receipt_radiobutton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
    				.addGap(18)
    				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
    					.addComponent(Withdraw_button, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
    					.addComponent(Balance_button, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
    				.addGap(18)
    				.addComponent(Logout_button, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
    				.addGap(159))
    	);
    	getContentPane().setLayout(groupLayout);

    
    }
	
	
}