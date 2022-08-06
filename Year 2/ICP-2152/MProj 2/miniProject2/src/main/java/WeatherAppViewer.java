import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class WeatherAppViewer {

	private JFrame frmGetBbcWeather;
	private JTextField txtUrl;
	private JLabel lblUrl;
	private JTextArea txtaForecast;
	private JButton btnForecast;
	private JLabel weatherIcon;
	private JTree dtree;
	
	private WeatherRSSParser parser = new DOMXPathWeatherRSSParserImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeatherAppViewer window = new WeatherAppViewer();
					window.frmGetBbcWeather.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WeatherAppViewer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGetBbcWeather = new JFrame();
		frmGetBbcWeather.setTitle("Get BBC Weather");
		frmGetBbcWeather.setBounds(100, 100, 450, 410);
		frmGetBbcWeather.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmGetBbcWeather.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblUrl = new JLabel("Location: ");
		lblUrl.setBounds(26, 44, 56, 16);
		panel.add(lblUrl);
		
		txtUrl = new JTextField();
		txtUrl.setBounds(94, 41, 260, 22);
		txtUrl.setText("");
		panel.add(txtUrl);
		txtUrl.setColumns(10);
		
		btnForecast = new JButton("Forecast");
		btnForecast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txtUrl.getText().contentEquals("")) {
					//get geoid from location
					String gid = parser.getGeoId(txtUrl.getText().toLowerCase());					
					parser.setURL(gid);
					//set text area
					try {
						String fcast = parser.getHeadlines();
						txtaForecast.setText(fcast);
					} catch (IllegalArgumentException e) {
						//if URL is incorrect, display error message in text area
						txtaForecast.setText("An error occurred, " + e.getMessage());
					}
					//display description in tree
					String[][] fdesc = parser.getDescription(); 
					dtree.setModel(new DefaultTreeModel(
							new DefaultMutableTreeNode("Additional Info") {
								{
									DefaultMutableTreeNode node_1;
									for (String[] strings : fdesc) {
										node_1 = new DefaultMutableTreeNode(strings[0]);										
												node_1.add(new DefaultMutableTreeNode(strings[1]));
										add(node_1);
									}
								}
							}
						));
					//set weather icon
					try {
						String fcast = parser.getHeadlines();
						fcast = fcast.substring(fcast.indexOf(": ") + 2);
						fcast = fcast.substring(0, fcast.indexOf(", "));						
						ImageIcon icn = new ImageIcon(getClass().getResource("/" + fcast + ".png"));
						weatherIcon.setIcon(icn);					
						
					} catch (NullPointerException e) {
						//if image file for the weather type doesn't exist
						//or if the weather condition is "Not available",
						//set to the 'Not available' icon
						ImageIcon icn = new ImageIcon(getClass().getResource("/Not available.png"));
						weatherIcon.setIcon(icn);
					}
				}
			}
		});
		btnForecast.setBounds(167, 76, 97, 25);
		panel.add(btnForecast);
		
		txtaForecast = new JTextArea();
		txtaForecast.setBounds(12, 114, 408, 40);
		panel.add(txtaForecast);
		
		weatherIcon = new JLabel(new ImageIcon(getClass().getResource("/Not available.png")));
		weatherIcon.setBounds(314, 167, 106, 106);
		panel.add(weatherIcon);
		
		dtree = new JTree();
		dtree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Additional Info") ));
		dtree.setBounds(12, 167, 290, 183);
		panel.add(dtree);
	}
}
