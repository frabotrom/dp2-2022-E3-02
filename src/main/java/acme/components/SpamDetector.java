package acme.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import acme.entities.SystemConfiguration;

public class SpamDetector {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Methods
	
	private SpamDetector() {}
	
	public static Boolean isSpam(final String textToCheck, final SystemConfiguration systemConfiguration) {
		
		boolean result = false;
		
		final List<String> wordsToCheck = SpamDetector.obteinWordsToText(textToCheck);		
		final List<List<String>> weakSpamTermsWords = SpamDetector.obteinSpamWords(systemConfiguration.getWeakSpamTerms());		
		final List<List<String>> strongSpamTermsWords = SpamDetector.obteinSpamWords(systemConfiguration.getStrongSpamTerms());
		
		final Double weakThreshold = systemConfiguration.getWeakThreshold();
		final Double strongThreshold = systemConfiguration.getStrongThreshold();
		
		final Double weakSpamRatio = SpamDetector.spamRatioCalculator(wordsToCheck, weakSpamTermsWords);
		final Double strongSpamRatio = SpamDetector.spamRatioCalculator(wordsToCheck, strongSpamTermsWords);
		
		if(weakSpamRatio >= weakThreshold || strongSpamRatio >= strongThreshold) {
			result = true;
		}
		
		return result;
	}
	
	private static List<String> obteinWordsToText(final String originalText){
		
		// Los replace quitan los signos de puntuación y seapradores más comunes.
		// El guión y la barra baja no aparecen porque pueden ser usados para formar una única palabra
		
		return Arrays.asList(originalText.replaceAll("[.,:;/*=|()¡!¿?{}`´<>]"," ")
			.replace("\""," ").replace("\\"," ").replace("["," ").replace("]"," ").trim().split("\\s+"));		
	}
	
	private static List<List<String>> obteinSpamWords(final String originalText){
		
		final List<String> spamSentenceList = Arrays.asList(originalText.split(","));
		final List<List<String>> spamWordsListEdited = new ArrayList<List<String>>();
		
		for(final String sentence: spamSentenceList) {
			
			final List<String> spamWordsList = Arrays.asList(sentence.trim().split("\\s+"));
			spamWordsListEdited.add(spamWordsList);
		}
		
		return spamWordsListEdited;
	}
	
	private static Double spamRatioCalculator(final List<String> wordsToCheck, final List<List<String>> spamTermsSentences) {
		
		Integer spamTermRecurrence = 0;
		
		// Para evitar elementos repetidos
		final Set<List<String>> spamTermsSentencesSet = new HashSet<List<String>>(spamTermsSentences);
		final List<List<String>> spamTermsSentencesList = new ArrayList<List<String>>(spamTermsSentencesSet);
		
		final StringBuilder sentenceBuilder = new StringBuilder("/");
		for(final String word: wordsToCheck) {
			sentenceBuilder.append(word.toUpperCase() + "/");
		}
		
		final String sentenceToCheck = sentenceBuilder.toString();
		
		for(final List<String> spamTermsSentence: spamTermsSentencesList) {
			
			final StringBuilder spamTermBuilder = new StringBuilder("/");
			for(final String word: spamTermsSentence) {
				spamTermBuilder.append(word.toUpperCase() + "/");
			}
			
			final String spamTerm = spamTermBuilder.toString();
			String sentenceToCheckAux = sentenceToCheck;
			
			if(sentenceToCheck.contains(spamTerm)) {
				while (sentenceToCheckAux.indexOf(spamTerm) > -1) {
					
					sentenceToCheckAux = sentenceToCheckAux.substring(
						sentenceToCheckAux.indexOf(spamTerm)+spamTerm.length()-1, sentenceToCheckAux.length());
				    spamTermRecurrence = spamTermRecurrence + spamTermsSentence.size();
				}
			}	
		}
		
		return (double) spamTermRecurrence/wordsToCheck.size();
	}	
}
