package controller;
import view.*;

public class Controller {
	View view;
	public KeyCreater theKeyCreater;
	public KeyRing theKeyRing;
	public void addView(View view) {
		this.view=view;
	}
	public Controller() {
		theKeyCreater = new KeyCreater();
		theKeyRing = new KeyRing();
	}
	
	

}
