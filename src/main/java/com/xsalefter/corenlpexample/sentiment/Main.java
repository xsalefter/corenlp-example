package com.xsalefter.corenlpexample.sentiment;

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;

public class Main {

    private static final String TWEET1 = "Anybody make music? I need some songs for a video project and it would be sweet to use some of your stuff. I'm not totally sure what I want yet though, I'll know it when I hear it. Show off your tunes!";
    private static final String TWEET2 = "This movie doesn't care about cleverness, wit or any other kind of intelligent humor.\n" +
            "Those who find ugly meanings in beautiful things are corrupt without being charming.\n" +
            "There are slow and repetitive parts, but it has just enough spice to keep it interesting.";
    private static final String TWEET3 = "Fuck you";
    private static final String TWEET4 = "She's dead. You're looking for a ghost. Are you happy now?";
    private static final String TWEET5 = "I'm going to kill him by chopping his head";
    private static final String TWEET6 = "This movie was actually neither that funny, nor super witty.";

    public static void main(String... args) {
        runWithSentimentAnalyzer(TWEET1, TWEET2, TWEET3, TWEET4, TWEET5, TWEET6);
        runWithSimpleAPI(TWEET1, TWEET2, TWEET3, TWEET4, TWEET5, TWEET6);
    }

    private static void runWithSentimentAnalyzer(String... tweets) {
        SentimentAnalyzer analyzer = new SentimentAnalyzer();
        System.out.println("============ SENTIMENT ANALYZER ===================");
        for (String tweet : tweets) {
            System.out.println(analyzer.getSentiment(tweet));
        }
        System.out.println("===================================================");
    }

    private static void runWithSimpleAPI(String... tweets) {
        System.out.println("************* CORENLP SIMPLE SENTENCE *************");
        for (String tweet : tweets) {
            System.out.println("*** NEW TWEET: ");
            Document document = new Document(tweet);
            for (Sentence sentence : document.sentences()) {
                System.out.println(new SentimentTweet(sentence.text(), sentence.sentiment().toString()));
            }
        }
    }
}
