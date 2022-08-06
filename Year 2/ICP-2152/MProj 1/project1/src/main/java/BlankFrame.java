import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class BlankFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtDeposit;
	private JTextField txtWithdraw;
	private JLabel lblBalance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlankFrame frame = new BlankFrame(new BankAccount(629, 250));
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
	public BlankFrame(final BankAccount anAccount) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(50);
		flowLayout.setHgap(100);
		contentPane.add(panel, BorderLayout.CENTER);
		
				JButton btnWithdraw = new JButton("Withdraw");
				btnWithdraw.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (!(txtWithdraw.getText() == "")) {
							try {
								double amount = Double.parseDouble(txtWithdraw.getText());
								anAccount.withdraw(amount);
								txtWithdraw.setText("");
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				});
				panel.add(btnWithdraw);
				btnWithdraw.setHorizontalAlignment(SwingConstants.LEFT);

		txtWithdraw = new JTextField();
		panel.add(txtWithdraw);
		txtWithdraw.setColumns(10);
		
				JButton btnDeposit = new JButton("Deposit");
				btnDeposit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (!(txtDeposit.getText() == "")) {
							try {
								double amount = Double.parseDouble(txtDeposit.getText());
								anAccount.deposit(amount);
								txtDeposit.setText("");
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				});
				panel.add(btnDeposit);
				btnDeposit.setVerticalAlignment(SwingConstants.BOTTOM);
				btnDeposit.setHorizontalAlignment(SwingConstants.LEFT);

		txtDeposit = new JTextField();
		panel.add(txtDeposit);
		txtDeposit.setColumns(10);
				
		JLabel lblHeader = new JLabel("Bank of Utopia");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
		contentPane.add(lblHeader, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{141, 113, 0, 0, 0, 92, 0};
		gbl_panel_1.rowHeights = new int[]{30, 25, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
				JButton btnBalance = new JButton("Show balance");
				btnBalance.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							lblBalance.setText("Your balance is: " + anAccount.getBalance());
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				});
				GridBagConstraints gbc_btnBalance = new GridBagConstraints();
				gbc_btnBalance.anchor = GridBagConstraints.NORTHWEST;
				gbc_btnBalance.insets = new Insets(0, 0, 5, 5);
				gbc_btnBalance.gridx = 1;
				gbc_btnBalance.gridy = 0;
				panel_1.add(btnBalance, gbc_btnBalance);
				btnBalance.setVerticalAlignment(SwingConstants.TOP);
		
		lblBalance = new JLabel("Your balance is:");
		GridBagConstraints gbc_lblBalance = new GridBagConstraints();
		gbc_lblBalance.gridwidth = 2;
		gbc_lblBalance.insets = new Insets(0, 0, 0, 5);
		gbc_lblBalance.anchor = GridBagConstraints.WEST;
		gbc_lblBalance.gridx = 1;
		gbc_lblBalance.gridy = 1;
		panel_1.add(lblBalance, gbc_lblBalance);
		lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalance.setVerticalAlignment(SwingConstants.BOTTOM);
		
	}

}
