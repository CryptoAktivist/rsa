package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.Key;

public class AddPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	View view;
	
	JScrollPane scrollPane;
	JPanel theKeysPanel;
	JButton deleteButton;
	JButton addKeyButton;
	LinkedList<MyJReadioButton> radioButtons = new LinkedList<MyJReadioButton>();
	LinkedList<JLabel> lisOfKeyLabels = new LinkedList<JLabel>();
	//LinkedList<JLabel> labelsE = new LinkedList<JLabel>();
	//LinkedList<JLabel> labelsD = new LinkedList<JLabel>();
	JDialog dialogJdialog;
	JTextField dialogNField;
	JTextField dialogEField;
	JButton dialogOkButton;
	public AddPanel(View view) {
		this.view=view;
		this.setLayout(new GridBagLayout() );
		theKeysPanel = new JPanel();
		scrollPane = new JScrollPane(theKeysPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		scrollPane.setMinimumSize(new Dimension(400, 200));
		
		
		theKeysPanel.setLayout(new GridBagLayout() );
		
		deleteButton = new JButton("Delete key");
		deleteButton.addActionListener(this);
		addKeyButton = new JButton("Add key");
		addKeyButton.addActionListener(this);
		
		//=== Add All ===
		GridBagConstraints c = new GridBagConstraints();
	
		c.gridx=0;
		c.gridy=0;
		this.add(scrollPane, c);
		c.gridx=0;
		c.gridy=1;
		c.anchor = GridBagConstraints.WEST;
		this.add(addKeyButton, c);
		c.gridx=0;
		c.gridy=1;
		c.anchor = GridBagConstraints.EAST;
		this.add(deleteButton, c);		
	
	}
	public void setActive() {
		theKeysPanel.removeAll();
		radioButtons = new LinkedList<MyJReadioButton>();
		showAllKeys();
		
		scrollPane.revalidate();
		scrollPane.repaint();
	}

	public void showAllKeys() {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0,10,0,10);  //top padding
		int anz = view.cont.theKeyRing.howManyKeys();
		for(int i=0;i<anz;i++) {
			Key key = view.cont.theKeyRing.getKey(i);
			if(key==null) {
				return;
			}
			c.gridx=0;
			c.gridy=i;
			MyJReadioButton jrb= new MyJReadioButton();
			jrb.setMyKeyIndex(i);
			jrb.addActionListener(this);
			jrb.setMyKey(key);;
			radioButtons.add(jrb);
			theKeysPanel.add(jrb,c );
			
		
			JTextField jrle = new JTextField( "e:"+key.getE());
			jrle.setBorder(null);
			jrle.setForeground(UIManager.getColor("Label.foreground"));
			jrle.setFont(UIManager.getFont("Label.font"));
			jrle.setOpaque(false);
			c.ipadx=5;
			c.gridx=1;
			c.gridy=i;
			theKeysPanel.add(jrle, c);
			
			JTextField jrln = new JTextField("N:"+key.getN());
			jrln.setBorder(null);
			jrln.setForeground(UIManager.getColor("Label.foreground"));
			jrln.setFont(UIManager.getFont("Label.font"));
			jrln.setOpaque(false);
			c.gridx=2;
			theKeysPanel.add(jrln, c);
			
			c.ipadx=0;
			
			if( 0 != key.getD() ) {
				JLabel jrld = new JLabel( "d:"+key.getD());
				c.gridx=3;
				//lisOfKeyLabels.add(jrle);
				theKeysPanel.add(jrld , c);
				jrld.setForeground(new Color(139,0,0) );
				jrle.setForeground(new Color(139,0,0) );
				jrln.setForeground(new Color(139,0,0));
			}else {
				jrle.setForeground(new Color(0,128,0));
				jrln.setForeground(new Color(0,128,0));
				
			}
			
			//scrollPane size other wise it doesnst scroll
			//scrollPane.revalidate();
			//scrollPane.setPreferredSize(scrollPane.getPreferredSize());
		}
	}
	public void unmarkAllJbkExept(MyJReadioButton notTuUnmark) {
		for (JRadioButton jrb : radioButtons ) {
				if(notTuUnmark==null || notTuUnmark!=jrb) {
					jrb.setSelected(false);
				}
		}	
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==this.deleteButton) {
			for (MyJReadioButton jrb : radioButtons ) {
				if( jrb.isSelected() ){
					System.out.println("Delete this key: "+ jrb.getMyKeyIndex() );
					view.cont.theKeyRing.removeKey(jrb.getMyKey());
					//radioButtons.remove(jrb);
					//theKeysPanel.remove(jrb);
					break;
				}
			}
			this.setActive();
		}
		if(ae.getSource()==this.addKeyButton) {
			dialogJdialog = new JDialog();
			dialogJdialog.setTitle("Add a key");
			dialogJdialog.setSize(new Dimension(500,200));
			dialogJdialog.setMinimumSize(new Dimension(450,200));
			dialogJdialog.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();

			
			c.gridx=0;
			c.gridy=0;
			dialogJdialog.add(new JLabel("e:"),c);
			c.gridx=1;
			c.gridy=0;
			this.dialogEField = new JTextField(30);
			dialogJdialog.add(this.dialogEField ,c);
			
			c.gridx=0;
			c.gridy=1;
			dialogJdialog.add(new JLabel("N:"),c);
			c.gridx=1;
			c.gridy=1;
			this.dialogNField = new JTextField(30);
			dialogJdialog.add(this.dialogNField,c);	
			
			c.gridx=0;
			c.gridy=2;
			dialogOkButton = new JButton("ok");
			dialogOkButton.addActionListener(this);
			dialogJdialog.add(dialogOkButton,c);
			
			dialogJdialog.setModal(true);
			dialogJdialog.setVisible(true);
		}
		for (MyJReadioButton jrb : radioButtons ) {
			if(ae.getSource()==jrb) {
				//System.out.println("juhui"+ myJrB.getMyKeyIndex() );
				unmarkAllJbkExept(jrb);
			}
		}
		if(ae.getSource()==this.dialogOkButton) {
			long e=0;
			long n=0;
			try{
				e = Long.parseLong( dialogEField.getText());
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(view,
						dialogEField.getText() +" is not a Number! Plz put in a number.",
					    "Input error",
					    JOptionPane.ERROR_MESSAGE);
				return;
			}
			try{
				n = Long.parseLong( dialogNField.getText());
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(view,
						dialogNField.getText()+" is not a Number! Plz put in a number.",
					    "Input error",
					    JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			n = Long.parseLong( dialogNField.getText());
			if(e!=0&&n!=0) {
				System.out.println("Add e:" + e +" and n:" +n);
				view.cont.theKeyRing.addKey(new Key(n,e));
				dialogJdialog.setVisible(false);
				this.setActive();	
			}
		}	
	}
}
