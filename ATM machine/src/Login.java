/*
 * @Author Nikhilesh Kovvuri
 * Date: 24-03-2021
 * version:1.0
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Login extends JFrame {

	//declaring variables for the program
	private static final long serialVersionUID = 1L;
	private JTextField Accountno_textField;
	private JPasswordField Pin_field;
	private JButton Login_button;
	private JPanel contentPane;
	private int attempt = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 611, 480);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// components for the frame to display content

		JLabel lblNewLabel = new JLabel("Enter Login Details");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));

		Accountno_textField = new JTextField();
		Accountno_textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		Accountno_textField.setColumns(10);

		Pin_field = new JPasswordField();
		Pin_field.setFont(new Font("Tahoma", Font.PLAIN, 32));

		JLabel lblAccount = new JLabel("Account No");
		lblAccount.setBackground(Color.BLACK);
		lblAccount.setForeground(Color.BLACK);
		lblAccount.setFont(new Font("Tahoma", Font.PLAIN, 31));

		JLabel lblPin = new JLabel("Pin");
		lblPin.setForeground(Color.BLACK);
		lblPin.setBackground(Color.CYAN);
		lblPin.setFont(new Font("Tahoma", Font.PLAIN, 31));

		Login_button = new JButton("Login");
		Login_button.setFont(new Font("Tahoma", Font.PLAIN, 26));
		Login_button.addActionListener(new ActionListener() {
/*
 * Adding action event  for login button 
 */
			public void actionPerformed(ActionEvent e) {
				String userName = Accountno_textField.getText();
				String password = Pin_field.getText();
				
				// try catch statement for connecting to data base and verifying login details
				try {
					Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_machine","root", "Mybro@1998");

					PreparedStatement st = (PreparedStatement) connection
							.prepareStatement("Select Account_number, Pin from Bank where Account_number=? and Pin=?");

					st.setString(1, userName);
					st.setString(2, password);
					ResultSet rs = st.executeQuery();
					if(attempt!=0) {
						if (rs.next()) {
							JOptionPane.showMessageDialog(Login_button, "Logged in"); 
							Receipt.Account_Number= userName;
						}
						if (userName.equals("999999") && password.equals("1234"))
						{
							System.out.println("manager");
							dispose();
							ManagerScreen.main(null);

						}


						else if (userName.equals("432344") && password.equals("4564"))
						{
							System.out.println("customer"); 
							dispose();
							UserHome.main(null);
						}
						else if(attempt>=3)
						{
							JOptionPane.showMessageDialog(Login_button, "You have failed 3 attempts! Removing card..."); 
							dispose(); 
							Welcome.main(null);
						}

						else
						{
							attempt++ ;
						}

					}
				}

				catch (SQLException sqlException) 
				{ 
					sqlException.printStackTrace();
				}
			}

		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(134)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(220)
										.addComponent(lblAccount, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(250)
										.addComponent(lblPin, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(165)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(Pin_field, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
												.addComponent(Accountno_textField, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(215)
										.addComponent(Login_button, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(165, Short.MAX_VALUE))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
						.addGap(29)
						.addComponent(lblAccount, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(Accountno_textField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(lblPin, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(Pin_field, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(Login_button, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addGap(151))
				);
		contentPane.setLayout(gl_contentPane);
	}
}