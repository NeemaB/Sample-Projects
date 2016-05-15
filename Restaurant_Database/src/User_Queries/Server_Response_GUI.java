package User_Queries;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.ComponentOrientation;
import javax.swing.border.LineBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import java.awt.Toolkit;

public class Server_Response_GUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblresults;
	private JScrollPane scrollPane;
	private JTextPane results;
	private String helpText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			Server_Response_GUI dialog = new Server_Response_GUI(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Server_Response_GUI(String serverResponse) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Server_Response_GUI.class.getResource("/Images/restaurant_Icon.png")));
		
		setTitle("Server Response\r\n");
		setBounds(100, 100, 533, 438);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(204, 204, 204));
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		lblresults = new JLabel("Results:");
		lblresults.setBounds(60, 21, 183, 24);
		lblresults.setFont(new Font("SansSerif", Font.PLAIN, 18));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 59, 384, 236);
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(new LineBorder(Color.BLACK, 2, true));
		
		JLabel label = new JLabel("");
		label.setBounds(473, 6, 38, 35);
		label.setIcon(new ImageIcon(Server_Response_GUI.class.getResource("/Images/restaurantIcon1.png")));
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(473, 74, 38, 35);
		label_1.setIcon(new ImageIcon(Server_Response_GUI.class.getResource("/Images/restaurantIcon2.png")));
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(473, 163, 38, 35);
		label_2.setIcon(new ImageIcon(Server_Response_GUI.class.getResource("/Images/restaurantIcon3.png")));
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(473, 248, 38, 35);
		label_3.setIcon(new ImageIcon(Server_Response_GUI.class.getResource("/Images/restaurantIcon4.png")));
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(473, 319, 38, 35);
		label_4.setIcon(new ImageIcon(Server_Response_GUI.class.getResource("/Images/restaurantIcon1.png")));
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(6, 319, 38, 35);
		label_5.setIcon(new ImageIcon(Server_Response_GUI.class.getResource("/Images/restaurantIcon1.png")));
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(93, 319, 38, 35);
		label_6.setIcon(new ImageIcon(Server_Response_GUI.class.getResource("/Images/restaurantIcon2.png")));
		
		JLabel label_7 = new JLabel("");
		label_7.setBounds(186, 319, 38, 35);
		label_7.setIcon(new ImageIcon(Server_Response_GUI.class.getResource("/Images/restaurantIcon3.png")));
		
		JLabel label_8 = new JLabel("");
		label_8.setBounds(280, 319, 38, 35);
		label_8.setIcon(new ImageIcon(Server_Response_GUI.class.getResource("/Images/restaurantIcon4.png")));
		
		JLabel label_9 = new JLabel("");
		label_9.setBounds(386, 319, 38, 35);
		label_9.setIcon(new ImageIcon(Server_Response_GUI.class.getResource("/Images/restaurantIcon2.png")));
		
		results = new JTextPane();
		results.setEditable(false);
		results.setSelectionColor(Color.BLACK);
		results.setBorder(null);
		scrollPane.setViewportView(results);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		contentPanel.setLayout(null);
		
		results.setText(serverResponse);
		contentPanel.add(scrollPane);
		contentPanel.add(label_1);
		contentPanel.add(label_2);
		contentPanel.add(label_3);
		contentPanel.add(lblresults);
		contentPanel.add(label);
		contentPanel.add(label_5);
		contentPanel.add(label_6);
		contentPanel.add(label_7);
		contentPanel.add(label_8);
		contentPanel.add(label_9);
		contentPanel.add(label_4);
		this.setModal(true);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("About");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Help");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, helpText);
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem);
	}
	
	public void setLabel(String labelString){
		
		lblresults.setText(labelString);
	}
	
	public JTextPane getResults(){
		
		return results;
	}
	
	public void modifyHelpMessage(String text){
		
		helpText = text;
	}
}
