package com.mit.barcodescanner;

import java.util.ArrayList;
import java.util.List;

import jebl.util.ProgressListener;

import com.biomatters.geneious.publicapi.components.Dialogs;
import com.biomatters.geneious.publicapi.components.Dialogs.DialogOptions;
import com.biomatters.geneious.publicapi.components.GDialog;
import com.biomatters.geneious.publicapi.documents.AnnotatedPluginDocument;
import com.biomatters.geneious.publicapi.documents.DocumentField;
import com.biomatters.geneious.publicapi.documents.DocumentFieldAndValue;
import com.biomatters.geneious.publicapi.documents.sequence.NucleotideSequenceDocument;
import com.biomatters.geneious.publicapi.plugin.DocumentOperationException;
import com.biomatters.geneious.publicapi.plugin.DocumentSelectionSignature;
import com.biomatters.geneious.publicapi.plugin.GeneiousActionOptions;
import com.biomatters.geneious.publicapi.plugin.Options;
import com.biomatters.geneious.publicapi.plugin.SequenceAnnotationGenerator;
import com.sun.java.swing.plaf.windows.WindowsBorders;

public class BarcodeScannerAnnotationGenerator extends
		SequenceAnnotationGenerator {

	@Override
	public GeneiousActionOptions getActionOptions() {
		//Put menu item in sequence menu
		return new GeneiousActionOptions("Scan barcode for this sequence").setMainMenuLocation(GeneiousActionOptions.MainMenu.AnnotateAndPredict);
	}	

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentSelectionSignature[] getSelectionSignatures() {
		return new DocumentSelectionSignature[] {
				new DocumentSelectionSignature(NucleotideSequenceDocument.class, 1, Integer.MAX_VALUE)
		};
	}
	
	private DocumentField makeBarcodeField() {
		return DocumentField.createStringField("barcode", "Document's barcode", "barcode.code");
	}
	
	@Override
	public List<SequenceAnnotationGenerator.AnnotationGeneratorResult> generate(AnnotatedPluginDocument[] documents,
			SequenceAnnotationGenerator.SelectionRange selectionRange,
			ProgressListener progresslistener,
			Options options) throws DocumentOperationException {
		List<SequenceAnnotationGenerator.AnnotationGeneratorResult> resultsList = new ArrayList<SequenceAnnotationGenerator.AnnotationGeneratorResult>();
		
		DocumentField barcodeField = makeBarcodeField();
		Dialogs.showDialog(new DialogOptions(new Dialogs.DialogAction[] {
				new Dialogs.DialogAction("booooo") {

					@Override
					public boolean performed(GDialog dialog) {
						// TODO Auto-generated method stub
						return true;
					}
					
				}
				},"boo"), new BarcodeScannerGui(documents, resultsList));
		
		return resultsList;
	}
	
	public Options getOptions(AnnotatedPluginDocument[] documents,
			SequenceAnnotationGenerator.SelectionRange selectRange) throws DocumentOperationException {
		return new BarcodeScannerOptions();
	}

}
