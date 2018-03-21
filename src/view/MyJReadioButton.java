package view;

import javax.swing.JRadioButton;

import controller.Key;

public class MyJReadioButton extends JRadioButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int myKeyIndex;
	private Key myKey;
	
	public int getMyKeyIndex() {
		return myKeyIndex;
	}

	public void setMyKeyIndex(int myKeyIndex) {
		this.myKeyIndex = myKeyIndex;
	}

	public Key getMyKey() {
		return myKey;
	}

	public void setMyKey(Key myKey) {
		this.myKey = myKey;
	}
	

}
