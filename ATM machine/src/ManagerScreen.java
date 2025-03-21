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
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

public class ManagerScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerScreen frame = new ManagerScreen();
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
	public ManagerScreen() {
		setTitle("Manager Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 611);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton Summary_button = new JButton("Summary");
		/*
		 * Adding action event  for shutdown button in manager Screen
		 */
		Summary_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//Creating a text area to display transaction by using file and buffer reader
				JTextArea textArea = new JTextArea(); 
				getContentPane().add(textArea); 
				textArea.setEditable(false);
				textArea.setRows(5);
				textArea.setBounds(43, 123, 506, 434); 
				try {
				String textLine;
				FileReader fr = new FileReader("Transaction.txt"); 
				BufferedReader reader = new BufferedReader(fr);
				while((textLine=reader.readLine()) != null) 
				{
				textLine = reader.readLine();
				textArea.read(reader, "jSummary_textArea");
				}
				}
				catch (IOException ioe) 
				{ 
					System.err.println(ioe); 
					System.exit(1);
				}

			}
		});
		//components for displaying properties
		JLabel lblNewLabel = new JLabel("Welcome Manager To Portal");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		
		JButton Shutdown_button = new JButton("Shutdown");
		Shutdown_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
	
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(254)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(196, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(598, Short.MAX_VALUE)
					.addComponent(Summary_button, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(598, Short.MAX_VALUE)
					.addComponent(Shutdown_button, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(127)
					.addComponent(Summary_button, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(56)
					.addComponent(Shutdown_button, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(262))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
