package com.mit.barcodescanner;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.biomatters.geneious.publicapi.documents.AnnotatedPluginDocument;
import com.biomatters.geneious.publicapi.documents.DocumentField;
import com.biomatters.geneious.publicapi.documents.DocumentFieldAndValue;
import com.biomatters.geneious.publicapi.plugin.SequenceAnnotationGenerator.AnnotationGeneratorResult;

public class BarcodeFinderGUI extends JComponent {
	private PlateUI plateui;
	private JButton scanButton;
	private Map<String, TestTubeButton> tubeMap;
	private JLabel geneLabel;
	public BarcodeFinderGUI(final List<String> barcodes) {
		super();
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestTubeButton t = (TestTubeButton) e.getSource();
				System.out.println(t.getText());
				if (t.hasBarcode()) {
					barcodes.add(t.getBarcode());
				}
			}
		};
		plateui = new PlateUI(al);
		tubeMap = plateui.tubeMap;
		setLayout(new FlowLayout());
		add(plateui);
		scanButton = new JButton("Scan");
		add(scanButton);
		geneLabel = new JLabel();
		geneLabel.setText("Select test tubes to search for");
		add(geneLabel);
		//setup size
		for (char i = 'A'; i <= 'H'; i++) {
			for (int j = 1; j <= 12; j++) {
				String id = "" + i + j;
				tubeMap.get(id).setText("-");
			}
		}
		scanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Scanning...");	
				scan();
			}
		});
	};
	
	private void scan() {
		BarcodeScannerIO bcs = new BarcodeScannerIO("http://18.125.5.183:2233/");
		Map<String, String> tubeBarcodeMap;
		try {
			 tubeBarcodeMap = bcs.readScannerData();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		for (char i = 'A'; i <= 'H'; i++) {
			for (int j = 1; j <= 12; j++) {
				String id = "" + i + j;
				if (tubeBarcodeMap.containsKey(id)) {
					String barcode = tubeBarcodeMap.get(id);
					tubeMap.get(id).setBarcode(barcode);
					tubeMap.get(id).setText("O");
				} else {
					tubeMap.get(id).clearBarcode();
					tubeMap.get(id).setText("-");
				}
			}
		}
	}
	
	private DocumentField makeBarcodeField() {
		return DocumentField.createStringField("barcode", "Document's barcode", "barcode.code");
	}
	
	private DocumentField makePlateLocField() {
		return DocumentField.createStringField("plateloc", "Document's plate location", "plateloc.code");
	}
}
