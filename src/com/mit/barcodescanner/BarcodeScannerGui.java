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

class PlateUI extends JComponent {
	Map<String, TestTubeButton> tubeMap;
	public PlateUI(ActionListener al) {
		super();
		tubeMap = new HashMap<String, TestTubeButton>();
		setLayout(new GridLayout(8,12));
		for (char i = 'A'; i <= 'H'; i++) {
			for (int j = 1; j <= 12; j++) {
				String id = "" + i + j;
				TestTubeButton button = new TestTubeButton(id, "");
				button.setFont(new Font("sansserif",Font.PLAIN, 10));
				button.addActionListener(al);
				add(button);
				tubeMap.put(id, button);
			}
		}
	}
}

public class BarcodeScannerGui extends JComponent {
	private PlateUI plateui;
	private JButton scanButton;
	private Map<String, TestTubeButton> tubeMap;
	private AnnotatedPluginDocument[] documents;
	private int documentIndex = 0;
	private List<AnnotationGeneratorResult> resultsList;
	private JLabel geneLabel;
	public BarcodeScannerGui(final AnnotatedPluginDocument[] documents, final List<AnnotationGeneratorResult> resultsList) {
		super();
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (documentIndex >= documents.length)
					return;
				TestTubeButton t = (TestTubeButton) e.getSource();
				System.out.println(t.getText());
				if (t.hasBarcode()) {
					DocumentField barcodeField = makeBarcodeField();
					AnnotationGeneratorResult result = new AnnotationGeneratorResult();
					result.addDocumentFieldToSet(new DocumentFieldAndValue(barcodeField, "BLOOP"));
					resultsList.add(result);
					documentIndex++;
					if (documentIndex >= documents.length) {
						geneLabel.setText("Done");
					} else {
						geneLabel.setText(documents[documentIndex].getName());
					}
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
		geneLabel.setText(documents[documentIndex].getName());
		add(geneLabel);
		scanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Scanning...");	
				scan();
			}
		});
	};
	
	private void scan() {
		BarcodeScannerIO bcs = new BarcodeScannerIO();
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
}
