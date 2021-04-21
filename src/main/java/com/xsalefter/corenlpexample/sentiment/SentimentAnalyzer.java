package com.xsalefter.corenlpexample.sentiment;

import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class SentimentAnalyzer {

    public SentimentTweet getSentiment(String line) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        int mainSentiment = 0;
        if (line != null && line.length() > 0) {
            int longest = 0;
            Annotation annotation = pipeline.process(line);
            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                String partText = sentence.toString();
                if (partText.length() > longest) {
                    mainSentiment = sentiment;
                    longest = partText.length();
                }

            }
        }

        return new SentimentTweet(line, translateSentiment(mainSentiment));
    }

    private String translateSentiment(int sentiment) {
        switch (sentiment) {
            case 0:
                return "very-negative";
            case 1:
                return "negative";
            case 2:
                return "neutral";
            case 3:
                return "positive";
            case 4:
                return "very-positive";
            default:
                throw new IllegalArgumentException("Unknown sentiment value: " + sentiment);
        }
    }
}