package ECGLearning.src.baumWelch.ML.HMM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataClassifier {

	public static void main(String[] args) {

		String csvFile = "/home/598813/workspace/set-p/set-p/100.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		String signalOut = null;
		int Fenstergroesse = 5;
		double x = 0.0;
		int n = 0;
		boolean k = false;

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] signal = line.split(cvsSplitBy);
				if (k = false)
					// alt: jede Zeile wird gelesen und ausgegeben
					// System.out.println("Timestamp = " + signal[0] + " , ECG =
					// " +
					// signal[1] + "]");

					// neu: n Zeilen werden zu einem Wert und dann ausgegeben
					// möglicherweise müssen die Schleifen gedreht werden
					while (n <= Fenstergroesse) {
						x = x + Double.valueOf(signal[1]);
						signalOut = signal[0];
					}
				Fenstergroesse = 0;
				System.out.println("[Timestamp = " + signalOut + " , ECG = " + x + "]");

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}