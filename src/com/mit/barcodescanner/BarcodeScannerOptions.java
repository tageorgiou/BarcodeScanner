package com.mit.barcodescanner;

import com.biomatters.geneious.publicapi.plugin.Options;

public class BarcodeScannerOptions extends Options {
	
	public BarcodeScannerOptions() {
		codeLocationOptions();
	}
	
	private StringOption scannerAddress;
	
	private void codeLocationOptions() {
		scannerAddress = addStringOption("host", "Scanner address", "http://18.125.5.183:2233/");
		scannerAddress.setRestoreDefaultApplies(false);
	}
	
	public String getScannerAddress() {
		return scannerAddress.getValue();
	}

}
