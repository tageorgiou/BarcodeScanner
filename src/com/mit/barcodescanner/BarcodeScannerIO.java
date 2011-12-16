package com.mit.barcodescanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class BarcodeScannerIO {
	public Map<String, String> readScannerData() throws IOException {
		Map<String, String> tubeBarcodeMap = new HashMap<String,String>();
		tubeBarcodeMap.put("A2", "234324");
		return tubeBarcodeMap;
//		URL scannerURL = new URL("http://18.125.7.3:2233/");
//		URLConnection sc = scannerURL.openConnection();
//		BufferedReader in = new BufferedReader(
//				new InputStreamReader(
//						sc.getInputStream()));
//		String inputLine;
//		in.readLine();
//		while ((inputLine = in.readLine()) != null) {
//			String[] splitLine = inputLine.split(",");
//			tubeBarcodeMap.put(splitLine[0], splitLine[1]);
//		}
//		in.close();
//		return tubeBarcodeMap;
	}
}
