package com.mit.barcodescanner;

import java.util.ArrayList;
import java.util.List;

import jebl.util.ProgressListener;

import com.biomatters.geneious.publicapi.components.Dialogs;
import com.biomatters.geneious.publicapi.components.GDialog;
import com.biomatters.geneious.publicapi.components.Dialogs.DialogOptions;
import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
import com.biomatters.geneious.publicapi.databaseservice.Query;
import com.biomatters.geneious.publicapi.databaseservice.WritableDatabaseService;
import com.biomatters.geneious.publicapi.documents.AnnotatedPluginDocument;
import com.biomatters.geneious.publicapi.plugin.DocumentOperation;
import com.biomatters.geneious.publicapi.plugin.DocumentOperationException;
import com.biomatters.geneious.publicapi.plugin.DocumentSelectionSignature;
import com.biomatters.geneious.publicapi.plugin.GeneiousActionOptions;
import com.biomatters.geneious.publicapi.plugin.Options;
import com.biomatters.geneious.publicapi.plugin.PluginUtilities;
import com.biomatters.geneious.publicapi.plugin.SequenceSelection;

public class BarcodeFinder extends DocumentOperation {
	public BarcodeFinder() {
		
	}
	
	public static List<AnnotatedPluginDocument> findDocumentsWithBarcode(String barcode) throws DatabaseServiceException {
		List<String> barcodes = new ArrayList<String>();
		barcodes.add(barcode);
		return findDocumentsWithBarcodes(barcodes);
	}
	
	public static List<AnnotatedPluginDocument> findDocumentsWithBarcodes(List<String> barcodes) throws DatabaseServiceException {
		 WritableDatabaseService database= (WritableDatabaseService) PluginUtilities.getGeneiousService("LocalDocuments");
		 List<AnnotatedPluginDocument> documentsInEntireDatabase = database.retrieve(Query.Factory.createQuery(""), ProgressListener.EMPTY);
		 //List<AnnotatedPluginDocument> documentsInRootFolderOnly = database.retrieve(Query.Factory.createBrowseQuery(), ProgressListener.EMPTY);
		 List<AnnotatedPluginDocument> resultList = new ArrayList<AnnotatedPluginDocument>();
		 for (AnnotatedPluginDocument d: documentsInEntireDatabase) {
			 System.out.println(d.getFieldValue("barcode.code"));
			 System.out.println(d.getFieldValue("barcode"));
			if (barcodes.contains(d.getFieldValue("barcode.code")))
				resultList.add(d);
		 }
		 return resultList;
	}

	@Override
	public GeneiousActionOptions getActionOptions() {
		return new GeneiousActionOptions("Find Barcodes").setInMainToolbar(true);
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentSelectionSignature[] getSelectionSignatures() {
		return new DocumentSelectionSignature[0];
	}
	
	@Override
	public List<AnnotatedPluginDocument> performOperation(
			AnnotatedPluginDocument[] annotatedDocuments,
			ProgressListener progressListener, Options options)
			throws DocumentOperationException {
		List<String> barcodes = new ArrayList<String>();
		Dialogs.showDialog(new DialogOptions(new Dialogs.DialogAction[] {
				new Dialogs.DialogAction("Search") {

					@Override
					public boolean performed(GDialog dialog) {
						// TODO Auto-generated method stub
						return true;
					}
					
				}
				},"Search"), new BarcodeFinderGUI(barcodes));
		try {
			List<AnnotatedPluginDocument> found = findDocumentsWithBarcodes(barcodes);
			System.out.println("Found " + found.size());
			return found;
		} catch (DatabaseServiceException e) {
			e.printStackTrace();
			return new ArrayList<AnnotatedPluginDocument>();
		}
	}
}
