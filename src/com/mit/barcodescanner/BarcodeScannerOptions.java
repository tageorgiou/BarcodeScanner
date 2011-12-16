package com.mit.barcodescanner;

import com.biomatters.geneious.publicapi.plugin.Options;

public class BarcodeScannerOptions extends Options {
	
	public BarcodeScannerOptions() {
		codeLocationOptions();
	}
	
	private FileSelectionOption codeLocation;
	
	private void codeLocationOptions() {
		codeLocation = addFileSelectionOption("EXE", "asd", "?");
		codeLocation.setRestoreDefaultApplies(false);
	}

}
