package ECGLearning.src.baumWelch.ML.HMM;

import java.util.Hashtable;
import java.util.Vector;

import ECGLearning.src.baumWelch.DA.Processing.DataDecoding;
import ECGLearning.src.baumWelch.Util.Parser.JsonParser;
import javafx.util.Pair;

public class Main {
	public static void main(String[] args) throws Exception {
		JsonParser jp = new JsonParser("/home/598813/workspace/Hidden-Markov-Model-master/Resources/test_HMM.json");
		String name = DataDecoding.getInstance().getModelName(jp.getName());
		Vector<String> states = DataDecoding.getInstance().getStates(jp.getStates());
		Vector<String> observations = DataDecoding.getInstance().getObservations(jp.getObservations());
		Hashtable<String, Double> initialProbabilities = DataDecoding.getInstance()
				.getInitialProbabilities(jp.getInitialProbabilities());
		Hashtable<Pair<String, String>, Double> transitionMatrix = DataDecoding.getInstance()
				.getTransitionMatrix(jp.getTransitionMatrix());
		Hashtable<Pair<String, String>, Double> emissionMatrix = DataDecoding.getInstance()
				.getEmissionMatrix(jp.getEmissionMatrix());

		System.out.println(initialProbabilities);
		// String name = "Test" ;

		Vector<String> estimatedStates = new Vector<String>();
		estimatedStates.add("R");
		estimatedStates.add("S");
		estimatedStates.add("R");
		estimatedStates.add("S");
		estimatedStates.add("R");
		estimatedStates.add("S");
		estimatedStates.add("R");
		estimatedStates.add("S");

		// sampleStates.add("R");

		Vector<String> observationSequence = new Vector<String>();
		observationSequence.add("U");
		observationSequence.add("D");
		observationSequence.add("D");
		observationSequence.add("U");
		observationSequence.add("U");
		observationSequence.add("D");

		observationSequence.add("U");
		observationSequence.add("D");

		// sampleO.add("U");

		// sampleO.add("U");
		// sampleO.add("D");
		// sampleO.add("U");

		/*
		 * Hashtable<String, Double> initialProbabilities = new Hashtable();
		 * Hashtable<Pair<String, String>, Double> transitionMatrix =new
		 * Hashtable() ; Hashtable<Pair<String, String>, Double> emissionMatrix
		 * = new Hashtable();
		 */

		initialProbabilities.put("R", 0.3);
		initialProbabilities.put("S", 0.4);
		initialProbabilities.put("C", 0.3);

		Pair rr = new Pair("R", "R");
		Pair rs = new Pair("R", "S");
		Pair rc = new Pair("R", "C");
		Pair sr = new Pair("S", "R");
		Pair ss = new Pair("S", "S");
		Pair sc = new Pair("S", "C");
		Pair cr = new Pair("C", "R");
		Pair cs = new Pair("C", "S");
		Pair cc = new Pair("C", "C");

		transitionMatrix.put(rr, 0.2);
		transitionMatrix.put(rs, 0.1);
		transitionMatrix.put(rc, 0.7);
		transitionMatrix.put(sr, 0.3);
		transitionMatrix.put(ss, 0.4);
		transitionMatrix.put(sc, 0.3);
		transitionMatrix.put(cr, 0.1);
		transitionMatrix.put(cs, 0.4);
		transitionMatrix.put(cc, 0.5);

		Pair rf = new Pair("R", "F");
		Pair ru = new Pair("R", "U");
		Pair rd = new Pair("R", "D");
		Pair sf = new Pair("S", "F");
		Pair su = new Pair("S", "U");
		Pair sd = new Pair("S", "D");
		Pair cf = new Pair("C", "F");
		Pair cu = new Pair("C", "U");
		Pair cd = new Pair("C", "D");

		emissionMatrix.put(rf, 0.4);
		emissionMatrix.put(ru, 0.5);
		emissionMatrix.put(rd, 0.1);
		emissionMatrix.put(sf, 0.4);
		emissionMatrix.put(su, 0.0);
		emissionMatrix.put(sd, 0.6);
		emissionMatrix.put(cf, 0.4);
		emissionMatrix.put(cu, 0.2);
		emissionMatrix.put(cd, 0.4);

		HiddenMarkovModel hmm = new HiddenMarkovModel(name, estimatedStates, observationSequence, initialProbabilities,
				transitionMatrix, emissionMatrix);

		System.out.println(hmm.evaluateUsingBruteForce(estimatedStates, observationSequence));
		System.out.println(hmm.evaluateUsingForwardAlgorithm(estimatedStates, observationSequence));
		System.out.println(hmm.getOptimalStateSequenceUsingViterbiAlgorithm(states, observationSequence));
		hmm.estimateParametersUsingBaumWelchAlgorithm(states, observationSequence, false);
		System.out.println(hmm.getInitialProbabilities());
		System.out.println(hmm.getTransitionMatrix());
		System.out.println(hmm.getEmissionMatrix());
		System.out.println(hmm.evaluateUsingBruteForce(estimatedStates, observationSequence));
		System.out.println(hmm.evaluateUsingForwardAlgorithm(estimatedStates, observationSequence));

	}

}
