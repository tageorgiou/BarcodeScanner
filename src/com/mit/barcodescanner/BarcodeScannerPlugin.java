package com.mit.barcodescanner;

import com.biomatters.geneious.publicapi.documents.AnnotatedPluginDocument;
import com.biomatters.geneious.publicapi.documents.DocumentUtilities;
import com.biomatters.geneious.publicapi.documents.PluginDocument;
import com.biomatters.geneious.publicapi.documents.sequence.AminoAcidSequenceDocument;
import com.biomatters.geneious.publicapi.documents.sequence.NucleotideSequenceDocument;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceDocument;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultAminoAcidSequence;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultNucleotideSequence;
import com.biomatters.geneious.publicapi.plugin.*;

import jebl.util.ProgressListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Joseph Heled
 * @version $Id: BarcodeScannerPlugin.java 19210 2008-05-02 05:20:17Z amy $
 */
public class BarcodeScannerPlugin extends GeneiousPlugin {
    
    public String getName() {
        return "Barcode Scanner";
    }

    public String getDescription() {
        return "Scan barcodes";
    }

    public String getHelp() {
        return null;
    }

    public String getAuthors() {
        return "Thomas Georgiou";
    }

    public String getVersion() {
        return "0.1";
    }

    public String getMinimumApiVersion() {
        return "4.0";
    }

    public int getMaximumApiVersion() {
        return 4;
    }
    
    public SequenceAnnotationGenerator[] getSequenceAnnotationGenerators() {
    	return new SequenceAnnotationGenerator[] {
    			new BarcodeScannerAnnotationGenerator()
		};
    }
}
