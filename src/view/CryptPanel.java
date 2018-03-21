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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.Key;

public class CryptPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	View view;
	JTextField cypherTextField;
	JTextField messageTextField;
	JTextField answer;
	JButton cryptButton;
	JButton decryptButton;
	LinkedList<MyJReadioButton> radioButtons = new LinkedList<MyJReadioButton>();
	LinkedList<JLabel> lisOfKeyLabels = new LinkedList<JLabel>();
	JScrollPane scrollPane;
	JPanel theKeysPanel;
	
	public CryptPanel(View view) {
		this.view=view;
		this.setLayout(new GridBagLayout() );
		cypherTextField = new JTextField(50);
		messageTextField = new JTextField(50);
		answer = new JTextField(50);
		
		cypherTextField.setText("89");
		messageTextField.setText("27");
		
		GridBagConstraints c = new GridBagConstraints();
		
		cryptButton=new JButton("Crypt");
		cryptButton.addActionListener(this);
		decryptButton=new JButton("Decrypt");
		decryptButton.addActionListener(this);
		
		theKeysPanel = new JPanel();
		scrollPane = new JScrollPane(theKeysPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(300, 100));
		theKeysPanel.setLayout(new GridBagLayout() );
		
		/// === Add all ===
		c.gridx=0;
		c.gridy=0;
		this.add(scrollPane, c);
		
		c.gridx=0;
		c.gridy=1;
		this.add(messageTextField, c);
		
		c.anchor= GridBagConstraints.WEST;
		c.gridx=1;
		c.gridy=1;
		this.add(cryptButton, c);
		
		c.gridx=0;
		c.gridy=2;
		this.add(cypherTextField, c);
		
		
		c.gridx=1;
		c.gridy=2;
		this.add(decryptButton, c);
	
		
		c.gridx=0;
		c.gridy=3;
		this.add(answer, c);
	}
	public void setActive() {
		theKeysPanel.removeAll();
		radioButtons = new LinkedList<MyJReadioButton>();
		showAllKeys();
		this.revalidate();

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
			
			c.gridx=1;
			c.gridy=i;
			JLabel jrle = new JLabel( "e:"+key.getE());
			//lisOfKeyLabels.add(jrle);
			theKeysPanel.add(jrle, c);
			
			JLabel jrln = new JLabel("N:"+key.getN());
			c.gridx=2;
			//lisOfKeyLabels.add(jrln);
			theKeysPanel.add(jrln, c);
			if( 0 != key.getD() ) {
				JLabel jrld = new JLabel( "d:"+key.getD());
				c.gridx=3;
				//lisOfKeyLabels.add(jrle);
				theKeysPanel.add(jrld , c);
				jrle.setForeground(new Color(139,0,0) );
				jrln.setForeground(new Color(139,0,0));
				jrld.setForeground(new Color(139,0,0));
			}else {
				jrle.setForeground(new Color(0,128,0));
				jrln.setForeground(new Color(0,128,0));
				
			}
			
			//scrollPane size other wise it doesnst scroll
			//scrollPane.revalidate();
			//scrollPane.setPreferredSize(scrollPane.getPreferredSize());
		}
	}
	
	public void docryptStuff(Key k1) {
		long answer=0;
		try {
			answer = this.view.cont.theKeyRing.crypt(Long.parseLong(messageTextField.getText()), k1);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(view,
					messageTextField.getText() +" is not a Number! Please put in a number.",
				    "Input error",
				    JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(answer>k1.getN()) {
			JOptionPane.showMessageDialog(view,
					messageTextField.getText() +" is bigger than N of the chosen key.",
				    "Result incorrect",
				    JOptionPane.ERROR_MESSAGE);
		}
		this.answer.setText(""+answer);
	}
	public void doDecryptStuff(Key k1) {
		
		long answer=0;
		try {
			answer = this.view.cont.theKeyRing.decrypt(Long.parseLong(cypherTextField.getText()), k1);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(view,
					cypherTextField.getText() +" is not a Number! Please put in a number.",
				    "Input error",
				    JOptionPane.ERROR_MESSAGE);
				return;
		}
		if(Long.parseLong(cypherTextField.getText())>k1.getN()) {
			JOptionPane.showMessageDialog(view,
					cypherTextField.getText() +" is bigger than N of the chosen key.",
				    "Result incorrect",
				    JOptionPane.ERROR_MESSAGE);
				return;
		}
		
		this.answer.setText(""+answer);
		
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
		for (MyJReadioButton jrb : radioButtons ) {
			if(ae.getSource()==jrb) {
				//System.out.println("juhui"+ myJrB.getMyKeyIndex() );
				unmarkAllJbkExept(jrb);
				return;
			}
		}
		Key k1 = getSelectedKey();
		if(k1==null) {
			JOptionPane.showMessageDialog(view,
				    "Bitte einen Schlüssel wählen.");
			return;	
		}
		
		if(ae.getSource()==cryptButton) {
			docryptStuff(k1);
		}else if(ae.getSource()==decryptButton) {
			if(k1.istItprivateKey()==false) {
				JOptionPane.showMessageDialog(view,
					    "You cna only with a private Key decrypt a message",
					    "Public key chosen",
					    JOptionPane.WARNING_MESSAGE);
				
			}else {
				doDecryptStuff(k1);
			}
			
		}
		
		
	}
	
	public Key getSelectedKey() {
		for(MyJReadioButton myJrb: radioButtons) {
			if(myJrb.isSelected()) {
				return myJrb.getMyKey();
			}
		}
		return null;
	}
		

}
