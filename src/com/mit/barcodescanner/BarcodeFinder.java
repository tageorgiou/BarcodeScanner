package com.mit.barcodescanner;

import java.util.ArrayList;
import java.util.List;

import jebl.util.ProgressListener;

import com.biomatters.geneious.publicapi.databaseservice.DatabaseServiceException;
import com.biomatters.geneious.publicapi.databaseservice.Query;
import com.biomatters.geneious.publicapi.databaseservice.WritableDatabaseService;
import com.biomatters.geneious.publicapi.documents.AnnotatedPluginDocument;
import com.biomatters.geneious.publicapi.plugin.PluginUtilities;

public class BarcodeFinder {
	public BarcodeFinder() {
		
	}
	
	public List<AnnotatedPluginDocument> findDocumentsWithBarcodes(List<String> barcodes) throws DatabaseServiceException {
		 WritableDatabaseService database= (WritableDatabaseService) PluginUtilities.getGeneiousService("LocalDocuments");
		 List<AnnotatedPluginDocument> documentsInEntireDatabase = database.retrieve(Query.Factory.createQuery(""), ProgressListener.EMPTY);
		 //List<AnnotatedPluginDocument> documentsInRootFolderOnly = database.retrieve(Query.Factory.createBrowseQuery(), ProgressListener.EMPTY);
		 List<AnnotatedPluginDocument> resultList = new ArrayList<AnnotatedPluginDocument>();
		 for (AnnotatedPluginDocument d: documentsInEntireDatabase) {
			if (barcodes.contains(d.getFieldValue("barcode.code")))
				resultList.add(d);
		 }
		 return resultList;
		 
	}
}
