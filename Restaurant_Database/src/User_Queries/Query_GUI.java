package User_Queries;

import java.awt.*;
import java.awt.EventQueue;

import javax.swing.JFrame;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;
import ca.ece.ubc.cpen221.mp5.RestaurantDBServer;
import ca.ece.ubc.cpen221.mp5.ServerClient;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;
import java.awt.Canvas;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Query_GUI {

	public JFrame frmQueryConstructor;
	public ServerClient Client;
	private JComboBox queryType;
	private JLabel lblUserString;
	private JLabel lblRestaurantList;
	private JScrollPane rstLstScroll;
	private JTextField userString;
	private JLabel lblNeighbourhoods;
	private JCheckBox chckbxDowntownBerkeley;
	private JCheckBox chckbxUCCampusArea;
	private JCheckBox chckbxTelegraphAve;
	private JComboBox category;
	private JLabel lblCategory;
	private JLabel lblPriceRange;
	private JSpinner priceLowest;
	private JSpinner priceHighest;
	private JButton sendQuery;
	private JList restList;
	private JButton Clear;
	private JTextField restFinder;
	
	private JLabel label;
	private JLabel icon1;
	private JLabel icon2;
	private JLabel icon3;
	private JLabel icon4;
	private JLabel icon5;
	private JLabel icon8;
	private JLabel icon10;
	private JLabel icon11;
	private JLabel icon12;
	private JLabel icon13;
	private JLabel icon14;
	private JLabel icon15;
	private JLabel icon16;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static DefaultListModel restListModel = new DefaultListModel();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Query_GUI window = new Query_GUI(null);
					window.frmQueryConstructor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Query_GUI(RestaurantDB database) {

		initializeComponents(database);
		initializeEventHandlers(database);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeComponents(RestaurantDB database) {
		frmQueryConstructor = new JFrame();
		frmQueryConstructor.setIconImage(Toolkit.getDefaultToolkit().getImage(Query_GUI.class.getResource("/Images/restaurant_Icon.png")));
		frmQueryConstructor.getContentPane().setBackground(new Color(51, 0, 0));
		frmQueryConstructor.setTitle("Query Constructor");
		frmQueryConstructor.setBounds(100, 100, 808, 487);
		frmQueryConstructor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 204));
		panel.setBounds(12, 12, 768, 395);
		panel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		

		queryType = new JComboBox();
		queryType.setBounds(242, 14, 312, 31);
		queryType.setModel(new DefaultComboBoxModel(new String[] { "Restaurant Search", "Random Restaurant Review",
				"Get Restaurant Details", "Add Review", "Add User", "Add Restaurant" }));
		queryType.setSelectedIndex(0);
		
		JLabel lblQueryType = new JLabel("<html><b> Query Type: </b></html>");
		lblQueryType.setBounds(84, 14, 77, 21);
		lblRestaurantList = new JLabel("<html><b> Restaurant List: </b></html>");
		lblRestaurantList.setBounds(84, 92, 95, 16);
		
		userString = new JTextField();
		userString.setBackground(UIManager.getColor("Menu.background"));
		userString.setBounds(242, 63, 312, 31);
		userString.setColumns(10);
		
		lblUserString = new JLabel("<html><b> JSON String: </b></html>");
		lblUserString.setBounds(84, 63, 76, 25);

		sendQuery = new JButton("Send Query");
		sendQuery.setBounds(242, 295, 188, 34);
		
		rstLstScroll = new JScrollPane();
		rstLstScroll.setBounds(242, 92, 312, 142);
		rstLstScroll.setBorder(new LineBorder(new Color(0, 0, 0)));

		chckbxTelegraphAve = new JCheckBox("<html><b> Telegraph Ave </b></html>");
		chckbxTelegraphAve.setBounds(537, 96, 106, 24);
		buttonGroup.add(chckbxTelegraphAve);

		chckbxUCCampusArea = new JCheckBox("<html><b> UC Campus Area </b></html>");
		chckbxUCCampusArea.setBounds(398, 96, 121, 24);
		buttonGroup.add(chckbxUCCampusArea);

		chckbxDowntownBerkeley = new JCheckBox("<html><b> Downtown Berkeley </b></html>");
		chckbxDowntownBerkeley.setBounds(242, 96, 138, 24);
		buttonGroup.add(chckbxDowntownBerkeley);

		category = new JComboBox();
		category.setBounds(242, 167, 188, 25);
		category.setModel(new DefaultComboBoxModel(new String[] {"Cafes", "Mexican", "Italian", "Persian/Iranian", "Delis", "Japanese", "Korean", "Sushi Bars", "Chinese", "Mediterranean", "Thai", "Vietnamese", "Fast Food", "Burgers ", "Pizza"}));

		lblNeighbourhoods = new JLabel("<html><b> Neighbourhood: </b></html>");
		lblNeighbourhoods.setBounds(84, 100, 89, 16);

		lblCategory = new JLabel("<html><b> Category: </b></html>");
		lblCategory.setBounds(84, 167, 76, 16);

		priceLowest = new JSpinner();
		priceLowest.setForeground(new Color(51, 204, 102));
		priceLowest.setBackground(UIManager.getColor("Menu.selectionForeground"));
		priceLowest.setBounds(242, 236, 68, 31);
		
		priceLowest.setModel(new SpinnerNumberModel(1, 1, 4, 1));

		priceHighest = new JSpinner();
		priceHighest.setBounds(363, 236, 67, 31);
		priceHighest.setModel(new SpinnerNumberModel(4, 1, 4, 1));

		lblPriceRange = new JLabel("<html><b> Price Range </b></html>");
		lblPriceRange.setBounds(84, 238, 146, 16);
		
		panel.setBorder(
				new TitledBorder(new LineBorder(new Color(184, 207, 229)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		restList = new JList();
		restList.setBackground(UIManager.getColor("Menu.background"));
		restList.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		restList.setModel(restListModel);
		InitializeRestaurantList(restListModel, database);
		frmQueryConstructor.getContentPane().setLayout(null);
		
		rstLstScroll.setViewportView(restList);
		panel.setLayout(null);
		panel.add(lblCategory);
		panel.add(lblQueryType);
		panel.add(lblUserString);
		panel.add(lblRestaurantList);
		panel.add(lblNeighbourhoods);
		panel.add(lblPriceRange);
		panel.add(userString);
		panel.add(rstLstScroll);
		
		restFinder = new JTextField();
		
		rstLstScroll.setColumnHeaderView(restFinder);
		restFinder.setColumns(10);
		panel.add(chckbxDowntownBerkeley);
		panel.add(chckbxUCCampusArea);
		panel.add(chckbxTelegraphAve);
		panel.add(sendQuery);
		panel.add(priceLowest);
		panel.add(priceHighest);
		panel.add(category);
		panel.add(queryType);
		frmQueryConstructor.getContentPane().add(panel);
		
		icon1 = new JLabel("");
		icon1.setIcon(new ImageIcon(Query_GUI.class.getResource("/Images/restaurantIcon2.png")));
		icon1.setBounds(730, 81, 38, 41);
		panel.add(icon1);
		
		icon2 = new JLabel("");
		icon2.setIcon(new ImageIcon(Query_GUI.class.getResource("/Images/restaurantIcon3.png")));
		icon2.setBounds(730, 169, 38, 35);
		panel.add(icon2);
		
		icon3 = new JLabel("");
		icon3.setIcon(new ImageIcon(Query_GUI.class.getResource("/Images/restaurantIcon4.png")));
		icon3.setBounds(730, 257, 38, 35);
		panel.add(icon3);
		
		icon4 = new JLabel("");
		icon4.setIcon(new ImageIcon(Query_GUI.class.getResource("/Images/restaurantIcon1.png")));
		icon4.setBounds(730, 14, 38, 35);
		panel.add(icon4);
		
		icon5 = new JLabel("");
		icon5.setIcon(new ImageIcon(Query_GUI.class.getResource("/Images/restaurantIcon2.png")));
		icon5.setBounds(94, 348, 38, 35);
		panel.add(icon5);
		
		icon8 = new JLabel("");
		icon8.setIcon(new ImageIcon(Query_GUI.class.getResource("/Images/restaurantIcon1.png")));
		icon8.setBounds(12, 348, 38, 35);
		panel.add(icon8);
		
		icon10 = new JLabel("");
		icon10.setIcon(new ImageIcon(Query_GUI.class.getResource("/Images/restaurantIcon3.png")));
		icon10.setBounds(180, 348, 38, 35);
		panel.add(icon10);
		
		icon11 = new JLabel("");
		icon11.setIcon(new ImageIcon(Query_GUI.class.getResource("/Images/restaurantIcon4.png")));
		icon11.setBounds(272, 348, 38, 35);
		panel.add(icon11);
		
		icon12 = new JLabel("");
		icon12.setIcon(new ImageIcon(Query_GUI.class.getResource("/Images/restaurantIcon3.png")));
		icon12.setBounds(550, 348, 38, 35);
		panel.add(icon12);
		
		icon13 = new JLabel("");
		icon13.setIcon(new ImageIcon(Query_GUI.class.getResource("/Images/restaurantIcon1.png")));
		icon13.setBounds(363, 348, 38, 35);
		panel.add(icon13);
		
		icon14 = new JLabel("");
		icon14.setIcon(new ImageIcon(Query_GUI.class.getResource("/Images/restaurantIcon2.png")));
		icon14.setBounds(462, 348, 38, 35);
		panel.add(icon14);
		
		icon15 = new JLabel("");
		icon15.setIcon(new ImageIcon(Query_GUI.class.getResource("/Images/restaurantIcon4.png")));
		icon15.setBounds(639, 348, 38, 35);
		panel.add(icon15);
		
		icon16 = new JLabel("");
		icon16.setIcon(new ImageIcon(Query_GUI.class.getResource("/Images/restaurantIcon1.png")));
		icon16.setBounds(730, 348, 38, 35);
		panel.add(icon16);
		
		Clear = new JButton("Clear");
		
		Clear.setBounds(556, 65, 98, 26);
		panel.add(Clear);
		
		label = new JLabel("");
		label.setBounds(742, 12, 38, 35);
		frmQueryConstructor.getContentPane().add(label);
		label.setIcon(new ImageIcon(Query_GUI.class.getResource("/Images/restaurantIcon1.png")));

		JMenuBar menuBar = new JMenuBar();
		frmQueryConstructor.setJMenuBar(menuBar);

		JMenu mnAbout = new JMenu("About");
		mnAbout.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnAbout);

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnHelp);
		
		lblUserString.setVisible(false);
		userString.setVisible(false);
		lblRestaurantList.setVisible(false);
		rstLstScroll.setVisible(false);
		Clear.setVisible(false);
		
		//Image Background = getImage(getCodeBase(), "data/Restaurant-Background.jpg" );
		
		/*rstLst.setToolTipText("<html><b>Select Multiple Restaurants<br> "
				+ " By Holding CTRL And Clicking<br> On Different Restaurants</b>"
				+ "</html>");*/
	}

	private void initializeEventHandlers(RestaurantDB database) {

		queryType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (queryType.getSelectedIndex() == 0) {
					
					lblUserString.setVisible(false);
					userString.setVisible(false);
					lblRestaurantList.setVisible(false);
					restList.setVisible(false);
					rstLstScroll.setVisible(false);
					lblNeighbourhoods.setVisible(true);
					chckbxDowntownBerkeley.setVisible(true);
					chckbxUCCampusArea.setVisible(true);
					chckbxTelegraphAve.setVisible(true);
					category.setVisible(true);
					lblCategory.setVisible(true);
					lblPriceRange.setVisible(true);
					priceLowest.setVisible(true);
					priceHighest.setVisible(true);
					Clear.setVisible(false);
					
					
					chckbxTelegraphAve.setBounds(537, 96, 106, 24);
					chckbxUCCampusArea.setBounds(398, 96, 121, 24);
					chckbxDowntownBerkeley.setBounds(242, 96, 138, 24);
					lblNeighbourhoods.setBounds(84, 100, 89, 16);
					category.setBounds(242, 167, 188, 25);
					lblCategory.setBounds(84, 167, 76, 16);
					priceLowest.setBounds(242, 236, 68, 31);
					priceHighest.setBounds(363, 236, 67, 31);
					lblPriceRange.setBounds(84, 238, 146, 16);
					sendQuery.setBounds(242, 300, 188, 34);
					
					

				} else if (queryType.getSelectedIndex() == 1 || queryType.getSelectedIndex() == 2) {
					
					lblUserString.setVisible(false);
					userString.setVisible(false);
					lblRestaurantList.setVisible(true);
					restList.setVisible(true);
					rstLstScroll.setVisible(true);
					lblNeighbourhoods.setVisible(false);
					chckbxDowntownBerkeley.setVisible(false);
					chckbxUCCampusArea.setVisible(false);
					chckbxTelegraphAve.setVisible(false);
					category.setVisible(false);
					lblCategory.setVisible(false);
					lblPriceRange.setVisible(false);
					priceLowest.setVisible(false);
					priceHighest.setVisible(false);
					Clear.setVisible(false);
					
					
					sendQuery.setBounds(242, 284, 188, 34);
					
					
					if(queryType.getSelectedIndex() == 1){
						restList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					}else{
						restList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
					}
				
				} else {
					
					lblUserString.setVisible(true);
					userString.setVisible(true);
					Clear.setVisible(true);
					lblRestaurantList.setVisible(false);
					restList.setVisible(false);
					rstLstScroll.setVisible(false);
					lblNeighbourhoods.setVisible(false);
					chckbxDowntownBerkeley.setVisible(false);
					chckbxUCCampusArea.setVisible(false);
					chckbxTelegraphAve.setVisible(false);
					category.setVisible(false);
					lblCategory.setVisible(false);
					lblPriceRange.setVisible(false);
					priceLowest.setVisible(false);
					priceHighest.setVisible(false);
					
					sendQuery.setBounds(242, 141, 188, 34);
				}

			}
		});
		
		sendQuery.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(queryType.getSelectedIndex() == 0){
					String query;
					if (chckbxTelegraphAve.isSelected()){
						query = "in(\"Telegraph Ave\")";
					}else if (chckbxUCCampusArea.isSelected()){
						query = "in(\"UC Campus Area\")";
					}else if (chckbxDowntownBerkeley.isSelected()){
						query = "in(\"Downtown Berkeley\")";
					}else {
						JOptionPane.showMessageDialog(null, "Please Select a Neighbourhood");
						return ;
					}
					
					query = query + " && (category(\"" + category.getSelectedItem() + "\"))";
					
					query = query + " && price(" + priceLowest.getValue() + ".." + priceHighest.getValue() + ")";
					
					try{
						Client.SendQuery(query);
						
						String reply = Client.getReply();
						String readableOutput = reply.replaceAll("#", "\n");
						
						Server_Response_GUI response = new Server_Response_GUI(readableOutput);
						response.setLabel("Matches:");
						response.modifyHelpMessage("<html><b>Find Out More About Restaurants<br>"
								+ "Listed Here By Using The <br>\"Get Restauraunt Details\" Query </b>  </html> ");
						response.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						response.setVisible(true);
	
					} catch (IOException e){
						e.printStackTrace();
					}
					
				}else if (queryType.getSelectedIndex() == 1){
					
					if (restList.getSelectedIndex() == -1){
						
						JOptionPane.showMessageDialog(null, "Please Select a Restaurant");
						
					}else{
						
						String restaurantName = (String) restList.getSelectedValue();
						String query = "randomReview(\"" + restaurantName + "\")";
						
						try{
							Client.SendQuery(query);
							String reply = Client.getReply();
							
							String readableOutput = reply.replaceAll("#", "\n");
							
							Server_Response_GUI response = new Server_Response_GUI(readableOutput);
							response.setLabel("Review:");
							response.modifyHelpMessage("");
							response.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							response.setVisible(true);

						} catch(Exception e){
							e.printStackTrace();
						}
						
						
					}
				
				
				}else if(queryType.getSelectedIndex() == 2){
					
					if (restList.getSelectedIndex() == -1){
						
						JOptionPane.showMessageDialog(null, "Please Select a Restaurant");
						
					}else{
						String readableOutput = "";
						for (Object o : restList.getSelectedValues()){
							
						
							String query;
							String businessId = database.nameToBusinessId((String) o) ;
							query = "getRestaurant(\"" + businessId + "\")";
							
							try{
								Client.SendQuery(query);
								String reply = Client.getReply();
								readableOutput += RestaurantDBServer.getRestaurantOutput(reply);
								
								
							} catch(Exception e){
								e.printStackTrace();
							}
						}
						
						try {
							Server_Response_GUI response = new Server_Response_GUI(readableOutput);
							response.setLabel("Restaurant Details:");
							response.modifyHelpMessage("");
							response.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							response.setVisible(true);
						} catch (Exception e){
							e.printStackTrace();
						}
						
					}
				}else{
					String query = null;
					try {
						if(queryType.getSelectedIndex() == 3){
							query = "addReview(\"" + userString.getText() + "\")";
						}else if(queryType.getSelectedIndex() == 4){
							query = "addUser(\"" + userString.getText() + "\")";
						}else if(queryType.getSelectedIndex() == 5){
							query = "addRestaurant(\"" + userString.getText() + "\")";
						}
						Client.SendQuery(query);
						String reply = Client.getReply();
						String readableString = reply.replace("{", "").replace("\"", "").replace("}", "");
						
						Server_Response_GUI response = new Server_Response_GUI(readableString);
						
						response.setLabel("Results:");
						response.modifyHelpMessage("");
						response.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						response.setVisible(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		
		priceLowest.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				priceHighest.setModel(new SpinnerNumberModel((int) priceHighest.getValue(),(int) priceLowest.getValue(), 4, 1));
				
			}
		});
		
		priceHighest.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				priceLowest.setModel(new SpinnerNumberModel((int) priceLowest.getValue(), 1, (int) priceHighest.getValue(), 1));
			}
		});
		
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userString.setText("");
			}
		});
		
		
		restFinder.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				
				int lowestIndex = Integer.MAX_VALUE;
				for(int i = 0; i < restListModel.size(); i++){
					
					if(((String) restListModel.getElementAt(i)).toLowerCase().startsWith(restFinder.getText().toLowerCase())){
						restList.setSelectedValue(restListModel.getElementAt(i), true);
						
						if(restList.getSelectedIndex() < lowestIndex){	
							lowestIndex = restList.getSelectedIndex();
						}else {
							restList.setSelectedIndex(lowestIndex);
						
						}
						
					}
					
				}
			
			}
		});
	
	}
	
	public void InitializeRestaurantList(DefaultListModel restListModel, RestaurantDB database){
		
		for(Restaurant r: database.getRestaurants()){
			
			restListModel.addElement(r.getName());
			
		}
	
	}
	
	public ListModel getListModel(){
		
		return restListModel;
	}
	
	public static void updateListModel(String element){
		
		restListModel.addElement(element);
	}
}
