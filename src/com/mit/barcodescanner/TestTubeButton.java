package com.mit.barcodescanner;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class TestTubeButton extends JButton {
	public final String location;
	private boolean hasBarcode = false;
	private String barcode = "";
	
	public TestTubeButton(String location) {
		super();
		this.location = location;
	}

	public TestTubeButton(String location, String text) {
		super(text);
		this.location = location;
	}

	public boolean hasBarcode() {
		return hasBarcode;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.hasBarcode = true;
		this.barcode = barcode;
	}
	
	public void clearBarcode() {
		this.hasBarcode = false;
		this.barcode = "";
	}
	
}
