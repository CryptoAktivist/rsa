package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Key;
import controller.KeyCreater;

public class CreatePanel extends JPanel implements MouseListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel primes[] = new JLabel [1000] ;
	JPanel primePanel = new JPanel();
	View view;
	
	long primeA=0;
	long primeB=0;
	
	int primeAindex=0;
	int primeBindex=0;
	
	boolean lastSetB=true;
	JLabel labelPrimeA;
	JLabel labelPrimeB;
	
	JLabel nLabel; 
	JLabel eLabel;
	JLabel phiOfnLabel;
	JLabel dLabel;
	
	JButton nextPrimesKey; 
	JTextField firstPrime; 
	
	JButton addKeyButton; 
	
	Key key = new Key();
	
	public CreatePanel(View view) {
		this.view=view;
		this.setLayout(new GridBagLayout() );
		
		firstPrime = new JTextField("2");
		nextPrimesKey = new JButton("next");
		nextPrimesKey.addActionListener(this);
		
		
		addKeyButton = new JButton("Add This Key");
		addKeyButton.setFont(new Font("Arial",1,18));
		addKeyButton.addActionListener(this);
		
		//create calc Fields
		nLabel = new JLabel("n:");
		nLabel.setFont(new Font("Arial",1,20));
		eLabel = new JLabel("e:");
		eLabel.setFont(new Font("Arial",1,20));
		phiOfnLabel= new JLabel("phiofn:");
		phiOfnLabel.setFont(new Font("Arial",1,20));
		dLabel= new JLabel("d:");
		dLabel.setFont(new Font("Arial",1,20));
		
		//create Prime Number Fields
		labelPrimeA  = new JLabel("");
		labelPrimeA.setFont(new Font("Arial",1,20));
		labelPrimeB  = new JLabel("");
		labelPrimeB.setFont(new Font("Arial",1,20));
		
	
		//Create prime Panel
		primePanel.setLayout(new GridLayout(40, 25));
		long prime=2;
		try {
			prime= Long.parseLong( firstPrime.getText() );
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view,
					firstPrime.getText() +" is not a Number! Please put in a number.",
				    "Input error",
				    JOptionPane.ERROR_MESSAGE);
		}
		for(int i=0;i<1000;i++) {
			prime = KeyCreater.nextPrimes(prime);
			primes[i]=new JLabel(""+prime);
			primes[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			primes[i].setHorizontalAlignment(JLabel.CENTER);
			primes[i].addMouseListener(this);
			primes[i].setOpaque(true);
			primePanel.add(primes[i]);
		}
		//===============  add all ===============
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth= 1;
		c.anchor= GridBagConstraints.WEST;
		c.ipadx=60;
		//c.fill=GridBagConstraints.HORIZONTAL;
		this.add(firstPrime, c);
		c.ipadx=0;
		c.fill=GridBagConstraints.NONE;
		c.anchor= GridBagConstraints.EAST;
		c.gridy=0;
		c.gridx=0;
		this.add(nextPrimesKey, c);
		
		c.anchor= GridBagConstraints.WEST;
		c.gridy=1;
		this.add(labelPrimeA, c);
		c.gridy=1;
		c.anchor= GridBagConstraints.EAST;
		this.add(labelPrimeB, c );
		
		c.gridy=2;
		c.anchor= GridBagConstraints.WEST;
		this.add(primePanel,c);
		
		c.gridy=3;
		c.anchor= GridBagConstraints.WEST;
		this.add(nLabel,c);
		
		c.gridy=3;
		c.anchor= GridBagConstraints.EAST;
		this.add(eLabel,c);
		
		c.anchor= GridBagConstraints.WEST;
		c.gridy=4;
		this.add(phiOfnLabel,c);
		
		c.anchor= GridBagConstraints.EAST;
		c.gridy=4;
		this.add(dLabel,c);
		
		c.anchor= GridBagConstraints.CENTER;
		c.gridy=5;		
		this.add(this.addKeyButton,c);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i=0;i<1000;i++) {
			if(e.getSource()==primes[i]) {
				primes[i].setBackground(new Color(210, 210, 255));
				if(lastSetB==true) {
					primeA= Long.parseLong(primes[i].getText());
					this.labelPrimeA.setText(""+primeA);
					primes[this.primeAindex].setOpaque(false);
					this.primeAindex=i;
					lastSetB=false;
				}else {
					primeB= Long.parseLong(primes[i].getText());
					this.labelPrimeB.setText(""+primeB);
					primes[this.primeBindex].setOpaque(false);;
					this.primeBindex=i;
					lastSetB=true;
				}
				break;
			}
		}
		calcStuff();
		
	}
	public void calcStuff() {
		if(this.primeA==0|| this.primeB==0){
			return;
		}
		key = new Key();
		key.setPrim1(this.primeA);
		key.setPrim2(this.primeB);
		key.calcN();
		key.calcE();
		key.calcPhiOfN();
		key.calcD();
		
		if(key.selfTest()==false) {
			return;
		}
		
		this.nLabel.setText("n:"+ key.getN());
		this.eLabel.setText("e:"+  key.getE());
		this.phiOfnLabel.setText("phi of n:" + key.calcPhiOfN() ) ;
		this.dLabel.setText("d:"+ key.getD() );
		
		this.revalidate();
		this.repaint();
		
	}
	public void doprimes() {
		long prime=0;
		try {
			prime= Long.parseLong( firstPrime.getText() );
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view,
					firstPrime.getText() +" is not a Number! Please put in a number.",
				    "Input error",
				    JOptionPane.ERROR_MESSAGE);
		}
		for(int i=0;i<1000;i++) {
			prime = KeyCreater.nextPrimes(prime);
			primes[i].setText(""+prime);
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == this.addKeyButton) {
			if(this.primeA==0|| this.primeB==0){
				return;
			}
			if(key.selfTest()==false) {
				return;
			}
			this.view.cont.theKeyRing.addKey(key);
		}else if(ae.getSource() == this.nextPrimesKey) {
			firstPrime.setText(primes[999].getText());
			primes[this.primeAindex].setOpaque(false);
			primes[this.primeBindex].setOpaque(false);
			this.nLabel.setText("n:");
			this.eLabel.setText("e:");
			this.phiOfnLabel.setText("phi of n:") ;
			this.dLabel.setText("d:" );
			doprimes();
		}
		
	}

}
