package User_Queries;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Progress_Bar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JLabel progressMessage;
	public JProgressBar progressBar;
	private JLabel lblNewLabel;

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
			Progress_Bar progress = new Progress_Bar();
			progress.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			progress.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the progress.
	 */
	public Progress_Bar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Progress_Bar.class.getResource("/Images/restaurant_Icon.png")));
		setBounds(100, 100, 527, 348);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(214, 153, 259, 40);
		progressBar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		progressBar.setBackground(new Color(204, 204, 204));
		progressBar.setToolTipText("");
		progressBar.setForeground(Color.BLUE);
		progressBar.setStringPainted(true);
		contentPanel.setLayout(null);
		
		progressMessage = new JLabel("<html><b> Setting Up Database ... </b></html>");
		progressMessage.setBounds(214, 60, 231, 62);
		progressMessage.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPanel.add(progressMessage);
		contentPanel.add(progressBar);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(6, 60, 196, 215);
		lblNewLabel.setIcon(new ImageIcon(Progress_Bar.class.getResource("/Images/restaurant-icon2.png.jpg")));
		contentPanel.add(lblNewLabel);
		
	}
	
	public void close(){
		
		System.exit(0);
	}
}
