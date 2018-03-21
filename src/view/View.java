package view;


import java.awt.*;
import java.awt.event.*;

import java.net.URL;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.*;

public class View extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Controller cont;	
	public void addController(Controller cont) {
		this.cont=cont;
	}
	JFrame frame;
	JPanel groundPanel;
	JPanel topPanel;
	CryptPanel cryptPanel;
	CreatePanel createPanel;
	AddPanel addPanel;
	JButton crypt;
	JButton makeKey;
	JButton addKey;
	
	JLabel imgLabel;
	
	public View(Controller cont) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		this.cont=cont;
		frame = new JFrame("RSA for cool bros");
		
		initalisePanels();
		initaliseButtons();
		initaliseActionListener();
		
		frame.add(groundPanel);

		
		//// Schluss sachen...
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		frame.setVisible(true);
		frame.setSize(new Dimension (1000,1200 ));
		frame.setMinimumSize(new Dimension (600,330 ));
	}
	


	private void initalisePanels(){
		groundPanel = new JPanel();
		groundPanel.setLayout(new BorderLayout() );
		
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(0, 3) );
		

		cryptPanel = new CryptPanel(this);
		
		createPanel = new CreatePanel(this);
		
		
		addPanel = new AddPanel(this);
		
	
		//cryptPanel.setBackground(new Color(100, 120, 0));
		cryptPanel.setVisible(false);
		
		//createPanel.setBackground(new Color(80, 100, 150));
		createPanel.setVisible(false);
		
		//addPanel.setBackground(new Color(200, 50, 180));
		addPanel.setVisible(false);
		
		initaliseCrypPanel();
		initaliseCreatePanel();
		initaliseAddPanel();
		
		//adding
		
		//cryptPanel.setPreferredSize(new Dimension(300, 300));
		groundPanel.add(topPanel, BorderLayout.PAGE_START);
		groundPanel.add(cryptPanel,BorderLayout.CENTER);
		groundPanel.add(addPanel,BorderLayout.CENTER);
		groundPanel.add(createPanel,BorderLayout.CENTER);
		
	}
	
	private void initaliseButtons(){
		crypt = new JButton();
		makeKey = new JButton();
		addKey = new JButton();
		
		try {
			URL cryptUrl = Main.class.getResource("/crypt.png");
			URL makeUrl = Main.class.getResource("/makeKey.png");
			URL addUrl = Main.class.getResource("/addKey.png");
			
			crypt.setIcon(new ImageIcon(cryptUrl)); 
			makeKey.setIcon(new ImageIcon(makeUrl)); 
			addKey.setIcon(new ImageIcon(addUrl)); 
			
		} catch (Exception  e) {
			e.printStackTrace();
			crypt = new JButton("crypt");
			makeKey = new JButton("makeKey");
			addKey = new JButton("addKey");
		}
		try {
			URL startUrl = Main.class.getResource("/start.png");
			imgLabel = new JLabel(new ImageIcon(startUrl));
		} catch (Exception  e) {
			imgLabel= new JLabel();
		}

		
		crypt.setToolTipText("Crypt and decrypt");
		//crypt.setBorderPainted(false);  
		crypt.setFocusPainted(false); 
	
		
		
		makeKey.setToolTipText("Generate a private key");
		//makeKey.setBorderPainted(false);  
		makeKey.setFocusPainted(false); 
		
		
		addKey.setToolTipText("Add a public key");
		//addKey.setBorderPainted(false);  
		addKey.setFocusPainted(false); 
		

		topPanel.add(crypt);
		topPanel.add(makeKey);
		topPanel.add(addKey);
		if(imgLabel!=null) {
			groundPanel.add(imgLabel);
		}
		
		
		
	}
	
	private void initaliseActionListener() {
		crypt.addActionListener(this);
		makeKey.addActionListener(this);
		addKey.addActionListener(this);
	}
	private void initaliseCrypPanel() {
		
	}
	private void initaliseAddPanel() {
		
	}
	private void initaliseCreatePanel() {
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == this.crypt){
			cryptPanel.setVisible(true);
			cryptPanel.setActive();
			addPanel.setVisible(false);
			createPanel.setVisible(false);
			imgLabel.setVisible(false);
			groundPanel.add(cryptPanel,BorderLayout.CENTER);

		}else if(ae.getSource() == this.makeKey ) {
			cryptPanel.setVisible(false);
			addPanel.setVisible(false);
			imgLabel.setVisible(false);
			createPanel.setVisible(true);
			groundPanel.add(createPanel,BorderLayout.CENTER);
		}else if(ae.getSource() == this.addKey ){
			cryptPanel.setVisible(false);
			addPanel.setVisible(true);
			createPanel.setVisible(false);
			imgLabel.setVisible(false);
			addPanel.setActive();
			groundPanel.add(addPanel,BorderLayout.CENTER);
		}else {
			System.out.println("No Action for this event");
		}
	}

}
